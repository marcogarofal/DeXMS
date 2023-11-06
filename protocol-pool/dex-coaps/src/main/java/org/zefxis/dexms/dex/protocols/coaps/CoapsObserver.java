package org.zefxis.dexms.dex.protocols.coaps;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.Data.Context;
import org.zefxis.dexms.gmdl.utils.Data.MediaType;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;
import org.eclipse.californium.core.network.Endpoint;

public class CoapsObserver implements Runnable {

	private CoapClient DTLSclient = null;
	JSONParser parser = new JSONParser();
	JSONObject jsonObject = null;
	private static String op_name = null;
	private static Operation op = null;
	private String coapUri = null;
	private MediatorCoapsSubcomponent bcGmSubcomponent;
	private GmServiceRepresentation serviceRepresentation;

	public CoapsObserver(MediatorGmSubcomponent bcGmSubcomponent, GmServiceRepresentation serviceRepresentation,
			MediatorConfiguration bcConfiguration) {
		this.bcGmSubcomponent = (MediatorCoapsSubcomponent) bcGmSubcomponent;
		this.serviceRepresentation = serviceRepresentation;

		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {

			op_name = en.getKey();
			op = en.getValue();
		}
		coapUri = "coaps://" + bcConfiguration.getSubcomponentAddress() + ":" + bcConfiguration.getSubcomponentPort()
				+ "/" + op_name;

	}

	public void run() {

		System.out.println("coapUri " + coapUri);
		CoapClient DTLSclient = new CoapClient(coapUri).useCONs();
		DTLS_conf(DTLSclient);
		DTLSclient = DTLSclient.useExecutor();
		CoapObserveRelation relation = DTLSclient.observe(

				new CoapHandler() {
					@Override
					public void onLoad(CoapResponse response) {

						Context context = null;
						MediaType media = null;
						String receivedText = response.getResponseText();
						String message_id = "";
						String message = receivedText;
						if(receivedText.contains("-")) {
							
							if (receivedText.split("-").length > 1) {

								message_id = receivedText.split("-")[1];
								message = receivedText.split("-")[0];
							}
						}
						
						JSONParser parser = new JSONParser();
						JSONObject jsonObject = null;

						try {
							jsonObject = (JSONObject) parser.parse(message);
						} catch (ParseException e) {
							e.printStackTrace();
						}

						List<Data<?>> datas = new ArrayList<>();

						for (Data<?> data : op.getGetDatas()) {
							Data d = new Data<String>(data.getName(), "String", true,
									String.valueOf(jsonObject.get(data.getName())), data.getContext(),
									data.getMediaType());
							datas.add(d);
							context = data.getContext();
							media = data.getMediaType();
						}

						if (!message_id.equals("")) {

							Data d = new Data<String>("op_name", "String", true, op_name, context, media);
							datas.add(d);

							d = new Data<String>("message_id", "String", true, message_id, context, media);
							datas.add(d);
						}

						if (op.getOperationType() == OperationType.TWO_WAY_SYNC) {

							bcGmSubcomponent.mgetTwowaySync(op.getScope(), datas);
						} else if (op.getOperationType() == OperationType.ONE_WAY) {

							bcGmSubcomponent.mgetOneway(op.getScope(), datas);
						}

					}
					
			

					@Override
					public void onError() {
						System.err.println("OBSERVING FAILED (press enter to exit)");
					}
				});

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			br.readLine();
		} catch (IOException e) {
		}

	}
	
	public void DTLS_conf(CoapClient client) {
		String keystoreFilePath = "/Users/zbenomar/Desktop/CP4SC/CoAP-publisher/end-to-end-examples/mqtt-to-coap/ectest3.jks";
        String keystorePassword = "testtest2";

        KeyStore keyStore=null;
		try {
			keyStore = KeyStore.getInstance("JKS");
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FileInputStream keyStoreFile=null;
		try {
			keyStoreFile = new FileInputStream(new String(keystoreFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			keyStore.load(keyStoreFile, keystorePassword.toCharArray());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Load the private key from the keystore
        char[] keyPassword = keystorePassword.toCharArray();
        PrivateKey privateKey = null;
		try {
			privateKey = (PrivateKey) keyStore.getKey("testtest2", keyPassword);
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Load the certificate chain from the keystore
        java.security.cert.Certificate[] certificateChain = null;
		try {
			certificateChain = keyStore.getCertificateChain("testtest2");
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Create a DTLS configuration
        DtlsConnectorConfig.Builder dtlsConfigBuilder = new DtlsConnectorConfig.Builder();
        //dtlsConfigBuilder.setSupportedCipherSuites(new CipherSuite[]{CipherSuite.});
        dtlsConfigBuilder.setIdentity(privateKey, certificateChain);
        dtlsConfigBuilder.setTrustStore(certificateChain);
        //dtlsConfigBuilder.setAddress(bindAddress);


        // Create a DTLS connector
        DTLSConnector dtlsConnector = new DTLSConnector(dtlsConfigBuilder.build());
        
        // Create a CoAP endpoint with the InetSocketAddress
        CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
        //builder.setInetSocketAddress(bindAddress);
        builder.setConnector(dtlsConnector);
        Endpoint coapEndpoint = builder.build();
        //EndpointManager.getEndpointManager().setDefaultEndpoint(coapEndpoint);
        // Add the CoAP endpoint to the server
        client.setEndpoint(coapEndpoint);

	}

}

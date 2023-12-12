package org.zefxis.dexms.dex.protocols.coaps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.network.config.NetworkConfigDefaults;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.EndpointManager;





public class CoapsObservableServer extends Thread {

	private static CoapsObservableResource coapObservableResource = null;
	private CoapServer coapServer = null;
	private BlockingQueue<String> waitingQueue = null;
	private String resourceName = null;
	private String hostIp = null;
	private int hostport = 0;
	private NetworkConfig config = null;

	public CoapsObservableServer(GmServiceRepresentation serviceRepresentation, String hostIp, int hostport,
			BlockingQueue<String> waitingQueue) {
		this.waitingQueue = waitingQueue;
		this.hostIp = hostIp;
		this.hostport = hostport;

		this.config = NetworkConfig.getStandard().setInt(NetworkConfig.Keys.PROTOCOL_STAGE_THREAD_COUNT, 100);

		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {

			this.resourceName = en.getKey();
		}

	}

	private Endpoint addEndpoints(String hostIp, int hostport) throws SocketException {
		//Endpoint coapEndpoint;
		InetSocketAddress bindToAddress = new InetSocketAddress(hostIp, hostport);
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
        dtlsConfigBuilder.setAddress(bindToAddress);
        
     // Create a DTLS connector
        DTLSConnector dtlsConnector = new DTLSConnector(dtlsConfigBuilder.build());
        
        // Create a CoAP endpoint with the InetSocketAddress
        CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
        //builder.setInetSocketAddress(bindAddress);
        builder.setConnector(dtlsConnector);
        Endpoint coapEndpoint = builder.build();
        //EndpointManager.getEndpointManager().setDefaultEndpoint(coapEndpoint);
        // Add the CoAP endpoint to the server
        //server.addEndpoint(coapEndpoint);

        coapEndpoint = builder.build();
        return coapEndpoint;
		//return new CoapEndpoint(dtlsConnector, false, config, null, null, null, null, hostIp, null, bindToAddress);

	}
	
	static class HelloWorldResource extends CoapResource {
        public HelloWorldResource() {
            super("hello");
            getAttributes().setTitle("Hello World Resource");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
        	System.out.println("I received a GET");
            exchange.respond("Hello, CoAP!");
        }
    }


	public void run() {        

		coapServer = new CoapServer(config);
		HelloWorldResource helloResource = new HelloWorldResource();
		coapServer.add(helloResource);


		try {
			coapServer.addEndpoint(addEndpoints(hostIp, hostport));
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}

		coapObservableResource = new CoapsObservableResource(resourceName, true, waitingQueue);
		coapServer.add(coapObservableResource);

		coapServer.start();

		while (true) {

			coapObservableResource.resourceChange();
			try {
				
				Thread.sleep(2000);
			} catch(Exception e) {}
			
			
		}

	}

}

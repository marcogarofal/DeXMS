package eu.chorevolution.vsb.gm.protocols.coap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.chorevolution.vsb.gm.protocols.primitives.BcGmSubcomponent;
import eu.chorevolution.vsb.gmdl.utils.BcConfiguration;
import eu.chorevolution.vsb.gmdl.utils.Data;
import eu.chorevolution.vsb.gmdl.utils.Data.Context;
import eu.chorevolution.vsb.gmdl.utils.Data.MediaType;
import eu.chorevolution.vsb.gmdl.utils.GmServiceRepresentation;
import eu.chorevolution.vsb.gmdl.utils.Operation;
import eu.chorevolution.vsb.gmdl.utils.enums.OperationType;

public class CoapObserver implements Runnable {

	private CoapClient client = null;
	JSONParser parser = new JSONParser();
	JSONObject jsonObject = null;
	private static String op_name = null;
	private static Operation op = null;
	private String coapUri = null;
	private BcCoapSubcomponent bcGmSubcomponent;
	private GmServiceRepresentation serviceRepresentation;

	public CoapObserver(BcGmSubcomponent bcGmSubcomponent, GmServiceRepresentation serviceRepresentation,
			BcConfiguration bcConfiguration) {
		
		this.bcGmSubcomponent = (BcCoapSubcomponent) bcGmSubcomponent;
		this.serviceRepresentation = serviceRepresentation;

		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {

			op_name = en.getKey();
			op = en.getValue();
		}
		coapUri = "coap://" + bcConfiguration.getSubcomponentAddress() + ":" + bcConfiguration.getSubcomponentPort()
				+ "/" + op_name;
		
	}

	public void run() {
		
		System.out.println("coapUri "+coapUri);
		CoapClient client = new CoapClient(coapUri).useNONs();
		client = client.useExecutor();
		CoapObserveRelation relation = client.observe(

				new CoapHandler() {
					@Override
					public void onLoad(CoapResponse response) {

						Context context = null;
						MediaType media = null;
						String receivedText = response.getResponseText();
						String message_id = "";
						String message = receivedText;
						if (receivedText.split("-").length > 1) {

							message_id = receivedText.split("-")[1];
							message = receivedText.split("-")[0];
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
		try {br.readLine();} catch (IOException e) {}

	}

}

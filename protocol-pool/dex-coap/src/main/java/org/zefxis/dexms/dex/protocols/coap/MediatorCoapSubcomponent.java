package org.zefxis.dexms.dex.protocols.coap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.Data.Context;
import org.zefxis.dexms.gmdl.utils.Data.MediaType;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;


public class MediatorCoapSubcomponent extends MediatorGmSubcomponent {

	private static CoapClient client = null;
	private CoapServer server;
	private GmServiceRepresentation serviceRepresentation;
	private ExecutorService executor = null;
	private String coapDestination = null;
	private CoapObserver observer = null;
	private Thread observerThread = null;
	
	public MediatorCoapSubcomponent(MediatorConfiguration bcConfiguration, GmServiceRepresentation serviceRepresentation) {
		super(bcConfiguration);
		switch (this.bcConfiguration.getSubcomponentRole()) {
		case SERVER:
			
		/*	this.serviceRepresentation = serviceRepresentation;
			NetworkConfig config = NetworkConfig.getStandard().setInt(NetworkConfig.Keys.PROTOCOL_STAGE_THREAD_COUNT, 100)
			                                           .setString(NetworkConfig.Keys.DEDUPLICATOR, "NO_DEDUPLICATOR");
			System.out.println(" this.bcConfiguration.getSubcomponentPort() "+this.bcConfiguration.getSubcomponentPort());
			server = new CoapServer(config, this.bcConfiguration.getSubcomponentPort());*/
			
			observer = new CoapObserver(this, serviceRepresentation, bcConfiguration);
			break;

		case CLIENT:
			
			executor = Executors.newFixedThreadPool(10);
//			agentPost = new MeasureAgent("timestamp_3",System.currentTimeMillis(),MonitorConstant.M2,MonitorConstant.timestamp_3_port_listener);
			coapDestination = "coap://"+bcConfiguration.getServiceAddress()+":"+bcConfiguration.getServicePort();
			this.client = new CoapClient(coapDestination+"bridgeNextClosure").useNONs();
			this.client.setURI(coapDestination+"/"+"bridgeNextClosure");  

			break;
		default:
			break;
		}
	}

	@Override
	public void start(){
		switch (this.bcConfiguration.getSubcomponentRole()) {
		case SERVER:
			
/*			server.start();
			server.add(getQueryListenerResource());*/
			observerThread = new Thread(observer);
			observerThread.start();

			break;
		case CLIENT:

			break;
		default:
			break;
		}
	}

	CoapResource getQueryListenerResource() {
		CoapResource resource = new CoapResource("bridgeNextClosure"){
			@Override
			public void handlePOST(CoapExchange exchange){
				
				String receivedText = exchange.getRequestText();
				String message_id = receivedText.split("-")[1];
				String message = receivedText.split("-")[0];
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = null;

				try {

					jsonObject = (JSONObject) parser.parse(message);

				} catch (ParseException e) {

					e.printStackTrace();
				}

				String op_name = (String) jsonObject.get("op_name");
				for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations()
						.entrySet()) {
					if (en.getKey().equals(op_name)) {
//						agentMget.fire(""+System.currentTimeMillis()+"-"+message_id);
						Operation op = en.getValue();
						List<Data<?>> datas = new ArrayList<>();
						Context context = null;
						MediaType media = null;
						for (Data<?> data : op.getGetDatas()) {
							Data d = new Data<String>(data.getName(), "String", true,
									(String) jsonObject.get(data.getName()), data.getContext(), data.getMediaType());
							datas.add(d);
//							System.err.println("Added " + d);
							context = data.getContext();
							media = data.getMediaType();
						}
						Data d = new Data<String>("op_name", "String", true,op_name, context,media);
						datas.add(d);
						d = new Data<String>("message_id", "String", true,message_id, context,media);
						datas.add(d);
						if (op.getOperationType() == OperationType.TWO_WAY_SYNC){

							String response = mgetTwowaySync(op.getScope(), datas);
							exchange.accept();
							exchange.respond(response);
							
						}else if (op.getOperationType() == OperationType.ONE_WAY){

							mgetOneway(op.getScope(), datas);
							exchange.accept();
						}
					}
				}
				
			}
		};
		return resource;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() {
		switch (this.bcConfiguration.getSubcomponentRole()){
		case SERVER:
			// server.stop();
			observerThread.stop();
			break;
		case CLIENT:
			try {
				 this.client.shutdown();
			} catch (Exception e){
				
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void postOneway(final String destination, final Scope scope, final List<Data<?>> datas, final long lease) {
		
		String message_id = null;
		JSONObject jsonObject = new JSONObject();
		
		for(Data<?> data : datas){
			
			if(data.getName().equals("message_id")){
				
				message_id = String.valueOf(data.getObject());
				
			}else{
				
				jsonObject.put(data.getName(), String.valueOf(data.getObject()));	
			}
			
		}
		
//		String message = jsonObject.toJSONString()+"-"+message_id;
//		CoapClient client = new CoapClient(coapDestination+"bridgeNextClosure").useNONs();
//		client.setURI(coapDestination+"/"+"bridgeNextClosure");  
//		client.post(message, MediaTypeRegistry.APPLICATION_JSON);

		ThreadSendCoapMessage sendCoapMessage  = new ThreadSendCoapMessage(jsonObject.toJSONString()+"-"+message_id);
//		agentPost.fire(""+System.currentTimeMillis()+"-"+message_id);
		sendCoapMessage.start();
        sendCoapMessage = null;
        
	}

	@Override
	public void mgetOneway(final Scope scope, final Object exchange) {
		
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);
		
	}

	@Override
	public void xmgetOneway(final String source, final Scope scope, final Object exchange) {
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);
	}

	@Override
	public <T> T postTwowaySync(final String destination, final Scope scope, final List<Data<?>> datas,
			final long lease) {

//		T responseText = (T) this.client.sendTwoWayRequest(destination, scope, datas, lease);
		return null;
	}

	@Override
	public void xtgetTwowaySync(final String destination, final Scope scope, final long timeout,
			final Object response) {
		// TODO Auto-generated method stub
	}

	@Override
	public <T> T mgetTwowaySync(final Scope scope, final Object exchange) {
		return this.nextComponent.postTwowaySync(this.bcConfiguration.getServiceAddress(), scope,
				(List<Data<?>>) exchange, 0);
	}

	@Override
	public void postTwowayAsync(final String destination, final Scope scope, final List<Data<?>> data,
			final long lease) {
		// TODO Auto-generated method stub
	}

	@Override
	public void xgetTwowayAsync(final String destination, final Scope scope, final Object response) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mgetTwowayAsync(final Scope scope, final Object exchange) {
		this.nextComponent.postTwowayAsync(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange,
				0);
	}

	@Override
	public void postBackTwowayAsync(final String source, final Scope scope, final Data<?> data, final long lease,
			final Object exchange){
		// TODO Auto-generated method stub
	}
	
	
	
	
	private static class ThreadSendCoapMessage extends Thread{
		
		String message = null;
		
		public ThreadSendCoapMessage(String message){
			
			this.message = message;
		}
		
		public void run(){
			
			client.post(message, MediaTypeRegistry.APPLICATION_JSON);
	        //client.shutdown();	
		   
		}
		
		
	}




	@Override
	public void setGmServiceRepresentation(GmServiceRepresentation serviceRepresentation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GmServiceRepresentation getGmServiceRepresentation(){
		// TODO Auto-generated method stub
		return null;
	}

}

package org.zefxis.dexms.dex.protocols.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Header;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;
import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;
import org.zefxis.dexms.tools.logger.GLog;
import org.zefxis.dexms.tools.logger.Logger;



public class MediatorRestSubcomponent extends MediatorGmSubcomponent {

	private Client client = null;
	private Server server = null;
	private Component component;
	private Logger logger = GLog.initLogger();

	private Component printerComponent;
	private Server printerServer;
	private static String printermsg = "empty";
	private static MediatorRestSubcomponent bcRestSubcomponent;
	private static String message;
	private ExecutorService executor = null;

	public MediatorRestSubcomponent(

			MediatorConfiguration bcConfiguration, GmServiceRepresentation serviceRepresentation

	) {

		super(bcConfiguration);
		
		System.out.println("MediatorRestSubcomponent --> "+this.bcConfiguration.getSubcomponentRole());
		this.bcRestSubcomponent = this;
		setGmServiceRepresentation(serviceRepresentation);
		int restservicePort = Integer.valueOf(bcConfiguration.getServicePort());
		switch (this.bcConfiguration.getSubcomponentRole()) {

		case SERVER:

			this.server = new Server(Protocol.HTTP, this.bcConfiguration.getSubcomponentAddress(),
					this.bcConfiguration.getSubcomponentPort());
			this.component = new Component();
			this.component.getServers().add(server);
			this.component.getContext().getParameters().add("maxThreads", "100");
			this.component.getContext().getParameters().add("maxTotalConnections", "-1");
			break;

		case CLIENT:

			executor = Executors.newFixedThreadPool(10);
			client = new Client(Protocol.HTTP);
			this.printerServer = new Server(Protocol.HTTP, restservicePort);
			this.printerComponent = new Component();
			this.printerComponent.getServers().add(printerServer);
			break;

		default:
			break;
		}
	}

	@Override
	public void start() {
		switch (this.bcConfiguration.getSubcomponentRole()) {
		case SERVER:

			try {
				this.component.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// this.component.setDefaultHost(this.bcConfiguration.getSubcomponentAddress());
			// this.component.getDefaultHost().attach("/",
			// RestServerResource.class);
			this.component.getDefaultHost().attach("/randomValue", new RestletRestService());

			break;
		case CLIENT:
			try {

				this.client.start();
				this.printerComponent.start();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			this.printerComponent.getDefaultHost().attach("/getmessage", PrinterRestServerResource.class);
			break;
		default:
			break;
		}
	}

	@Override
	public void stop() {
		switch (this.bcConfiguration.getSubcomponentRole()) {
		case SERVER:
			try {

				this.component.stop();

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			break;
		case CLIENT:
			try {
				this.client.stop();
				this.printerComponent.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void postOneway(final String destination, final Scope scope, final List<Data<?>> datas, final long lease) {
		System.out.println("This is Post one way of HTTP");
		
		
		JSONObject msgObj = new JSONObject();
		String message_id = "";
		for (Data d : datas) {

			if (!d.getName().equals("message_id")) {

				msgObj.put(d.getName(), d.getObject().toString());

			} else {

				message_id = d.getObject().toString();
			}

		}
		
		message = msgObj.toJSONString();
		if(!message_id.equals("")){
			
			message = msgObj.toJSONString() + "-" + message_id;
		}
		
		System.out.println("I received this data: "+message );
		executor.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Request request = new Request();
				request.setResourceRef(bcConfiguration.getServiceAddress());
				request.setMethod(Method.POST);
				request.setEntity(message, MediaType.APPLICATION_JSON);
				client.handle(request);
			}
		});
		

	}

	@Override
	public void mgetOneway(final Scope scope, final Object exchange) {
		System.out.println("This is mgetOneway one way of HTTP");

		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);

	}

	@Override
	public void xmgetOneway(final String source, final Scope scope, final Object exchange) {
		System.out.println("This is xmgetOneway one way of HTTP");

		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);
	}

	@Override
	public <T> T postTwowaySync(final String destination, final Scope scope, final List<Data<?>> datas,
			final long lease) {
		
		this.notifyStartEvent();
		Request request = null;

		switch (scope.getVerb()) {
		case GET:
			
			// Add in order to handle service for WP4 in Chorevolution Project
			if (scope.getName().equals("TrafficverketRoadcondition")
					|| scope.getName().equals("VasttrafikGenerateOAuth2token")
					|| scope.getName().equals("TrafficverketRoadcondition")
					|| scope.getName().equals("TrafficverketWeather")
					|| scope.getName().equals("VasttrafikrequestDepartureBoard")){

				request = CustomRestRequestBuilder.buildRestGetRequest(destination, scope, datas);
				
			} 
			// Default
			else {

				request = RestRequestBuilder.buildRestGetRequest(destination, scope, datas);
			}
			break;

		case POST:
			
			// Add in order to handle service for WP4 in Chorevolution Project
			if (scope.getName().equals("TrafficverketRoadcondition")
					|| scope.getName().equals("VasttrafikGenerateOAuth2token")
					|| scope.getName().equals("TrafficverketRoadcondition")
					|| scope.getName().equals("TrafficverketWeather")
					|| scope.getName().equals("VasttrafikrequestDepartureBoard")) {

				request = CustomRestRequestBuilder.buildRestPostRequest(destination, scope, datas);

			} 
			// Default
			else {

				request = RestRequestBuilder.buildRestPostRequest(destination, scope, datas);

			}
			
			break;

		default:

			printermsg = "Undefined method";
			logger.e(this.getClass().getName(), printermsg);
			break;
		}

		if(request.getMethod().equals(Method.GET)){
			
			printermsg = (request.toString());
			
		}else{
			
			printermsg = (request.getEntityAsText());
		}
		logger.i(this.getClass().getName() + "[request]", printermsg);
		
		Response response = this.client.handle(request);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printermsg = response.getStatus().toString();
		logger.i(this.getClass().getName() + "[response]", printermsg);
		
		this.notifyReleaseEvent();
		return (T) response.getEntityAsText();
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
			final Object exchange) {
		// TODO Auto-generated method stub
	}
	
	static String obj;

	public static class PrinterRestServerResource extends ServerResource {
		@Override
		protected Representation post(Representation entity) throws ResourceException {
			try {
				obj= entity.getText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return entity;
		}

		@Override
		protected Representation get() throws ResourceException{
			
			return new StringRepresentation(obj);
		}

	}

	public class RestletRestService extends Restlet {

		private ExecutorService executor = null;

		public RestletRestService() {

			executor = Executors.newFixedThreadPool(10);
		}

		@Override
		public void handle(Request request, Response response) {

			if (request.getMethod().equals(Method.POST)) {

				try {
					String receivedText = request.getEntity().getText().toString();

					processing(receivedText);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setAutoCommitting(false);
				final Response resp = response;
				response.setAutoCommitting(false);

				executor.execute(new Runnable() {

					@Override
					public void run() {

						resp.setStatus(Status.SUCCESS_OK);
						// resp.setEntity("OK Receive:)",
						// MediaType.TEXT_PLAIN);
						// resp.commit();
						return;
					}
				});
			}

		}

	}


	public void processing(String receivedText) {

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		String message_id = "";
		String message = "";
		message = receivedText;
		if(receivedText.split("-").length > 1){
			
			message_id = receivedText.split("-")[1];
			message = receivedText.split("-")[0];
		}
		
		try {
			jsonObject = (JSONObject) parser.parse(message);
			// agentMget.fire(""+System.currentTimeMillis()+"-"+message_id);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String op_name = (String) jsonObject.get("op_name");
		this.notifyStartEvent();
		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {
			if (en.getKey().equals(op_name)) {
				Operation op = en.getValue();
				List<Data<?>> datas = new ArrayList<>();

				for (Data<?> data : op.getGetDatas()) {
					Data d = new Data<String>(data.getName(), "String", true, (String) jsonObject.get(data.getName()),
							data.getContext(), data.getMediaType());
					datas.add(d);
					// System.err.println("Added " + d);
				}
				Data d = new Data<String>("op_name", "String", true, op_name, "BODY");
				datas.add(d);
				if(!message_id.equals("")){
					
					d = new Data<String>("message_id", "String", true, message_id, "BODY");
					datas.add(d);
				}
				
				if (op.getOperationType() == OperationType.TWO_WAY_SYNC) {

					String response = bcRestSubcomponent.mgetTwowaySync(op.getScope(), datas);

				} else if (op.getOperationType() == OperationType.ONE_WAY) {

					bcRestSubcomponent.mgetOneway(op.getScope(), datas);
				}
			}
		
		}
		

	}

	public class SendMessage extends Thread {

		String message = null;
		String scope = null;

		public SendMessage(String message) {

			this.message = message;
		}

		public void run() {

			Request request = new Request();
			request.setResourceRef(bcConfiguration.getServiceAddress());
			request.setMethod(Method.POST);
			request.setEntity(message, MediaType.APPLICATION_JSON);
			client.handle(request);
		}

	}

	@Override
	public void setGmServiceRepresentation(GmServiceRepresentation serviceRepresentation) {
		// TODO Auto-generated method stub

	}

	@Override
	public GmServiceRepresentation getGmServiceRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}
}

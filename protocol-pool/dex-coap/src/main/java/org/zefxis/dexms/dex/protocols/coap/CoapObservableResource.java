package org.zefxis.dexms.dex.protocols.coap;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.simple.JSONObject;
import org.zefxis.dexms.gmdl.utils.Data;

import fr.inria.mimove.monitor.agent.MeasureAgent;

public class CoapObservableResource extends CoapResource {

	private String resource = null;
	private volatile Type type = Type.NON;
	private BlockingQueue<List<Data<?>>> waitingQueue = null;
	private MeasureAgent agent = null;

	public CoapObservableResource(String name, boolean observable, BlockingQueue<List<Data<?>>> waitingQueue, MeasureAgent agent){
		
		super(name);
		// TODO Auto-generated constructor stub
		setObservable(observable);
		this.waitingQueue = waitingQueue;
		this.agent = agent;

	}

	public void resourceChange() {

		// String fileData = "/opt/artifacts/temp.data";
		boolean resourceChanged = false;
		 ;
		try {
			List<Data<?>> datas = waitingQueue.take();
			
			if(datas != null){
				
				
				
				JSONObject jsonObject = new JSONObject();
				
				for(Data<?> data : datas){
					
					
					jsonObject.put(data.getName(), String.valueOf(data.getObject()));
					
				}
				
				String message_id = (String) jsonObject.get("message_id");
				
				String datastream = jsonObject.toJSONString();
				
				if (this.resource != datastream) {

					resourceChanged = true;
					this.resource = datastream;
					
					agent.fire2(System.nanoTime(), message_id+"-timestamp_2-postOneway");
				}

				if (resourceChanged) {

					changed();
				}
				
			}
			
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void handleGET(CoapExchange exchange) {

		Response response = new Response(ResponseCode.CONTENT);
		response.setPayload(resource);
		response.setType(type);
		response.setDuplicate(false);
		exchange.respond(response);
	}

	@Override
	public void changed() {

		super.changed();
	}
}

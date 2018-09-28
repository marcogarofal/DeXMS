package eu.chorevolution.vsb.gm.protocols.mqtt;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.simple.JSONObject;

import eu.chorevolution.vsb.gmdl.utils.Data;
import eu.chorevolution.vsb.gmdl.utils.Scope;

public class MqttClientPost {

	private MqttClient client = null;
	private Scope scope = null;
	public MqttClientPost(MqttClient client){
		
		this.client = client;
	}
	
	public void postOneWayRequest(final String destination, final Scope scope, final List<Data<?>> datas){
		
		this.scope = scope;
		
		MqttMessage message = new MqttMessage();
		message.setQos(0);
		JSONObject jsonObject = new JSONObject();
		String message_id = "";
		for(Data data : datas){
			
			if(data.getName().equals("message_id")){
				
				message_id = String.valueOf(data.getObject());
			}else{
				
				jsonObject.put(data.getName(), String.valueOf(data.getObject()));	
			}
			
		}
    	String dataPost = jsonObject.toJSONString()+"-"+message_id;
		message.setPayload(dataPost.getBytes());
//		ThreadPublisher threadPublisher = new ThreadPublisher(client, message);
//		threadPublisher.start();
		try {
			
			client.publish(scope.getUri(), message);
//			agent.fire(""+System.currentTimeMillis()+"-"+message_id);
			
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void postTwoWaysRequest(){
		
		
	}
	
	public void stop(){
		
		
	try{
				
				client.disconnect();
				
			} catch (MqttException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class ThreadPublisher extends Thread{
		
		private MqttClient client = null;
		private MqttMessage message = null;
		
		public ThreadPublisher(MqttClient client, MqttMessage message){
			
			this.client = client;
			this.message = message;
		} 
		
		public void run(){
			
			try {
				
				client.publish(scope.getName(), message);
				
			} catch (MqttException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			this.interrupt();
			
		}
		
	}
}

 package org.zefxis.dexms.dex.protocols.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;


public class WebSocketObserverThread extends Thread {

	WebSocketObserver webSocketObserver = null;
	MediatorWebsocketSubcomponent bcWebsocketSubcomponent = null;
	
	private GmServiceRepresentation serviceRepresentation = null;
	
	public WebSocketObserverThread(WebSocketObserver webSocketObserver, MediatorWebsocketSubcomponent bcWebsocketSubcomponent,
			                       GmServiceRepresentation serviceRepresentation){
		
		this.webSocketObserver = webSocketObserver;
		this.bcWebsocketSubcomponent = bcWebsocketSubcomponent;
		this.serviceRepresentation = serviceRepresentation;
	}
	
	public void run(){
		
		webSocketObserver.connect();
		while(true){
			
			String message = null;
			try{
				
				message = webSocketObserver.msgQueue.take();
				if(!message.isEmpty()){
					

//					
					JSONParser parser = new JSONParser();
					JSONObject jsonObject = (JSONObject) parser.parse(message.trim());
				
					String op_name = (String) jsonObject.get("op_name");
					String message_id = (String) jsonObject.get("message_id");
					for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations()
							.entrySet()) {
						if (en.getKey().equals(op_name)){
							
							Operation op = en.getValue();
							List<Data<?>> datas = new ArrayList<>();

							for (Data<?> data : op.getGetDatas()) {
								Data d = new Data<String>(data.getName(), "String", true,
										(String) jsonObject.get(data.getName()), data.getContext(),data.getMediaType());
								datas.add(d);
							}
							Data d = new Data<String>("op_name", "String", true, op_name, "BODY");
							datas.add(d);
							String scope = op_name;
							if (op.getOperationType() == OperationType.TWO_WAY_SYNC) {
								
								
								String response = bcWebsocketSubcomponent.mgetTwowaySync(op.getScope(), datas);

							} else if (op.getOperationType() == OperationType.ONE_WAY) {
								
							
								bcWebsocketSubcomponent.mgetOneway(op.getScope(), datas);
							}
						}
					}
					
					
				}
				
				
			}catch (InterruptedException | ParseException e){
				
				e.printStackTrace();
			} 
			
		}
		
	}
}

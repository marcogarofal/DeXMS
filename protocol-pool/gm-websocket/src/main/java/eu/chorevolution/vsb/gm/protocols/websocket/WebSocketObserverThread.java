package eu.chorevolution.vsb.gm.protocols.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.chorevolution.vsb.gmdl.utils.Data;
import eu.chorevolution.vsb.gmdl.utils.GmServiceRepresentation;
import eu.chorevolution.vsb.gmdl.utils.Operation;
import eu.chorevolution.vsb.gmdl.utils.enums.OperationType;

public class WebSocketObserverThread extends Thread {

	WebSocketObserver webSocketObserver = null;
	BcWebsocketSubcomponent bcWebsocketSubcomponent = null;
	
//	private MeasureAgent agent = null;
	private GmServiceRepresentation serviceRepresentation = null;
	
	public WebSocketObserverThread(WebSocketObserver webSocketObserver, BcWebsocketSubcomponent bcWebsocketSubcomponent,
			                       GmServiceRepresentation serviceRepresentation){
		
		this.webSocketObserver = webSocketObserver;
		this.bcWebsocketSubcomponent = bcWebsocketSubcomponent;
//		this.agent = agent;
		this.serviceRepresentation = serviceRepresentation;
	}
	
	public void run(){
		
		webSocketObserver.connect();
		while(true){
			
			String msg = null;
			try{
				
				msg = webSocketObserver.msgQueue.take();
				if(!msg.isEmpty()){
					
					String message_id = msg.split("-")[1];
					String message = msg.split("-")[0];
//					agent.fire(""+System.currentTimeMillis()+"-"+message_id);
					JSONParser parser = new JSONParser();
					JSONObject jsonObject = (JSONObject) parser.parse(message);
				
					String op_name = (String) jsonObject.get("op_name");
					for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations()
							.entrySet()) {
						if (en.getKey().equals(op_name)){
							
							Operation op = en.getValue();
							List<Data<?>> datas = new ArrayList<>();

							for (Data<?> data : op.getGetDatas()) {
								Data d = new Data<String>(data.getName(), "String", true,
										(String) jsonObject.get(data.getName()), data.getContext(),data.getMediaType());
								datas.add(d);
//								System.err.println("Added " + d);
							}
							Data d = new Data<String>("op_name", "String", true, op_name, "BODY");
							datas.add(d);
							d = new Data<String>("message_id", "String", true,message_id, "BODY");
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

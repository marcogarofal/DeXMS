package org.zefxis.dexms.dex.protocols.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import org.json.simple.JSONObject;


import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;
import org.zefxis.dexms.tools.logger.GLog;
import org.zefxis.dexms.tools.logger.Logger;


public class MediatorWebsocketSubcomponent  extends MediatorGmSubcomponent{
	
	private WebSocketServer webSocketServer = null;
	private WebSocketObserver  webSocketObserver = null;
	SynchronousQueue<String> buffer = null;
	private GmServiceRepresentation serviceRepresentation = null;
	
	public MediatorWebsocketSubcomponent(MediatorConfiguration bcConfiguration,GmServiceRepresentation serviceRepresentation) {
		
		super(bcConfiguration);
		// TODO Auto-generated constructor stub
		
		
		this.serviceRepresentation = serviceRepresentation;
		buffer = new SynchronousQueue<String>();
		switch (this.bcConfiguration.getSubcomponentRole()){
		case SERVER:
			
			URI uri = null;
			try {
				
                uri = new URI("http://"+bcConfiguration.getSubcomponentAddress()+":"+bcConfiguration.getSubcomponentPort());
				webSocketObserver = new WebSocketObserver(uri);
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case CLIENT:   
			
//			webSocketServer = new WebSocketServer( new InetSocketAddress(bcConfiguration.getServicePort()));
			
			break;
		default:
			break;
		}
	}

	@Override
	public void start(){
		// TODO Auto-generated method stub
		switch (this.bcConfiguration.getSubcomponentRole()){
		case SERVER:
			
			WebSocketObserverThread Observerthread = new WebSocketObserverThread(webSocketObserver,this, serviceRepresentation);
//			WebSocketObserverThread Observerthread = new WebSocketObserverThread(webSocketObserver,this, serviceRepresentation, agentMget);
			Observerthread.start();
			break;
			
		case CLIENT:   
			webSocketServer.start();
			WebSocketPushNotification notifier = new WebSocketPushNotification(webSocketServer, this, buffer);
//			WebSocketPushNotification notifier = new WebSocketPushNotification(webSocketServer, buffer, agentPost);
			notifier.start();
			break;
		default:
			break;
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postOneway(String destination, Scope scope, List<Data<?>> data, long lease) {
		// TODO Auto-generated method stub
		
			
			JSONObject  jsonObject = new JSONObject();
			for(Data<?> d : data) {
				
				// jsonObject.put(d.getName(), d.getObject().toString());
				
				try {
				 // byte[] datainbyte = Base64.decodeBase64(d.getObject().toString());
					
			
				//  byte[] messageInByte = Base64.decode(d.getObject().toString());
					String message = d.getObject().toString();
					buffer.put(message);
				} catch (InterruptedException e){
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		
	}

	@Override
	public void mgetOneway(Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>)exchange, 0);
		
	}

	@Override
	public void xmgetOneway(String source, Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T postTwowaySync(String destination, Scope scope, List<Data<?>> datas, long lease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void xtgetTwowaySync(String destination, Scope scope, long timeout, Object response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T mgetTwowaySync(Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postTwowayAsync(String destination, Scope scope, List<Data<?>> data, long lease) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void xgetTwowayAsync(String destination, Scope scope, Object response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mgetTwowayAsync(Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postBackTwowayAsync(String source, Scope scope, Data<?> data, long lease, Object exchange) {
		// TODO Auto-generated method stub
		
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

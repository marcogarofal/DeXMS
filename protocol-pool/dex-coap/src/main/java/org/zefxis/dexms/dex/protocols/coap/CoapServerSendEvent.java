package org.zefxis.dexms.dex.protocols.coap;

import java.net.InetSocketAddress;
import java.util.LinkedList;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapServerSendEvent extends CoapServer {
    
    private static int LAT = 1; 
    private static int LON = 1; 
    private CoapServerSendEvent serveur = null;
    private LinkedList<String> buffer = null;
    
    public CoapServerSendEvent(LinkedList<String> buffer){
    	this.buffer = buffer;
    	add(new CoapPushDataResource());
    }
    
    public void addEndpoints(int COAP_PORT, String IP_ADDRESS){ 
    	
    	InetSocketAddress bindToAddress = new InetSocketAddress(IP_ADDRESS, COAP_PORT); 
        addEndpoint(new CoapEndpoint(bindToAddress)); 
      
    } 
      
    
    public void stop(){
    	
    	if(serveur != null){
    		
    		serveur.stop();
    	}
    }
    
    public class CoapPushDataResource extends CoapResource {

    	
    	public CoapPushDataResource(){ 
            
            // set resource identifier 
            super("bridgeNextClosure"); 
             
            // set display name 
            getAttributes().setTitle("bridgeNextClosure Resource"); 
        } 

        @Override 
        public void handleGET(CoapExchange exchange){ 
             
            while(true){
            	
            	// respond to the request 
            	synchronized(buffer){
            		
            		String content = buffer.poll();
            		if( content == null){
            			
            			try {
            				
            				buffer.wait();
            				
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
            		}else{
            			
                		exchange.respond(content);
                		long time = System.nanoTime();
                		System.err.println(" BcCoapSubcomponent postOneway message ["+content+"] at time "+time);
            		}
            		
            	}
            }
        } 
    }
		   
}


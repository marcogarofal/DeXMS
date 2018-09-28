package eu.chorevolution.vsb.gm.protocols.coap;


import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.coap.MediaTypeRegistry;


	public class SendCoapMessage extends Thread{
		
		String message = null;
		CoapClient client = null;
		
		public SendCoapMessage(CoapClient client , String message){
			
			this.message = message;
			this.client = client;
		}
		
		public void run(){
			
			client.post(message, MediaTypeRegistry.APPLICATION_JSON);
	        //client.shutdown();	
			destroy();
	       
		}
		
		public void destroy(){
			
			 currentThread().interrupt();
			 try {
				
				 currentThread().join(1000);
				
			} catch (InterruptedException e) {}
		}
		
	}


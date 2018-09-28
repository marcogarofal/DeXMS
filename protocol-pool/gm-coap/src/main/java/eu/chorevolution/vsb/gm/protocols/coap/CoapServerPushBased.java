package eu.chorevolution.vsb.gm.protocols.coap;

import java.net.InetSocketAddress;
import java.net.SocketException;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapServerPushBased {

	private static PushBasedResource pushBasedResource = null;
	private CoapServer coapServer = null;
	
	public CoapServerPushBased(String resourceName, String hostIp, int hostport){
		
		NetworkConfig config = NetworkConfig.getStandard().setInt(NetworkConfig.Keys.PROTOCOL_STAGE_THREAD_COUNT, 10)
                                                          .setString(NetworkConfig.Keys.DEDUPLICATOR, "NO_DEDUPLICATOR");
		coapServer = new CoapServer(config, hostport);
		try {
			
			coapServer.addEndpoint(addEndpoints(hostIp, hostport));
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pushBasedResource = new PushBasedResource(resourceName,true);
    	coapServer.add(pushBasedResource); 
        coapServer.start();
        
		
	}
	
	private CoapEndpoint addEndpoints(String hostIp, int hostport) throws SocketException { 
        InetSocketAddress bindToAddress = new InetSocketAddress(hostIp, hostport); 
     return new CoapEndpoint(bindToAddress); 
      
    } 
   
    
    public static void sendNotification(String message){
    	
    	
    	pushBasedResource.changed(message);
    }
    
    public void start(){
    	
    	coapServer.start();
    }
    
    public void stop(){
    	
    	coapServer.stop();
    }
    
    public class PushBasedResource extends CoapResource {
    	
    	private String message = null;
    	private volatile Type type = Type.NON;
    	public PushBasedResource(String name, boolean observable) {
    		super(name);
    		// TODO Auto-generated constructor stub
    		setObservable(observable);

    	}

    	@Override
    	public void handleGET(CoapExchange exchange) {

    		Response response = new Response(ResponseCode.CONTENT);
    		response.setPayload(message);
    		response.setType(type);
    		response.setDuplicate(false);
    		exchange.respond(response);
    	}

    	@Override
    	public void changed(){
    		
    		super.changed();
    	}

    	public void changed(String message){
    		
    		this.message = message;
    		changed();
    	}
    	
    }
}

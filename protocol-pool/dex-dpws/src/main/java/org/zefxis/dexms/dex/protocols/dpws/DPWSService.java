
package org.zefxis.dexms.dex.protocols.dpws;

import java.util.LinkedList;

import org.ws4d.java.communication.DPWSCommunicationManager;
import org.ws4d.java.communication.connection.ip.IPNetworkDetection;
import org.ws4d.java.communication.protocol.http.HTTPBinding;
import org.ws4d.java.service.DefaultService;
import org.ws4d.java.types.URI;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;



/**
 * This class was generated by the CHOReVOLUTION BindingComponent Generator using com.sun.codemodel 2.6
 * 
 */
public class DPWSService extends DefaultService
{
	
	private MediatorConfiguration bcConfiguration = null;
    private final static URI DOCU_EXAMPLE_SERVICE_ID = (new URI(DPWSDevice.DOCU_NAMESPACE + "/DPWSService"));
    
    public DPWSService(MediatorConfiguration bcConfiguration, LinkedList<String> buffer) {
        
    	super();
    	
        setServiceId(DOCU_EXAMPLE_SERVICE_ID);
        /* operation_1 operation_1;
        operation_1 = new operation_1(subcomponentRef);*/
        this.addBinding(new HTTPBinding(
        		
        		IPNetworkDetection.getInstance().getIPAddressOfAnyLocalInterface(bcConfiguration.getSubcomponentAddress(), false), 
        		5678, 
        		"DPWSService", 
        		DPWSCommunicationManager.COMMUNICATION_MANAGER_ID)
        		
        		);
       
//        DpwsEvent dpwsEvent = new DpwsEvent(buffer, agent);
        DpwsEvent dpwsEvent = new DpwsEvent(buffer);
        
        addEventSource(dpwsEvent);
        
//       DPWSOperation dpwsOperation = new DPWSOperation(subcomponentRef);
//       addOperation(dpwsOperation);
    }
    
    
}

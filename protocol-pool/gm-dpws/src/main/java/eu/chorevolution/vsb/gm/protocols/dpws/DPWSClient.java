package eu.chorevolution.vsb.gm.protocols.dpws;

import java.util.List;

import org.ws4d.java.JMEDSFramework;
import org.ws4d.java.client.DefaultClient;
import org.ws4d.java.client.SearchManager;
import org.ws4d.java.communication.CommunicationException;
import org.ws4d.java.communication.DPWSCommunicationManager;
import org.ws4d.java.communication.DPWSProtocolVersion;
import org.ws4d.java.configuration.DPWSProperties;
import org.ws4d.java.security.CredentialInfo;
import org.ws4d.java.service.Device;
import org.ws4d.java.service.InvocationException;
import org.ws4d.java.service.Operation;
import org.ws4d.java.service.parameter.ParameterValue;
import org.ws4d.java.service.parameter.ParameterValueManagement;
import org.ws4d.java.service.reference.DeviceReference;
import org.ws4d.java.service.reference.ServiceReference;
import org.ws4d.java.types.LocalizedString;
import org.ws4d.java.types.QName;
import org.ws4d.java.types.QNameSet;
import org.ws4d.java.types.SearchParameter;
import eu.chorevolution.vsb.gmdl.utils.Data;


public class DPWSClient extends DefaultClient {

	final static String	namespace	= DPWSDevice.DOCU_NAMESPACE;
	final static QName	service		= new QName("BasicServices", namespace);

	
	private static List<Data<?>> datas = null; 
	private String reply = null;
	private boolean POST_TWO_WAY = false;
	private static DPWSClient instanceClient = null;
	
	private DPWSClient(){}
	
	public static DPWSClient getInstanceClient(){
		
		if (instanceClient == null){ 	
			
			JMEDSFramework.start(null);
			instanceClient = new DPWSClient();
			DPWSProperties.getInstance().removeSupportedDPWSVersion(DPWSProtocolVersion.DPWS_VERSION_2006);
		}
		return instanceClient;
		
	}

	public void sendRequestOneWay(List<Data<?>> receivedDatas){
		
		
		datas = receivedDatas;
		searchService(getInstanceClient());
	}
	
	
	public String sendRequestTwoWays(List<Data<?>> receivedDatas){
		
		datas = receivedDatas;
		POST_TWO_WAY = true;
		searchService(getInstanceClient());
		return reply;
		
	}
	
	
	private void searchService(DPWSClient client){
		
		// Use discovery, define what you are searching for and start search
		SearchParameter search = new SearchParameter();
		search.setDeviceTypes(new QNameSet(new QName("DPWSDevice", namespace)), DPWSCommunicationManager.COMMUNICATION_MANAGER_ID);
		SearchManager.searchDevice(search, client, null);
		
		// search services
		search = new SearchParameter();
		search.setServiceTypes(new QNameSet(service), DPWSCommunicationManager.COMMUNICATION_MANAGER_ID);
		SearchManager.searchService(search, client, null);
	}
	
	public void serviceFound(ServiceReference servRef, SearchParameter search, String comManId){
		
		try {
			// get operation
			Operation op = servRef.getService().getOperation(service, "DpwsOperationFake", null, null);

			// create input value and set string values
			ParameterValue request = op.createInputValue();
			for(Data<?> data : datas) {
				if(data.getClassName().equals("String")){
					
					ParameterValueManagement.setString(request, data.getName(), (String)data.getObject());
				}
			}
			
			System.err.println("Invoking HelloWorldOp...");

			// invoke operation with prepared input
			ParameterValue result = op.invoke(request, CredentialInfo.EMPTY_CREDENTIAL_INFO);

			System.err.println("Finished invoking HelloWorldOp...");
			
			if(POST_TWO_WAY){
				
				// get string value from answer
				reply = ParameterValueManagement.getString(result, "reply");
				System.out.println(" reply "+reply);
			}
			
			
	} catch (CommunicationException e) {
		e.printStackTrace();
	} catch (InvocationException e) {
		e.printStackTrace();
	}
	}
	
	
	public void deviceFound(DeviceReference devRef, SearchParameter search, String comManId) {
		try {
			Device device = devRef.getDevice();
			System.err.println("Found DPWSDevice: " + device.getFriendlyName(LocalizedString.LANGUAGE_EN));
		} catch (CommunicationException e){
			e.printStackTrace();
		}
	}
}


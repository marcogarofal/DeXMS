package eu.chorevolution.vsb.gm.protocols.dpws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.ws4d.java.JMEDSFramework;
import org.ws4d.java.client.DefaultClient;
import org.ws4d.java.client.SearchManager;
import org.ws4d.java.communication.CommunicationException;
import org.ws4d.java.communication.DPWSCommunicationManager;
import org.ws4d.java.communication.DPWSProtocolVersion;
import org.ws4d.java.communication.connection.ip.IPNetworkDetection;
import org.ws4d.java.communication.protocol.http.HTTPBinding;
import org.ws4d.java.configuration.DPWSProperties;
import org.ws4d.java.eventing.ClientSubscription;
import org.ws4d.java.eventing.EventSource;
import org.ws4d.java.message.eventing.SubscriptionEndMessage;
import org.ws4d.java.security.CredentialInfo;
import org.ws4d.java.service.InvocationException;
import org.ws4d.java.service.parameter.ParameterValue;
import org.ws4d.java.service.parameter.ParameterValueManagement;
import org.ws4d.java.service.reference.ServiceReference;
import org.ws4d.java.structures.DataStructure;
import org.ws4d.java.types.QName;
import org.ws4d.java.types.QNameSet;
import org.ws4d.java.types.SearchParameter;
import org.ws4d.java.types.URI;

import eu.chorevolution.vsb.gmdl.utils.BcConfiguration;
import eu.chorevolution.vsb.gmdl.utils.Data;
import eu.chorevolution.vsb.gmdl.utils.GmServiceRepresentation;
import eu.chorevolution.vsb.gmdl.utils.Operation;
import eu.chorevolution.vsb.gmdl.utils.enums.OperationType;


public class DpwsObserver extends DefaultClient {


	private ClientSubscription  notificationSub = null;
	final static String	namespace	= DPWSDevice.DOCU_NAMESPACE;
	final static QName	service		= new QName("BasicServices", namespace);
	private static BcDPWSSubcomponent bcDPWSSubcomponent;
	private static GmServiceRepresentation serviceRepresentation;
	JSONParser parser = new JSONParser();
	JSONObject jsonObject = null;
	private static String op_name = null;
	private static  Operation op = null;
	private static BcConfiguration bcConfiguration = null;
	
	
	public static void run(BcConfiguration bcConfig, BcDPWSSubcomponent bcSubcomponent, GmServiceRepresentation serviceRep){
		
		bcConfiguration = bcConfig;
		bcDPWSSubcomponent = bcSubcomponent;
		serviceRepresentation = serviceRep;
		
		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {

			op_name = en.getKey();
			op = en.getValue();
		}
		// search services
		JMEDSFramework.start(null);
		DPWSProperties.getInstance().removeSupportedDPWSVersion(DPWSProtocolVersion.DPWS_VERSION_2006);
		
		DpwsObserver dpwsObserver = new DpwsObserver();
		SearchParameter search = new SearchParameter();
		search.setServiceTypes(new QNameSet(service), DPWSCommunicationManager.COMMUNICATION_MANAGER_ID);
		SearchManager.searchService(search, dpwsObserver, null);
	}

	public void serviceFound(ServiceReference servRef, SearchParameter search, String comManId) {
		try {
			
			//get event
			EventSource eventSource = servRef.getService().getEventSource(service, "DpwsEvent", null, null);
			if(eventSource != null){
				
				DataStructure bindings = new org.ws4d.java.structures.ArrayList();
				
				HTTPBinding binding = new HTTPBinding(
						
										IPNetworkDetection.getInstance().getIPAddressOfAnyLocalInterface(bcConfiguration.getSubcomponentAddress(), false),
										10236, 
										"/EventSink", 
										DPWSCommunicationManager.COMMUNICATION_MANAGER_ID);
				bindings.add(binding);
				notificationSub = eventSource.subscribe(this, 0, bindings, CredentialInfo.EMPTY_CREDENTIAL_INFO);	
			}

		} catch (CommunicationException | InvocationException | IOException e) {
			
			e.printStackTrace();
		}
	}

	public ParameterValue eventReceived(ClientSubscription clientSubscription, URI actionURI, ParameterValue parameterValue){
		
		if(clientSubscription.equals(notificationSub)){
			
			String eventText = ParameterValueManagement.getString(parameterValue, "name");
			String message_id = eventText.split("-")[1];
			String message = eventText.split("-")[0];
			parser = new JSONParser();
			try {
				jsonObject = (JSONObject) parser.parse(message);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String op_name_content = (String)jsonObject.get("op_name");
			if(op_name_content.equals(op_name)) {
				
				List<Data<?>> datas = new ArrayList<>();
				
				for(Data<?> data: op.getGetDatas()) {
					Data d = new Data<String>(data.getName(), "String", true, (String)jsonObject.get(data.getName()), data.getContext(), data.getMediaType());
					datas.add(d);
				}
				Data d = new Data<String>("message_id", "String", true, message_id, "BODY");
				datas.add(d);
				if(op.getOperationType() == OperationType.TWO_WAY_SYNC){
					
					bcDPWSSubcomponent.mgetTwowaySync(op.getScope(), datas);
					
				}
				else if(op.getOperationType() == OperationType.ONE_WAY){
					
					bcDPWSSubcomponent.mgetOneway(op.getScope(), datas);
				}
			}
		}
		return null;
		
	}
	
	public void subscriptionTimeoutReceived(ClientSubscription clientSubscription){
		
		subscriptionEndReceived(clientSubscription, SubscriptionEndMessage.WSE_STATUS_UNKNOWN);
		
	}
	
	public void subscriptionEndReceived(ClientSubscription clientSubscription, int reason){
		
		System.err.println(" Subscription ended ");
	}
	
	

}

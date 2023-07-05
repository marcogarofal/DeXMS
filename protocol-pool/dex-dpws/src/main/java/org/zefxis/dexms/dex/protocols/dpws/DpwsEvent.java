package org.zefxis.dexms.dex.protocols.dpws;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.ws4d.java.schema.Element;
import org.ws4d.java.schema.SchemaUtil;
import org.ws4d.java.security.CredentialInfo;
import org.ws4d.java.service.DefaultEventSource;
import org.ws4d.java.service.parameter.ParameterValue;
import org.ws4d.java.service.parameter.ParameterValueManagement;
import org.ws4d.java.types.QName;
import org.zefxis.dexms.dex.protocols.dpws.DPWSDevice;


public class DpwsEvent extends DefaultEventSource{
	
	public final static String	DOCU_NAMESPACE	= DPWSDevice.DOCU_NAMESPACE;
	private LinkedList<String> buffer = null;
//	private MeasureAgent agent = null;
	
	public DpwsEvent(LinkedList<String> buffer) {
		super("DpwsEvent", new QName("BasicServices", DOCU_NAMESPACE));
		// TODO Auto-generated constructor stub
		
//		this.agent = agent;
		this.buffer = buffer;
		Element name = new Element(new QName("name",DOCU_NAMESPACE), SchemaUtil.TYPE_STRING);
		setOutput(name);	
	}
	
	public void fireHelloWorldSimpleEvent(int eventCounter){
		
		synchronized (buffer) {
			
			String dataEvent = buffer.poll();
			System.out.println("  dataEvent "+dataEvent);
			if(dataEvent == null){
				try {
					
					buffer.wait();
					
				}catch (InterruptedException e){e.printStackTrace();}	
			}
			else{
				
				ParameterValue parameterValue = createOutputValue();
				ParameterValueManagement.setString(parameterValue, "name", dataEvent);
//				String message_id = agent.getMessageID(dataEvent);
//				agent.fire(""+System.currentTimeMillis()+"-"+message_id);
				fire(parameterValue, eventCounter++, CredentialInfo.EMPTY_CREDENTIAL_INFO);
				
			}
		}
				
	}
	
}

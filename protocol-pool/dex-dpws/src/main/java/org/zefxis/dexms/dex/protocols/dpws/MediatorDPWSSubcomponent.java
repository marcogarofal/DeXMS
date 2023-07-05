package org.zefxis.dexms.dex.protocols.dpws;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.ws4d.java.JMEDSFramework;
import org.ws4d.java.eventing.EventSource;
import org.ws4d.java.types.QName;

import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.dex.protocols.generators.MediatorSubcomponentGenerator;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;
import org.zefxis.dexms.tools.logger.GLog;
import org.zefxis.dexms.tools.logger.Logger;

public class MediatorDPWSSubcomponent extends MediatorGmSubcomponent {

	private DPWSDevice device = null;
    private DpwsObserver dpwsObserver = null;
    private DPWSService service = null;
    private GmServiceRepresentation serviceRepresentation = null;
    private LinkedList<String> buffer = null;
    private DpwsEventProvider eventProvider = null;
    private MediatorConfiguration bcConfiguration = null;
    
	public MediatorDPWSSubcomponent(MediatorConfiguration bcConfiguration, GmServiceRepresentation serviceRepresentation) {
		
		super(bcConfiguration);
		buffer = new LinkedList<String>();
		this.serviceRepresentation = serviceRepresentation;
		this.bcConfiguration = bcConfiguration;
		
		switch (this.bcConfiguration.getSubcomponentRole()){
		case SERVER:
			
//			JMEDSFramework.start(null);
//			device = new DPWSDevice();
//			final DPWSService service = new DPWSService(this);
//			device.addService(service);
//			agentGet = new MeasureAgent("timestamp_4",System.currentTimeMillis(),MonitorConstant.M4,MonitorConstant.timestamp_4_port_listener);
//			dpwsObserver = new DpwsObserver(this.bcConfiguration);
			
			break;
		case CLIENT :   
				
//				client = DPWSClient.getInstanceClient();
//			agentPost = new MeasureAgent("timestamp_3",System.currentTimeMillis(),MonitorConstant.M2,MonitorConstant.timestamp_3_port_listener);
			
			JMEDSFramework.start(null);
			buffer = new LinkedList<String>();
//			service = new DPWSService(this.bcConfiguration,buffer, agentPost);
			service = new DPWSService(this.bcConfiguration,buffer);
			device = new DPWSDevice();
			EventSource eventSource = service.getEventSource(new QName("BasicServices", DPWSDevice.DOCU_NAMESPACE), "DpwsEvent", null, "DpwsEvent");
			eventProvider = new  DpwsEventProvider((DpwsEvent)eventSource);
			eventProvider.start();
			device.addService(service);
			
			break;
		default:
			break;
		}
	}

	@Override
	public void start() {
		switch (this.bcConfiguration.getSubcomponentRole()) {
		case SERVER:
			
//			try {
//				device.start();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			DpwsObserver.run(this.bcConfiguration, this, serviceRepresentation);
			break;
		case CLIENT:   
			
			try {
				
				device.start();
				
			} catch (IOException e){
				
				e.printStackTrace();
			}
				
			break;
		default:
			break;
		}
	}

	@Override
	public void stop() {
		switch (this.bcConfiguration.getSubcomponentRole()) {
		case SERVER:
			try {
				device.stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case CLIENT:   
			break;
		default:
			break;
		}
	}

	@Override
	public void postOneway(final String destination, final Scope scope, final List<Data<?>> datas, final long lease) {
			
				 synchronized(buffer) {
					
		        	String message_id = "";
		        	JSONObject jsonObject = new JSONObject();
					for(Data<?> data : datas){
						if(data.getName().equals("message_id")){
							
							message_id = (String)data.getObject();
						}else{
							
							jsonObject.put(data.getName(), (String)data.getObject());
						}
						
					}
					buffer.add(jsonObject.toJSONString()+"-"+message_id);
					buffer.notifyAll();
					
				}
	}

	@Override
	public void mgetOneway(final Scope scope, final Object exchange) {
		String message_id = "";
		for(Data<?> data : (List<Data<?>>)exchange){
			
			if(data.getName().equals("message_id")){
				
				message_id = (String)data.getObject();
			}

		}
		
//		agentGet.fire(""+System.currentTimeMillis()+"-"+message_id);
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>)exchange, 0);
		
	}

	@Override
	public void xmgetOneway(final String source, final Scope scope, final Object exchange) {
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>)exchange, 0);
	}

	@Override
	public <T> T postTwowaySync(final String destination, final Scope scope, final List<Data<?>> datas, final long lease) {
		return null;
	}

	@Override
	public void xtgetTwowaySync(final String destination, final Scope scope, final long timeout, final Object response) {
		// TODO Auto-generated method stub
	}

	@Override
	public <T> T mgetTwowaySync(final Scope scope, final Object exchange) {
		
		return this.nextComponent.postTwowaySync(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>)exchange, 0);
		
	}

	@Override
	public void postTwowayAsync(final String destination, final Scope scope, final List<Data<?>> data, final long lease) {
		// TODO Auto-generated method stub
	}

	@Override
	public void xgetTwowayAsync(final String destination, final Scope scope, final Object response) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mgetTwowayAsync(final Scope scope, final Object exchange) {
		
		this.nextComponent.postTwowayAsync(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>)exchange, 0);
	}

	@Override
	public void postBackTwowayAsync(final String source, final Scope scope, final Data<?> data, final long lease, final Object exchange) {
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

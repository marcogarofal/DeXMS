package org.zefxis.dexms.dex.protocols.mqtt;

import java.net.InetAddress;
import java.util.List;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.simple.JSONObject;

import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.tools.logger.GLog;
import org.zefxis.dexms.gmdl.utils.Data;

public class MediatorMQTTSubcomponent extends MediatorGmSubcomponent{

	MqttBroker broker = null;
	MqttClient serverSubscriber = null;
	MqttClient serverPublisher = null;
	private MqttClient client = null;
	GmServiceRepresentation serviceRepresentation = null;

	
	public MediatorMQTTSubcomponent(MediatorConfiguration bcConfiguration,
			GmServiceRepresentation serviceRepresentation) {
		super(bcConfiguration);

	
		this.serviceRepresentation = serviceRepresentation;
		System.out.println("MQTT "+ this.bcConfiguration.getSubcomponentRole()+" tcp://" + this.bcConfiguration.getSubcomponentAddress() + ":"
				+ this.bcConfiguration.getSubcomponentPort());
		switch (this.bcConfiguration.getSubcomponentRole()) {
		case SERVER:

			broker = new MqttBroker(this.bcConfiguration.getSubcomponentAddress(),
					this.bcConfiguration.getSubcomponentPort());
			try {
				serverSubscriber = new MqttClient("tcp://" + this.bcConfiguration.getSubcomponentAddress() + ":"
						+ this.bcConfiguration.getSubcomponentPort(), "serverSubscriber");
			} catch (MqttException e1) {
				e1.printStackTrace();
			}
			serverSubscriber.setCallback(new MediatorMQTTSubscriberCallback(this, serviceRepresentation));
			try {
				serverPublisher = new MqttClient("tcp://" + this.bcConfiguration.getSubcomponentAddress() + ":"
						+ this.bcConfiguration.getSubcomponentPort(), "serverPublisher");
			} catch (MqttException e1) {
				e1.printStackTrace();
			}
			break;
		case CLIENT:

			try {

				client = new MqttClient("tcp://" + this.bcConfiguration.getServiceAddress() + ":"
						+ this.bcConfiguration.getServicePort(), "client");

			} catch (MqttException e) {

				e.printStackTrace();
			}
			client.setCallback(new MediatorMQTTSubscriberCallback(this, serviceRepresentation));
			
			break;
		default:
			break;
		}

	}

	@Override
	public void start() {
		switch (this.bcConfiguration.getSubcomponentRole()) {

		case SERVER:

			try {

				InetAddress ip = InetAddress.getLocalHost();
				if (ip.getHostAddress().equals(this.bcConfiguration.getSubcomponentAddress().toString())) {

					broker.start();
				}

			} catch (Exception e1) {

				e1.printStackTrace();
			}

			try {

				MqttConnectOptions options = new MqttConnectOptions();
				options.setCleanSession(false);
				serverSubscriber.connect(options);

			} catch (MqttException e) {

				e.printStackTrace();
			}

			try {

				MqttConnectOptions options = new MqttConnectOptions();
				options.setCleanSession(false);
				serverPublisher.connect(options);

			} catch (MqttException e) {
				e.printStackTrace();
			}

			for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations()
					.entrySet()) {
				try {
					serverSubscriber.subscribe((String) en.getKey());
				} catch (MqttException e) {
					e.printStackTrace();
				}
				GLog.log.i("Info", "Server subscriber subscribed to " + (String) en.getKey());
			}
			break;
		case CLIENT:
			
			System.out.println(" Starting client");
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(false);

			try {

				client.connect(options);
				System.out.println(" ServiceRepresentation  "+serviceRepresentation.getInterfaces().get(0).getOperations()
						.entrySet().size());
				
				for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations()
						.entrySet()) {
					try {
						
						
						client.subscribe((String) en.getKey());
						if(client.isConnected()){
							
							System.out.println(client.getClientId()+"subribes topic "+(String) en.getKey());
							
						}else{
							
							System.out.println("Not connected");
						}
					} catch (MqttException e) {
						e.printStackTrace();
					}
					
				}

			} catch (MqttException e) {

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
				serverSubscriber.disconnect();
				serverSubscriber.close();
				serverPublisher.disconnect();
				serverPublisher.close();
				broker.stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case CLIENT:
			try {
				client.close();
			} catch (MqttException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void postOneway(final String destination, final Scope scope, final List<Data<?>> datas, final long lease) {
		// TODO Auto-generated method stub
		
	
		MqttMessage message = new MqttMessage();
		message.setQos(0);
		JSONObject jsonObject = new JSONObject();
		for (Data data : datas){

			jsonObject.put(data.getName(), String.valueOf(data.getObject()));

		}

		message.setPayload(jsonObject.toJSONString().getBytes());
		try {

			client.publish(scope.getUri(), message);

		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void mgetOneway(final Scope scope, final Object exchange) {

		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);

	}

	@Override
	public void xmgetOneway(final String source, final Scope scope, final Object exchange) {
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);
	}

	@Override
	public <T> T postTwowaySync(final String destination, final Scope scope, final List<Data<?>> datas,
			final long lease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void xtgetTwowaySync(final String destination, final Scope scope, final long timeout,
			final Object response) {
		// TODO Auto-generated method stub
	}

	@Override
	public <T> T mgetTwowaySync(final Scope scope, final Object exchange) {
		return this.nextComponent.postTwowaySync(this.bcConfiguration.getServiceAddress(), scope,
				(List<Data<?>>) exchange, 0);
	}

	@Override
	public void postTwowayAsync(final String destination, final Scope scope, final List<Data<?>> data,
			final long lease) {
		// TODO Auto-generated method stub
	}

	@Override
	public void xgetTwowayAsync(final String destination, final Scope scope, final Object response) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mgetTwowayAsync(final Scope scope, final Object exchange) {
		this.nextComponent.postTwowayAsync(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange,
				0);
	}

	@Override
	public void postBackTwowayAsync(final String source, final Scope scope, final Data<?> data, final long lease,
			final Object exchange) {
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
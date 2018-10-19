package org.zefxis.dexms.dex.protocols.mqtt;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.dex.protocols.generators.MediatorSubcomponentGenerator;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



public class MediatorMQTTSubcomponent extends MediatorGmSubcomponent {

	MqttBroker broker = null;
	MqttClient serverSubscriber = null;
	MqttClient serverPublisher = null;
	private MqttClient client = null;
	MqttClientPost mqttClientPost = null;
	GmServiceRepresentation serviceRepresentation = null;

	// MeasureAgent agent = null;
	public MediatorMQTTSubcomponent(MediatorConfiguration bcConfiguration, GmServiceRepresentation serviceRepresentation) {
		super(bcConfiguration);
		this.serviceRepresentation = serviceRepresentation;
		System.out.println("tcp://" + this.bcConfiguration.getSubcomponentAddress() + ":"
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
			serverSubscriber.setCallback(new ServerSubscriberCallback());
			try {
				serverPublisher = new MqttClient("tcp://" + this.bcConfiguration.getSubcomponentAddress() + ":"
						+ this.bcConfiguration.getSubcomponentPort(), "serverPublisher");
			} catch (MqttException e1) {
				e1.printStackTrace();
			}
			break;
		case CLIENT:

			// agent = new MeasureAgent("timestamp_5",
			// System.currentTimeMillis(),
			// MonitorConstant.M4,MonitorConstant.timestamp_5_port_listener);
			try {

				client = new MqttClient("tcp://" + this.bcConfiguration.getServiceAddress() + ":"
						+ this.bcConfiguration.getServicePort(), "client");
				System.out.println(" URL is  " + this.bcConfiguration.getServiceAddress() + ":"
						+ this.bcConfiguration.getServicePort());
			} catch (MqttException e) {

				e.printStackTrace();
			}
			client.setCallback(new SubscriberCallback());
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
				System.out.println("Server subscriber subscribed to " + (String) en.getKey());
			}
			break;
		case CLIENT:
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(false);

			try {

				client.connect(options);
				mqttClientPost = new MqttClientPost(client);

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
	public void postOneway(final String destination, final Scope scope, final List<Data<?>> data, final long lease) {
		// TODO Auto-generated method stub
		// mqttClientPost.postOneWayRequest(destination, scope, data, agent);
		mqttClientPost.postOneWayRequest(destination, scope, data);
		

	}

	@Override
	public void mgetOneway(final Scope scope, final Object exchange) {

		// long time = System.currentTimeMillis();
		// System.out.println(" mgetOnewayBcMQTTSubcomponent time "+time);
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

	private final class SubscriberCallback implements MqttCallback {
		public SubscriberCallback() {
			super();
		}

		@Override
		public void messageArrived(String topic, MqttMessage msg) throws Exception {
			// System.out.println(topic + " " + msg.toString());
			// mgetOneway(topic, msg);
		}

		@Override
		public void connectionLost(Throwable arg0) {
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken arg0) {
		}
	}

	private final class ServerSubscriberCallback implements MqttCallback {

		public ServerSubscriberCallback() {
			super();
		}

		@Override
		public void messageArrived(String topic, MqttMessage msg) throws Exception {
			
			
			String receivedText = Base64.encode(msg.getPayload());
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;

			try {

				jsonObject = (JSONObject) parser.parse(receivedText);

			} catch (ParseException e) {

				for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations()
						.entrySet()) {
					if (en.getKey().equals(topic)) {
						Operation op = en.getValue();
						List<Data<?>> datas = new ArrayList<>();

						for (Data<?> data : op.getGetDatas()) {

							jsonObject = new JSONObject();
							jsonObject.put(data.getName(), receivedText);

						}

					}

				}

			}

			for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations()
					.entrySet()) {
				if (en.getKey().equals(topic)) {
					Operation op = en.getValue();
					List<Data<?>> datas = new ArrayList<>();

					for (Data<?> data : op.getGetDatas()) {

						// Data d = new Data<String>(data.getName(), "String",
						// true, receivedText, data.getContext(),
						// data.getContext());

						Data d = new Data<String>(data.getName(), "String", true,
								(String) jsonObject.get(data.getName()), data.getContext(), data.getMediaType());

						datas.add(d);
						// System.err.println("Added " + d);
					}
					if (op.getOperationType() == OperationType.TWO_WAY_SYNC) {
						String response = mgetTwowaySync(op.getScope(), datas);
						serverPublisher.publish(topic + "Reply", response.getBytes(), 2, false);
					} else if (op.getOperationType() == OperationType.ONE_WAY) {
						mgetOneway(op.getScope(), datas);
					}
				}
			}
		}

		@Override
		public void connectionLost(Throwable arg0) {

		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken arg0) {

		}

	}

	private static String byteArrayToString(byte[] array) {

		String string = null;
		try {

			string = new String(array, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
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
package org.zefxis.dexms.dex.protocols.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;


public class MediatorMQTTSubscriberCallback implements MqttCallback {

	private MediatorMQTTSubcomponent mediatorMQTTSubComponent = null;
	private GmServiceRepresentation serviceRepresentation = null;

	public MediatorMQTTSubscriberCallback(MediatorMQTTSubcomponent mediatorMQTTSubComponent,
			GmServiceRepresentation serviceRepresentation) {
		super();

		this.mediatorMQTTSubComponent = mediatorMQTTSubComponent;
		this.serviceRepresentation = serviceRepresentation;
	}

	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {

		
		String receivedText = new String(msg.getPayload());
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {

			jsonObject = (JSONObject) parser.parse(receivedText);

		} catch (ParseException e) {

			System.err.println(e.getMessage());
		}

		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {
			if (en.getKey().equals(topic)) {
				Operation op = en.getValue();
				List<Data<?>> datas = new ArrayList<>();

				for (Data<?> data : op.getGetDatas()) {

					Data d = new Data<String>(data.getName(), "String", true, (String) jsonObject.get(data.getName()),
							data.getContext(), data.getMediaType());

					datas.add(d);
				}
				if (op.getOperationType() == OperationType.TWO_WAY_SYNC) {

					String response = mediatorMQTTSubComponent.mgetTwowaySync(op.getScope(), datas);
					mediatorMQTTSubComponent.serverPublisher.publish(topic + "Reply", response.getBytes(), 2, false);

				} else if (op.getOperationType() == OperationType.ONE_WAY) {

					mediatorMQTTSubComponent.mgetOneway(op.getScope(), datas);
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

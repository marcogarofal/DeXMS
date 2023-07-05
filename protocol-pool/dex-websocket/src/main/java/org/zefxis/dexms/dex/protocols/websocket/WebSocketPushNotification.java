package org.zefxis.dexms.dex.protocols.websocket;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.json.simple.JSONObject;
import org.zefxis.dexms.gmdl.utils.Data;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class WebSocketPushNotification extends Thread {

	WebSocketServer webSocketServer = null;
	BlockingQueue<List<Data<?>>> waitingQueue = null;
	BlockingQueue<byte[]> bufferInByte = null;
	private boolean isbyte = false;
	MediatorWebsocketSubcomponent bcWebsocketSubcomponent = null;
	// MeasureAgent agent = null;

	public WebSocketPushNotification(WebSocketServer webSocketServer,
			MediatorWebsocketSubcomponent bcWebsocketSubcomponent, BlockingQueue<List<Data<?>>> waitingQueue) {

		this.webSocketServer = webSocketServer;
		this.waitingQueue = waitingQueue;
		isbyte = false;
		this.bcWebsocketSubcomponent = bcWebsocketSubcomponent;

	}

	public void run() {

		while (true) {

			List<Data<?>> datas = null;
			try {
				datas = waitingQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (datas != null) {

				JSONObject jsonObject = new JSONObject();

				for (Data<?> data : datas) {

					jsonObject.put(data.getName(), String.valueOf(data.getObject()));

				}
				String message = jsonObject.toJSONString();

				if (message != null) {

					bcWebsocketSubcomponent.notifyStartEvent();
					SendMessage send = new SendMessage(message);
					send.start();
					bcWebsocketSubcomponent.notifyReleaseEvent();

				}

			}

		}
	}

	public class SendMessage extends Thread {

		String message = null;
		byte[] messageInByte = null;
		boolean inbyte = false;

		public SendMessage(String message) {

			this.message = message;
			inbyte = false;
		}

		public void run() {

			webSocketServer.send(message);
			SendMessage.currentThread().interrupt();
		}

	}
}

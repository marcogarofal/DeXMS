package org.zefxis.dexms.dex.protocols.websocket;

import java.util.concurrent.SynchronousQueue;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
public class WebSocketPushNotification extends Thread {

	WebSocketServer webSocketServer = null;
	SynchronousQueue<String> buffer = null;
	SynchronousQueue<byte[]> bufferInByte = null;
	private boolean isbyte = false;
	MediatorWebsocketSubcomponent bcWebsocketSubcomponent = null;
	// MeasureAgent agent = null;

	public WebSocketPushNotification(WebSocketServer webSocketServer, MediatorWebsocketSubcomponent bcWebsocketSubcomponent,
			SynchronousQueue<String> buffer) {

		this.webSocketServer = webSocketServer;
		this.buffer = buffer;
		isbyte = false;
		this.bcWebsocketSubcomponent = bcWebsocketSubcomponent;

	}

	public void webSocketPushNotification(WebSocketServer webSocketServer,
			MediatorWebsocketSubcomponent bcWebsocketSubcomponent, SynchronousQueue<byte[]> buffer) {

		this.webSocketServer = webSocketServer;
		this.bufferInByte = buffer;
		isbyte = true;
		this.bcWebsocketSubcomponent = bcWebsocketSubcomponent;

	}

	public void run() {

		while (true) {

			if (isbyte) {

				byte[] message = bufferInByte.poll();
				if (message != null) {

					bcWebsocketSubcomponent.notifyStartEvent();
					SendMessage send = new SendMessage(message);
					send.start();
					bcWebsocketSubcomponent.notifyReleaseEvent();

				}
			} else {

				String message = buffer.poll();
				if (message != null) {

					bcWebsocketSubcomponent.notifyStartEvent();
					byte[] bytearray = Base64.decode(message);
					SendMessage send = new SendMessage(bytearray);
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

		public SendMessage(byte[] message) {

			this.messageInByte = message;
			inbyte = true;
		}

		public void run() {

			if (inbyte) {

				webSocketServer.send(messageInByte);
			} else {

				webSocketServer.send(message);
			}

			SendMessage.currentThread().interrupt();
		}

	}
}

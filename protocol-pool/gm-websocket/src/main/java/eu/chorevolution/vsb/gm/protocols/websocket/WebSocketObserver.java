package eu.chorevolution.vsb.gm.protocols.websocket;

import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.java_websocket.handshake.ServerHandshake;


	public class WebSocketObserver extends org.java_websocket.client.WebSocketClient implements Runnable {

		public BlockingQueue<String> msgQueue;
		
		public WebSocketObserver(URI serverURI) {
			super(serverURI);
			msgQueue = new LinkedBlockingQueue<String>();
			
		}

		@Override
		public void onOpen(ServerHandshake handshakedata) {
			System.out.println("Client opens a stream");
		}

		@Override
		public void onMessage(String message) {
			
			msgQueue.add(message);
			message += " at " + System.currentTimeMillis();
			//System.out.println("Client receives : " + message);
			
		}

		@Override
		public void onClose(int code, String reason, boolean remote) {
			
			System.err.println("stream closed");
		}

		@Override
		public void onError(Exception ex) {
			
			System.err.println("an error occured " + ex.getStackTrace() + " " + ex.getMessage());
		}
		

	}

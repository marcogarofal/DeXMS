package org.zefxis.dexms.dex.protocols.coap;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;


public class CoapObservableServer extends Thread {

	private static CoapObservableResource coapObservableResource = null;
	private CoapServer coapServer = null;
	private BlockingQueue<String> waitingQueue = null;
	private String resourceName = null;
	private String hostIp = null;
	private int hostport = 0;
	private NetworkConfig config = null;

	public CoapObservableServer(GmServiceRepresentation serviceRepresentation, String hostIp, int hostport,
			BlockingQueue<String> waitingQueue) {

		this.waitingQueue = waitingQueue;
		this.hostIp = hostIp;
		this.hostport = hostport;

		this.config = NetworkConfig.getStandard().setInt(NetworkConfig.Keys.PROTOCOL_STAGE_THREAD_COUNT, 100);

		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {

			this.resourceName = en.getKey();
		}

		

	}

	private CoapEndpoint addEndpoints(String hostIp, int hostport) throws SocketException {
		InetSocketAddress bindToAddress = new InetSocketAddress(hostIp, hostport);
		return new CoapEndpoint(bindToAddress);

	}

	public void run() {

		coapServer = new CoapServer(config, hostport);

		try {
			coapServer.addEndpoint(addEndpoints(hostIp, hostport));
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}

		coapObservableResource = new CoapObservableResource(resourceName, true, waitingQueue);
		coapServer.add(coapObservableResource);

		coapServer.start();

		while (true) {

			coapObservableResource.resourceChange();
			try {
				
				Thread.sleep(2000);
			} catch(Exception e) {}
			
			
		}

	}

}

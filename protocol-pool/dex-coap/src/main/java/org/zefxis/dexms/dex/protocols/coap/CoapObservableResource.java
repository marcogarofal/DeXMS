package org.zefxis.dexms.dex.protocols.coap;

import java.util.concurrent.BlockingQueue;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapObservableResource extends CoapResource {

	private String resource = null;
	private volatile Type type = Type.NON;
	private BlockingQueue<String> waitingQueue = null;

	public CoapObservableResource(String name, boolean observable, BlockingQueue<String> waitingQueue) {

		super(name);
		// TODO Auto-generated constructor stub
		setObserveType(type);
		setObservable(observable);
		this.waitingQueue = waitingQueue;

	}

	public void resourceChange() {

		boolean resourceChanged = false;
		try {
			String datastream = waitingQueue.take();

			if (datastream != null) {

				if (this.resource != datastream) {

					resourceChanged = true;
					this.resource = datastream;

				}

				if (resourceChanged) {

					changed();
				}

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void handleGET(CoapExchange exchange) {

		Response response = new Response(ResponseCode.CONTENT);
		response.setPayload(resource);
		response.setType(type);
		response.setDuplicate(false);
		exchange.respond(response);

	}

	@Override
	public void changed() {

		super.changed();
	}

}

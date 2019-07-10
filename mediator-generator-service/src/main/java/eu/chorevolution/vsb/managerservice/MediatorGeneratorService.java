package eu.chorevolution.vsb.managerservice;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class MediatorGeneratorService extends Application {

	public MediatorGeneratorService() {
	}

	@Override
	public synchronized Restlet createInboundRoot() {

		// Create a router Restlet that routes each call to a

		Router router = new Router(getContext());
		// Defines only one route with a new instance of MediatorGeneratorResource.
		router.attach("/MediatorGenerator", MediatorGeneratorResource.class);
		return router;
	}

}
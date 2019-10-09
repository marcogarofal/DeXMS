package org.zefxis.dexms.examples;

import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.mediator.generator.MediatorGenerator;

public class I3Services {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		MediatorGenerator mediator = new MediatorGenerator();
		mediator.setServiceEndpoint("data.lacity.org", "80");
		mediator.setBusEndpoint("3.17.183.219", "8000");
		mediator.generate("/home/pntumba/inria_code/I3/DeX-IDL/laxparking.gidl", ProtocolType.MQTT, "laxparking");

		

	}

}

package org.zefxis.dexms.mediator.generator.test;

import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.mediator.generator.MediatorGenerator;
import org.zefxis.dexms.mediator.manager.MediatorOutput;



public class MediatorGeneratorTestGidlFiles{
	
	public static void main(String[] args){
		
//		String interfaceDescriptionPath  = "/home/pntumba/inria_code/repositories/WP4/GOOGLE.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcNews/model/news-service-name.gidl";
//		String interfaceDescriptionPath  = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcPws/model/pws-service-name.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcParking/model/parking-service-name.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcPoi/model/poi-service-name.gidl";
		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/models/journeyplanner.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcTraffic/model/traffic-service-name.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcPublicTransportation/model/publictransportation-service-name.gidl";
		
		MediatorGenerator vsbm = new MediatorGenerator();
		MediatorOutput  vsbOutput = vsbm.generateWar(interfaceDescriptionPath,ProtocolType.SOAP, "journeyplanner-service-name");
		
		
	}	
}
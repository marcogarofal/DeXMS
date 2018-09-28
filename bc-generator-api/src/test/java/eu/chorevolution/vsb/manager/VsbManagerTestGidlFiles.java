package eu.chorevolution.vsb.manager;

import eu.chorevolution.vsb.bc.manager.VsbOutput;
import eu.chorevolution.vsb.gmdl.utils.enums.ProtocolType;
import eu.chorevolution.vsb.manager.api.VsbManager;

public class VsbManagerTestGidlFiles{
	
	public static void main(String[] args){
		
//		String interfaceDescriptionPath  = "/home/pntumba/inria_code/repositories/WP4/GOOGLE.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcNews/model/news-service-name.gidl";
//		String interfaceDescriptionPath  = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcPws/model/pws-service-name.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcParking/model/parking-service-name.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcPoi/model/poi-service-name.gidl";
		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/models/journeyplanner.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcTraffic/model/traffic-service-name.gidl";
//		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/smart-mobility-tourism/bindingcomponents/without_adaptation/bcPublicTransportation/model/publictransportation-service-name.gidl";
		
		VsbManager vsbm = new VsbManager();
		VsbOutput  vsbOutput = vsbm.generateWar(interfaceDescriptionPath,ProtocolType.SOAP, "journeyplanner-service-name");
		
		
	}	
}
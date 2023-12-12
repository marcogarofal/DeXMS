package org.zefxis.dexms.examples;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.mediator.generator.MediatorGenerator;
import org.zefxis.dexms.mediator.manager.MediatorOutput;


public class I3Services {
		public static void main(String[] args) {
			// TODO Auto-generated method stub
						
			MediatorGenerator mediator = new MediatorGenerator();

			// Corresponds to the IP address and port number of the MQTT broker
			mediator.setServiceEndpoint("127.0.0.1", "6000");

			// Corresponds to the IP address and port number of the CoAP endpoint
			mediator.setBusEndpoint("127.0.0.1", "1883");

			String gidlFile = "src/main/java/org/zefxis/dexms/examples/randomValue.gidl";
			MediatorOutput output = mediator.generate(gidlFile, ProtocolType.MQTT, "COAPS_TO_MQTT");
			try {
				FileUtils.writeByteArrayToFile(new File("COAPS_TO_MQTT.jar"), output.jar);
			} 
			catch (IOException e) {e.printStackTrace();}
			
		
			
		}
	}
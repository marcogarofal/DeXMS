package org.zefxis.dexms.examples;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.mediator.generator.MediatorGenerator;
import org.zefxis.dexms.mediator.manager.MediatorOutput;
import org.zefxis.dexms.dex.protocols.rest.MediatorRestSubcomponent;

public class I3Services {
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			MediatorGenerator mediator = new MediatorGenerator();

			// Corresponds to the IP address and port number of the MQTT broker
			mediator.setServiceEndpoint("10.211.55.91", "1883");

			// Corresponds to the IP address and port number of the CoAP endpoint
			mediator.setBusEndpoint("10.211.55.105", "8893");

			String gidlFile = "src/main/java/org/zefxis/dexms/examples/randomValue.gidl";
			MediatorOutput output = mediator.generate(gidlFile, ProtocolType.REST, "MQTT_to_REST");
			try {
				FileUtils.writeByteArrayToFile(new File("MQTT_to_REST.jar"), output.jar);
			} 
			catch (IOException e) {e.printStackTrace();}
			
		}
	}
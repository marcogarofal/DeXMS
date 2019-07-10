package org.zefxis.dexms.mediator.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.zefxis.dexms.mediator.generator.client.MediatorManagerClient;
import org.zefxis.dexms.mediator.manager.MediatorOutput;



public class MediatorGeneratorRestServiceClient{

	
public static void main(String[] args){
		
		// TODO Auto-generated method stub
	

		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/zefxis/experiments/stress-testing/DeXIDL/temperature.gidl";
		byte[] byteArray = readBytesFromFile(interfaceDescriptionPath);
		
		MediatorManagerClient client = new MediatorManagerClient("127.0.0.1", 8080,"status");
		System.out.println(" Status  "+client.getStatus());
		
		
		
	
		/*MediatorOutput mediatorOutput = client.generateMediator(byteArray, "COAP", "MQTT_to_COAP");
		
		System.out.println("mediatorOutput.war.length "+mediatorOutput.war.length);*/
	}

	private static byte[] readBytesFromFile(String filePath){

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try{

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            
            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } 
        catch (IOException e){
        	
            e.printStackTrace();
            
        }finally{
        	
            if (fileInputStream != null){
                try{
                    
                	fileInputStream.close();
                    
                }catch(IOException e){
                	
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }
}

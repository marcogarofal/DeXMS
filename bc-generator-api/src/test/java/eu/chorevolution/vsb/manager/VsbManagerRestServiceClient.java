package eu.chorevolution.vsb.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import eu.chorevolution.vsb.bc.manager.VsbOutput;
import eu.chorevolution.vsb.manager.api.client.VsbManagerClient;

public class VsbManagerRestServiceClient{

	
public static void main(String[] args){
		
		// TODO Auto-generated method stub
		String interfaceDescriptionPath = "/home/pntumba/inria_code/repositories/evolution-service-bus/bc-manager/src/main/resources/coap.gidl";
		byte[] byteArray = readBytesFromFile(interfaceDescriptionPath);
		
		VsbManagerClient client = new VsbManagerClient("127.0.0.1", 8080);
		VsbOutput vsbOutput = client.generateBindingComponent(byteArray, "REST", "ServiceTest");
		System.out.println("vsbOutput.war.length "+vsbOutput.war.length);
		System.out.println("vsbOutput.wsdl.length "+vsbOutput.wsdl.length);
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

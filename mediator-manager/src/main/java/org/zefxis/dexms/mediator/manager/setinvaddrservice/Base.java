/**
 *   Copyright 2015 The CHOReVOLUTION project
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.zefxis.dexms.mediator.manager.setinvaddrservice;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.zefxis.dexms.mediator.manager.MediatorManagerRestService;
import org.zefxis.dexms.mediator.manager.setinvaddrservice.BaseService;

@WebService(
        name = "ConfigurableService",
        targetNamespace = "http://services.chorevolution.eu/"
)
public class Base implements BaseService {

	protected String ENDPOINTADDRESS=null;
	
	@Resource
	private WebServiceContext context;

	private static List<URL> endpoints = new ArrayList<URL>(); 
	/**
	 * Each BasicService subclass has the following lifecycle:
	 * 1. start the service
	 * 2. send it its own enpoint address
	 * 3. send the service dependency data (according to Enactment Engine choreography requirements)
	 * */
	
	public Base(){}

	/* (non-Javadoc)
	 * @see org.choreos.services.Base#setInvocationAddress(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
     * @see eu.chorevolution.enactment.dependencies.ConfigurableService#setInvocationAddress(java.lang.String, java.lang.String, java.util.List)
     */
	@WebMethod
	public void setInvocationAddress(String role, String name, List<String> endpoints) {
		
		
		ServletContext servletContext = (ServletContext) context.getMessageContext().get(javax.xml.ws.handler.MessageContext.SERVLET_CONTEXT);    

		
		//System.out.println("setting inv. addrr to "+ endpoints); 
		System.out.println("role "+ role); 
		System.out.println("name "+ name); 
		if (endpoints.size() > 0){
			
			System.out.println("set the invocatin address to : " +  endpoints.get(0));
			
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			
			
			File dir = new File(MediatorManagerRestService.class.getClassLoader().getResource("example.json").toExternalForm().substring(9)).getParentFile().getParentFile().getParentFile().getParentFile();
			File dir2 = new File(dir.getAbsolutePath() + File.separator + "config");
			
			String setinvaddrPath = dir2.getAbsolutePath()+File.separator+"config_block1_interface_1";
			System.out.println("Set invocation address into first file conf "+setinvaddrPath);
			try {
				jsonObject = (JSONObject) parser.parse(new FileReader(setinvaddrPath));
				
				
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			
			jsonObject.put("invocation_address", endpoints.get(0));
			
			try (FileWriter file = new FileWriter(setinvaddrPath)) {
				file.write(jsonObject.toJSONString());
			} catch (IOException e){
				
				e.printStackTrace();
			}
			
			
			setinvaddrPath = dir2.getAbsolutePath()+File.separator+"config_block2_interface_1";
			System.out.println("Set invocation address into second file conf "+setinvaddrPath);
			try {
				jsonObject = (JSONObject) parser.parse(new FileReader(setinvaddrPath));
				
				
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			
			jsonObject.put("invocation_address", endpoints.get(0));
			
			try (FileWriter file = new FileWriter(setinvaddrPath)) {
				file.write(jsonObject.toJSONString());
			} catch (IOException e){
				
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * Example call: (Client1) (getPort("Client1", "http://localhost/client1?wsdl", Client1.class));
	 * @throws MalformedURLException 
	 * */
	public static <T> T getPort(String service_name, String wsdlLocation, Class<T> client_class) throws MalformedURLException{
	    T retPort = null;
	    QName serviceQName = null;
	    URL serviceUrl = null;
	    Service s;

	    serviceQName = new QName(namespace, service_name+"Service");

	    serviceUrl = new URL(wsdlLocation);

	    s = Service.create(serviceUrl, serviceQName);
	    retPort=(T) s.getPort(client_class);

	    return retPort;
	}

}

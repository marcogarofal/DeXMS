package org.zefxis.dexms.mediator.generator.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.simple.JSONObject;
import org.restlet.Client;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.representation.ObjectRepresentation;
import org.zefxis.dexms.mediator.manager.MediatorOutput;

public class MediatorManagerClient {

	private String url = null;

	/**
	 * Default constructor of the class
	 * <p>
	 *
	 * @param dexms_host
	 *            address of DeXMS REST Service
	 * @param dexms_port
	 *            address of DeXMS REST Service
	 * 
	 */

	public MediatorManagerClient(String dexms_host, int dexms_port, String path) {

		this.url = "http://" + dexms_host + ":" + dexms_port + "/DexmsService/dexms/"+path;
	}
	
	public String getStatus() {
		
		return get(url).getEntityAsText();
		
	}

	/**
	 * Returns MediatorOutput object which contains the generated 1 byte array of
	 * archive war and 1 array bytes of wsdl file and url of the service set
	 * invocation address
	 * <p>
	 *
	 * @param interfaceDescServiceByteArray
	 *            array of a gidl or gmdl file
	 * @param protocol
	 *            the protocole type to use (SOAP, REST, ...)
	 * @param service_name
	 *            the name of the service will be generated
	 * @return VsbOutput object
	 */

	public MediatorOutput generateMediator(byte[] interfaceDescServiceByteArray, String protocol, String service_name) {

		MediatorOutput output = null;
		String interfaceDescServiceContent = byteArrayToString(interfaceDescServiceByteArray);
		JSONObject obj = new JSONObject();
		obj.put("interface", interfaceDescServiceContent);
		obj.put("protocol", protocol);
		obj.put("service_name", service_name);

		String data = obj.toJSONString();
		Response response = post(this.url, data);
		if (response.getStatus().getCode() != 200) {

			System.out.println(response.getStatus().toString());
			return output;
		}
		ObjectRepresentation<MediatorOutput> mOutput;
		try {

			mOutput = new ObjectRepresentation<MediatorOutput>(response.getEntity(), null, true, true);
			output = mOutput.getObject();

		} catch (IllegalArgumentException | IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * Transforms an array of bits in string
	 * <p>
	 *
	 * @param array
	 *            of bytes
	 * @return String object
	 */

	private static String byteArrayToString(byte[] array) {

		String string = null;
		try {

			string = new String(array, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * Sends a post request to DeXMS service
	 * <p>
	 *
	 * @param url
	 *            of DeXMS REST Service
	 * @param content
	 *            of the request
	 * @return Response restlet object
	 */

	private static Response post(String url, String content) {

		Request request = new Request();
		request.setResourceRef(url);
		request.setMethod(Method.POST);
		request.setEntity(content, MediaType.APPLICATION_JSON);
		Context ctx = new Context();
		Client client = new Client(ctx, Protocol.HTTP);
		return client.handle(request);
	}
	
	
	
	/**
	 * Sends a get request to DeXMS service
	 * <p>
	 *
	 * @param url
	 *            of DeXMS REST Service
	 * @param content
	 *            of the request
	 * @return DeXMS status
	 */
	
	private static Response get(String url) {

		Request request = new Request();
		request.setResourceRef(url);
		request.setMethod(Method.GET);
		Context ctx = new Context();
		Client client = new Client(ctx, Protocol.HTTP);
		return client.handle(request);
	}
}

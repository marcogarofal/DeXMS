package org.zefxis.dexms.dex.protocols.coap;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.ws.rs.core.UriBuilder;

import org.zefxis.dexms.dex.protocols.builders.RequestBuilder;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.Data.Context;



public class CoapRequestBuilder implements RequestBuilder {
	
	String destination;
	String scope;
	List<Data<?>> datas;
	
	public CoapRequestBuilder(String destination, String scope, List<Data<?>> datas){
		
		this.destination = destination;
		this.scope = scope;
		this.datas = datas;
	}
	
	
	public static CoapRequest buildCoapRequest(final String destination, final String scope, final List<Data<?>> datas) {
		CoapRequest request = new CoapRequest();
		request.setDestination(destination);
		request.setScope(scope);
		buildRequestQuery(request, datas);
		buildRequestBody(request, datas);
		return request;
	}

	private static void buildRequestQuery(CoapRequest request, final List<Data<?>> datas) {
		StringBuilder  dataBuilder = new StringBuilder();
		for(Data<?> data : datas) {
			if(data.getContext() == Context.QUERY){
				System.out.println(data.getName() + " " + data.getObject().toString());
				dataBuilder.append(data.getName() + "=" + data.getObject().toString()+",");
				// TODO
			}
		}
		request.setData(dataBuilder.toString());
	}
	
	private static void buildRequestBody(CoapRequest request, final List<Data<?>> datas) {
		StringBuilder  dataBuilder = new StringBuilder();
		for (Data<?> data : datas) {
			System.err.println(data.getContext()+"  "+ data.getName() + " " + data.getObject().toString());
			if(data.getContext() == Context.BODY){
				System.out.println(data.getName() + " " + data.getObject().toString());
				dataBuilder.append(data.getName() + "=" + data.getObject().toString()+",");
				// TODO
			}
		}
		request.setData(dataBuilder.toString());
	}
}
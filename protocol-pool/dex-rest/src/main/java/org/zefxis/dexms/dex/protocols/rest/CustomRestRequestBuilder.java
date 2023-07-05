package org.zefxis.dexms.dex.protocols.rest;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.restlet.Request;
import org.restlet.data.Header;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.StringRepresentation;
import org.restlet.util.Series;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.Data.Context;
import org.zefxis.dexms.gmdl.utils.Scope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nitorcreations.xmlutils.XMLUtil;



public class CustomRestRequestBuilder {

	private static Scope scope = null;

	public static Request buildRestPostRequest(final String destination, final Scope scope, final List<Data<?>> datas) {

		return buildRestRequest(Method.POST, destination, scope, datas);
	}

	public static Request buildRestGetRequest(final String destination, final Scope scope, final List<Data<?>> datas) {
		return buildRestRequest(Method.GET, destination, scope, datas);
	}

	private static Request buildRestRequest(final Method method, final String destination, final Scope sc,
			final List<Data<?>> datas) {
		scope = sc;
		Request request = new Request();
		request.setResourceRef("");

		UriBuilder builder = UriBuilder.fromPath(destination + scope.getUri());
		buildRequestHeaders(request, datas);
		buildRequestPath(builder, datas);
		buildRequestQuery(request, datas);
		buildRequestForm(request, datas);
		buildRequestBody(request, datas);

		String params = request.getResourceRef().toString();
		try {
			// request.setResourceRef(java.net.URLDecoder.decode(builder.toString(),
			// "UTF-8"));
			request.setResourceRef(java.net.URLDecoder.decode(destination + scope.getUri() + params, "UTF-8"));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setMethod(method);
		return request;
	}

	private static void buildRequestBody(final Request request, final List<Data<?>> datas) {
		StringBuilder req = new StringBuilder();

		for (Data<?> data : datas) {
			if (data.getContext() == Context.BODY) {

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

				try {

					if (scope.getName().equals("VasttrafikGenerateOAuth2token")) {

						if (data.getName().equals("authorization")) {

							Series<Header> requestHeaders = new Series(Header.class);
							requestHeaders.add("Authorization", data.getObject().toString());
							requestHeaders.add("Accept", "*/*");
							requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
							request.getAttributes().put("org.restlet.http.headers", requestHeaders);

						} else {

							req.append(ow.writeValueAsString(data.getObject()));

						}

					} else {

						req.append(ow.writeValueAsString(data.getObject()));
					}

				} catch (JsonProcessingException e) {

					e.printStackTrace();
				}

			}

		}

		if (scope.getName().equals("VasttrafikGenerateOAuth2token")) {
			
			String message = new String(req.toString().substring(1,req.toString().length()-1));
			StringRepresentation entity = new StringRepresentation(message); 
			entity.setMediaType(MediaType.APPLICATION_WWW_FORM);
			request.setEntity(entity);
			

		} else if (scope.getName().equals("TrafficverketRoadcondition")
				|| scope.getName().equals("TrafficverketWeather")) {

			String req_str = new String(req.toString());
			Matcher junkMatcher = (Pattern.compile("^([\\W]+)<")).matcher(req_str.trim());
			req_str = junkMatcher.replaceFirst("<");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			Document document = null;
			try {

				builder = factory.newDocumentBuilder();
				document = builder
						.parse(new InputSource(new StringReader(req_str.substring(0, req_str.lastIndexOf('>') + 1))));

			} catch (Exception e) {
				e.printStackTrace();
			}

			StringRepresentation entity = new StringRepresentation(XMLUtil.documentToString(document)); 
			entity.setCharacterSet(null);
			entity.setMediaType(MediaType.TEXT_XML);
			request.setEntity(entity);
		} else {

			request.setEntity(req.toString(), MediaType.APPLICATION_ALL_JSON);
		}

	}

	private static void buildRequestForm(final Request request, final List<Data<?>> datas) {
		for (Data<?> data : datas) {
			if (data.getContext() == Context.FORM) {
				// TODO
			}
		}
	}

	private static void buildRequestQuery(final Request request, final List<Data<?>> datas) {

		for (Data<?> data : datas) {

			if (data.getContext() == Context.QUERY) {

				if (data.getName().equals("authorization")) {

					if (scope.getName().equals("VasttrafikrequestDepartureBoard")) {

						Series<Header> requestHeaders = new Series(Header.class);
						requestHeaders.add("Authorization", data.getObject().toString());
						request.getAttributes().put("org.restlet.http.headers", requestHeaders);

					}

				} else {

					request.getResourceRef().addQueryParameter(data.getName(), data.getObject().toString());
				}
			}
		}

	}

	private static void buildRequestPath(final UriBuilder builder, final List<Data<?>> datas) {
		for (Data<?> data : datas) {
			if (data.getContext() == Context.PATH) {
				builder.resolveTemplate(data.getName(), data.getObject());
			}
		}
	}

	private static void buildRequestHeaders(final Request request, final List<Data<?>> datas) {
		for (Data<?> data : datas) {
			if (data.getContext() == Context.HEADER) {

				// request.getOnSent();
			}
		}
	}
}

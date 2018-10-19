package org.zefxis.dexms.dex.protocols.rest;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.zefxis.dexms.dex.protocols.builders.ResponseBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


// TODO: instead of one class with multiple methods:
// could use a Factory + an interface ResponseBuilder  + concretes impl. as JsonResponseBuilder, XmlResponseBuilder etc.
public class RestResponseBuilder extends ResponseBuilder {

	@Override
	public <T> T unmarshalObject(String mediaType, String serializedObject, Class<T> responseClass) {
		// TODO Auto-generated method stub
		switch (mediaType) {
		case "application/json":

			return unmarshalObjectFromJson(serializedObject, responseClass);

		case "application/xml":
			
			String serializedObjectToJson = XML.toJSONObject(serializedObject).toString();
			return unmarshalObjectFromJson(serializedObjectToJson, responseClass);

		default:
			String error = "{'error' : '415 HTTP Undefined media type'}";
			return unmarshalObjectFromJson(error, (Class<T>)RestErrorException.class);
		}

	}

	@Override
	public <T> T unmarshalObject(String mediaType, String serializedObject, CollectionType collectionType) {
		// TODO Auto-generated method stub
		switch (mediaType) {
		case "application/json":

			return RestResponseBuilder.unmarshalObjectFromJson(serializedObject, collectionType);
			
		case "application/xml":
			
			String serializedObjectToJson = XML.toJSONObject(serializedObject).toString();
			return unmarshalObjectFromJson(serializedObjectToJson, collectionType);
			
		default:
			String error = "{'error' : '415 HTTP Undefined media type'}";
			return unmarshalObjectFromJson(error, (Class<T>)RestErrorException.class);
			
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T unmarshalObjectFromJson(final String serializedObject, final Class<T> responseClass) {
		ObjectMapper mapper = new ObjectMapper();

		try {

			JsonNode rootNode = mapper.readTree(serializedObject);

			if (rootNode instanceof ObjectNode) {

				return (T) mapper.readValue(serializedObject, responseClass);

			}else{
				
				String error = "{'error' : '420 HTTP Json Respnse type must be an object'}";
				return (T) mapper.readValue(error, RestErrorException.class);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	private static <T> T unmarshalObjectFromJson(final String serializedObject, final CollectionType collectionType) {
		ObjectMapper mapper = new ObjectMapper();

		try {

			JsonNode rootNode = mapper.readTree(serializedObject);

			if (rootNode instanceof ArrayNode) {

				mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				return (T) mapper.readValue(serializedObject, collectionType);

			}else{
				
				String error = "{'error' : '420 HTTP Json Respnse type must be an array'}";
				return (T) mapper.readValue(error, RestErrorException.class);
				
			}

		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		return null;
	}

}

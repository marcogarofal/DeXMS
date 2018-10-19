package org.zefxis.dexms.dexprotocols.gidl2wsdl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.zefxis.dexms.gmdl.utils.Constants;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;



public class GidlToWSDL {

	private MediatorConfiguration configuration = null;
	private Document wsdlDocument = null;
	private Document gidlDocument = null;
	private Element wsdlTypes = null;
	private Element xsSchema = null;
	private Element wsdldefinitions = null;
	private NamedNodeMap attr = null;
	private Attr nodeAttribute = null;
	private ArrayList<String> xsSchemaElement = new ArrayList<String>();
	private Node hasOperationsNode = null;

	public GidlToWSDL(MediatorConfiguration configuration){

		this.configuration = configuration;
		parseModeleDescription();
		initWSDLDocument();

	}

	private void parseModeleDescription() {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;

		try {

			docBuilder = docFactory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {

			gidlDocument = docBuilder.parse(Constants.interfaceDescriptionFilePath);

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private void initWSDLDocument() {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {

			dBuilder = dbFactory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wsdlDocument = dBuilder.newDocument();
		wsdldefinitions = wsdlDocument.createElement("wsdl:definitions");

		nodeAttribute = wsdlDocument.createAttribute("xmlns:xsd");
		nodeAttribute.setValue("http://www.w3.org/2001/XMLSchema");
		wsdldefinitions.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("xmlns:wsdl");
		nodeAttribute.setValue("http://schemas.xmlsoap.org/wsdl/");
		wsdldefinitions.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("xmlns:tns");
		nodeAttribute.setValue(configuration.getTargetNamespace());
		wsdldefinitions.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("xmlns:soap");
		nodeAttribute.setValue("http://schemas.xmlsoap.org/wsdl/soap/");
		wsdldefinitions.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("xmlns:ns1");
		nodeAttribute.setValue("http://schemas.xmlsoap.org/soap/http");
		wsdldefinitions.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(configuration.getServiceName());
		wsdldefinitions.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("targetNamespace");
		nodeAttribute.setValue(configuration.getTargetNamespace());
		wsdldefinitions.setAttributeNode(nodeAttribute);
		wsdlDocument.appendChild(wsdldefinitions);

	}

	public void generateWSDL() {

		addTypeWSDL();
		addMessageWSDL();
		addPortType();
		addBinding();
		String endpoint = "http://localhost:"+Constants.service_port + "/" + configuration.getServiceName();
		addService(endpoint);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {

			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(wsdlDocument);
			StreamResult result = new StreamResult(new File(Constants.wsdlDestination + File.separator + Constants.wsdlName+".wsdl"));
			transformer.transform(source, result);
			

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addTypeWSDL() {

		wsdlTypes = wsdlDocument.createElement("wsdl:types");
		xsSchema = wsdlDocument.createElement("xs:schema");

		nodeAttribute = wsdlDocument.createAttribute("xmlns:xs");
		nodeAttribute.setValue("http://www.w3.org/2001/XMLSchema");
		xsSchema.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("xmlns:tns");
		nodeAttribute.setValue(configuration.getTargetNamespace());
		xsSchema.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("elementFormDefault");
		nodeAttribute.setValue("unqualified");
		xsSchema.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("targetNamespace");
		nodeAttribute.setValue(configuration.getTargetNamespace());
		xsSchema.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("version");
		nodeAttribute.setValue("1.0");
		xsSchema.setAttributeNode(nodeAttribute);

		hasOperationsNode = gidlDocument.getElementsByTagName("hasOperations").item(0);

		Element xselement = wsdlDocument.createElement("xs:element");
		attr = hasOperationsNode.getAttributes();

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
		xselement.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("type");
		nodeAttribute.setValue("tns:" + attr.getNamedItem("name").getTextContent());
		xselement.setAttributeNode(nodeAttribute);
		if (!xsSchemaElement.contains(attr.getNamedItem("name").getTextContent() + "xs:element")) {

			xsSchema.appendChild(xselement);
			xsSchemaElement.add(attr.getNamedItem("name").getTextContent() + "xs:element");
		}
		
		
		Element xselementResponse = wsdlDocument.createElement("xs:element");
		attr = hasOperationsNode.getAttributes();

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent()+"Response");
		xselementResponse.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("type");
		nodeAttribute.setValue("tns:" + attr.getNamedItem("name").getTextContent()+"Response");
		xselementResponse.setAttributeNode(nodeAttribute);
		if (!xsSchemaElement.contains(attr.getNamedItem("name").getTextContent() +"Response"+ "xs:element")) {

			xsSchema.appendChild(xselementResponse);
			xsSchemaElement.add(attr.getNamedItem("name").getTextContent() +"Response"+ "xs:element");
		}
		
		

//		addSpecialComplexType(inputDataNode, "inputDataNode");
//		addSpecialComplexType(outputDataNode, "outputDataNode");
//		if(inputDataNode != null && !inputDataNode.hasChildNodes()){
//			
//			goDeeply(inputDataNode);
//		}
//		
//		if(outputDataNode != null && !outputDataNode.hasChildNodes()){
//			
//			goDeeply(outputDataNode);
//		}
		addInputTypeWSDL(gidlDocument.getElementsByTagName("inputData").item(0));
		
		addOutputTypeWSDL(gidlDocument.getElementsByTagName("outputData").item(0));
		wsdlTypes.appendChild(xsSchema);
		wsdldefinitions.appendChild(wsdlTypes);

	}
	
	
	private void addInputTypeWSDL(Node inputDataNode){
		
		boolean foudComplexType = false;
		int arg = 0;
		for (int j = 0; j < inputDataNode.getChildNodes().getLength(); j++) {
			
			Node childNode = inputDataNode.getChildNodes().item(j);
			if(childNode.getNodeName().equals("hasDataType")){
				
				String xsi_type = childNode.getAttributes()
						.getNamedItem("xsi:type")
						.getTextContent().toString();
				if(xsi_type.equals("gidl:ComplexType")){
					
					foudComplexType = true;
				}
				
			}
			
		}
		if(foudComplexType){
			
			addSpecialComplexType(inputDataNode, "inputDataNode");
			goDeeply(inputDataNode);
			
		}else{
			
			attr = hasOperationsNode.getAttributes();
			Element xscomplexType = wsdlDocument.createElement("xs:complexType");
			nodeAttribute = wsdlDocument.createAttribute("name");
			nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
			xscomplexType.setAttributeNode(nodeAttribute);
			Element xssequence = wsdlDocument.createElement("xs:sequence");
			for (int j = 0; j < inputDataNode.getChildNodes().getLength(); j++) {

				Node childNode = inputDataNode.getChildNodes().item(j);
				if (childNode.getNodeName().equals("hasDataType")) {

					NamedNodeMap attrChild = childNode.getAttributes();

					Element xselementChild = wsdlDocument.createElement("xs:element");
				
					nodeAttribute = wsdlDocument.createAttribute("name");
					nodeAttribute.setValue("arg"+arg);
					arg++;
					xselementChild.setAttributeNode(nodeAttribute);
						
					nodeAttribute = wsdlDocument.createAttribute("type");
					nodeAttribute.setValue("xs:" + attrChild.getNamedItem("type").getTextContent());
					xselementChild.setAttributeNode(nodeAttribute);
						
					nodeAttribute = wsdlDocument.createAttribute("type");
					nodeAttribute.setValue("xs:" + attrChild.getNamedItem("type").getTextContent());
					xselementChild.setAttributeNode(nodeAttribute);
					
					nodeAttribute = wsdlDocument.createAttribute("minOccurs");
					nodeAttribute.setValue("0");
					xselementChild.setAttributeNode(nodeAttribute);
					
					xssequence.appendChild(xselementChild);

				}
			}
			xscomplexType.appendChild(xssequence);
			xsSchema.appendChild(xscomplexType);
			
		}
		
		
	}
	
	
	private void addOutputTypeWSDL(Node outputDataNode){
		
		addSpecialComplexType(outputDataNode, "outputDataNode");
		goDeeply(outputDataNode);
		
	}
	
	
	private void goDeeply(Node rootNode){
		
		
		for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {

			Node node = rootNode.getChildNodes().item(i);
			if (node.getNodeName().equals("hasDataType")) {

				attr = node.getAttributes();
				if (attr.getNamedItem("xsi:type") != null
						&& attr.getNamedItem("xsi:type").getTextContent().equals("gidl:ComplexType")) {

					Element xselement = wsdlDocument.createElement("xs:element");

					nodeAttribute = wsdlDocument.createAttribute("name");
					nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
					xselement.setAttributeNode(nodeAttribute);

					nodeAttribute = wsdlDocument.createAttribute("type");
					nodeAttribute.setValue("tns:" + attr.getNamedItem("name").getTextContent());
					xselement.setAttributeNode(nodeAttribute);

					if (!xsSchemaElement.contains(attr.getNamedItem("name").getTextContent() + "xs:element")) {

						xsSchema.appendChild(xselement);
						xsSchemaElement.add(attr.getNamedItem("name").getTextContent() + "xs:element");
					}

					if (node.hasChildNodes()) {

						Element xscomplexType = wsdlDocument.createElement("xs:complexType");
						nodeAttribute = wsdlDocument.createAttribute("name");
						nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
						xscomplexType.setAttributeNode(nodeAttribute);

						Element xssequence = wsdlDocument.createElement("xs:sequence");
						for (int j = 0; j < node.getChildNodes().getLength(); j++) {

							Node childNode = node.getChildNodes().item(j);
							if (childNode.getNodeName().equals("hasDataType")) {

								NamedNodeMap attrChild = childNode.getAttributes();

								Element xselementChild = wsdlDocument.createElement("xs:element");

								nodeAttribute = wsdlDocument.createAttribute("name");
								nodeAttribute.setValue(attrChild.getNamedItem("name").getTextContent());
								xselementChild.setAttributeNode(nodeAttribute);
								if (attrChild.getNamedItem("xsi:type").getTextContent().equals("gidl:ComplexType")) {
									
									
									if(attrChild.getNamedItem("maxOccurs").getTextContent().equals("unbounded")){
									
										nodeAttribute = wsdlDocument.createAttribute("maxOccurs");
										nodeAttribute.setValue("unbounded");
										xselementChild.setAttributeNode(nodeAttribute);
										
									}
									nodeAttribute = wsdlDocument.createAttribute("type");
									nodeAttribute.setValue("tns:" + attrChild.getNamedItem("name").getTextContent());
									xselementChild.setAttributeNode(nodeAttribute);
									
								} else {

									nodeAttribute = wsdlDocument.createAttribute("type");
									nodeAttribute.setValue("xs:" + attrChild.getNamedItem("type").getTextContent());
									xselementChild.setAttributeNode(nodeAttribute);
								}
								xssequence.appendChild(xselementChild);

							}
						}
						xscomplexType.appendChild(xssequence);
						
						if (!xsSchemaElement.contains(attr.getNamedItem("name").getTextContent() + "xscomplexType")) {

							xsSchema.appendChild(xscomplexType);
							xsSchemaElement.add(attr.getNamedItem("name").getTextContent() + "xscomplexType");

						}

					}
				}
			}			
			goDeeply(node);
		}

	}

	private void addMessageWSDL(){

		Element wsdlMessage = wsdlDocument.createElement("wsdl:message");
		Element wsdlMessageResponse = wsdlDocument.createElement("wsdl:message");
		Node node = gidlDocument.getElementsByTagName("hasOperations").item(0);
		attr = node.getAttributes();
		
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
		wsdlMessage.setAttributeNode(nodeAttribute);
		
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent()+"Response");
		wsdlMessageResponse.setAttributeNode(nodeAttribute);
		
		Element wsdlMessagePart = wsdlDocument.createElement("wsdl:part");
		Element wsdlMessagePartResponse = wsdlDocument.createElement("wsdl:part");

		nodeAttribute = wsdlDocument.createAttribute("element");
		nodeAttribute.setValue("tns:" + attr.getNamedItem("name").getTextContent());
		wsdlMessagePart.setAttributeNode(nodeAttribute);
		
		nodeAttribute = wsdlDocument.createAttribute("element");
		nodeAttribute.setValue("tns:" + attr.getNamedItem("name").getTextContent()+"Response");
		wsdlMessagePartResponse.setAttributeNode(nodeAttribute);
		
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue("parameters");
		wsdlMessagePart.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue("parameters");
		wsdlMessagePartResponse.setAttributeNode(nodeAttribute);
		
		wsdlMessage.appendChild(wsdlMessagePart);
		wsdlMessageResponse.appendChild(wsdlMessagePartResponse);
		
		wsdldefinitions.appendChild(wsdlMessage);
		wsdldefinitions.appendChild(wsdlMessageResponse);

	}

	private void addPortType() {

		Element wsdlPortType = wsdlDocument.createElement("wsdl:portType");

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(configuration.getServiceName());
		wsdlPortType.setAttributeNode(nodeAttribute);

		Element wsdlOperation = wsdlDocument.createElement("wsdl:operation");
		Node node = gidlDocument.getElementsByTagName("hasOperations").item(0);
		attr = node.getAttributes();

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
		wsdlOperation.setAttributeNode(nodeAttribute);

		Element wsdlInput = wsdlDocument.createElement("wsdl:input");

		nodeAttribute = wsdlDocument.createAttribute("message");
		nodeAttribute.setValue("tns:" + attr.getNamedItem("name").getTextContent());
		wsdlInput.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
		wsdlInput.setAttributeNode(nodeAttribute);

		Element wsdlOutput = wsdlDocument.createElement("wsdl:output");

		nodeAttribute = wsdlDocument.createAttribute("message");
		nodeAttribute.setValue("tns:" + attr.getNamedItem("name").getTextContent() + "Response");
		wsdlOutput.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent() + "Response");
		wsdlOutput.setAttributeNode(nodeAttribute);

		wsdlOperation.appendChild(wsdlInput);
		wsdlOperation.appendChild(wsdlOutput);
		wsdlPortType.appendChild(wsdlOperation);
		wsdldefinitions.appendChild(wsdlPortType);

	}

	private void addBinding(){

		Node node = gidlDocument.getElementsByTagName("hasOperations").item(0);
		attr = node.getAttributes();

		Element wsdlBinding = wsdlDocument.createElement("wsdl:binding");
		
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(configuration.getServiceName() + "SoapBinding");
		wsdlBinding.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("type");
		nodeAttribute.setValue("tns:"+configuration.getServiceName());
		wsdlBinding.setAttributeNode(nodeAttribute);

		Element wsdlSoapBinding = wsdlDocument.createElement("soap:binding");

		nodeAttribute = wsdlDocument.createAttribute("style");
		nodeAttribute.setValue("document");
		wsdlSoapBinding.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("transport");
		nodeAttribute.setValue("http://schemas.xmlsoap.org/soap/http");
		wsdlSoapBinding.setAttributeNode(nodeAttribute);

		Element wsdlOperation = wsdlDocument.createElement("wsdl:operation");

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
		wsdlOperation.setAttributeNode(nodeAttribute);

		Element wsdlSoapOperation = wsdlDocument.createElement("soap:operation");

		nodeAttribute = wsdlDocument.createAttribute("soapAction");
		nodeAttribute.setValue("");
		wsdlSoapOperation.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("style");
		nodeAttribute.setValue("document");
		wsdlSoapOperation.setAttributeNode(nodeAttribute);

		Element wsdlInput = wsdlDocument.createElement("wsdl:input");
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
		wsdlInput.setAttributeNode(nodeAttribute);

		Element wsdlOutput = wsdlDocument.createElement("wsdl:output");
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent() + "Response");
		wsdlOutput.setAttributeNode(nodeAttribute);

		Element wsdlSoapBodyInput = wsdlDocument.createElement("soap:body");
		nodeAttribute = wsdlDocument.createAttribute("use");
		nodeAttribute.setValue("literal");
		wsdlSoapBodyInput.setAttributeNode(nodeAttribute);
		
		
		Element wsdlSoapBodyOutput = wsdlDocument.createElement("soap:body");
		nodeAttribute = wsdlDocument.createAttribute("use");
		nodeAttribute.setValue("literal");
		wsdlSoapBodyOutput.setAttributeNode(nodeAttribute);
		
		wsdlOutput.appendChild(wsdlSoapBodyOutput);
		wsdlInput.appendChild(wsdlSoapBodyInput);
		wsdlOperation.appendChild(wsdlSoapOperation);
		wsdlOperation.appendChild(wsdlInput);
		wsdlOperation.appendChild(wsdlOutput);
		wsdlBinding.appendChild(wsdlSoapBinding);
		wsdlBinding.appendChild(wsdlOperation);
		wsdldefinitions.appendChild(wsdlBinding);
	}

	private void addService(String endpointLocation){

		Node node = gidlDocument.getElementsByTagName("hasOperations").item(0);
		attr = node.getAttributes();

		Element wsdlService = wsdlDocument.createElement("wsdl:service");
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(configuration.getServiceName());
		wsdlService.setAttributeNode(nodeAttribute);

		Element wsdlPort = wsdlDocument.createElement("wsdl:port");
		nodeAttribute = wsdlDocument.createAttribute("binding");
		nodeAttribute.setValue("tns:" + configuration.getServiceName() + "SoapBinding");
		wsdlPort.setAttributeNode(nodeAttribute);

		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(configuration.getServiceName() + "Port");
		wsdlPort.setAttributeNode(nodeAttribute);

		Element wsdlSoapAddress = wsdlDocument.createElement("soap:address");
		nodeAttribute = wsdlDocument.createAttribute("location");
		nodeAttribute.setValue(endpointLocation);
		wsdlSoapAddress.setAttributeNode(nodeAttribute);

		wsdlPort.appendChild(wsdlSoapAddress);
		wsdlService.appendChild(wsdlPort);
		wsdldefinitions.appendChild(wsdlService);

	}
	
	private void addSpecialComplexType(Node node, String type){
		
		
		attr = hasOperationsNode.getAttributes();
		Element xscomplexType = wsdlDocument.createElement("xs:complexType");
		nodeAttribute = wsdlDocument.createAttribute("name");
		nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
		String arg0 = null; 
		if(type.equals("outputDataNode")){
			
			nodeAttribute.setValue(attr.getNamedItem("name").getTextContent()+"Response");
			arg0 =  "return";
		}
		else{
			
			nodeAttribute.setValue(attr.getNamedItem("name").getTextContent());
			arg0 =  "arg0";
		}
		
		xscomplexType.setAttributeNode(nodeAttribute);
		Element xssequence = wsdlDocument.createElement("xs:sequence");

		Element xselementChild = wsdlDocument.createElement("xs:element");

		boolean found = false;
		Node firstHasDataTypeTagName = null;
		if(node != null && !node.hasChildNodes()){
			
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {

				if (node.getChildNodes().item(i).getNodeName().equals("hasDataType") && !found) {

					found = true;
					firstHasDataTypeTagName = node.getChildNodes().item(i);
				}
			}
			
		}
		
		if(found){
			
			nodeAttribute = wsdlDocument.createAttribute("name");
			nodeAttribute.setValue(arg0);
			xselementChild.setAttributeNode(nodeAttribute);

			nodeAttribute = wsdlDocument.createAttribute("minOccurs");
			nodeAttribute.setValue("0");
			xselementChild.setAttributeNode(nodeAttribute);

			nodeAttribute = wsdlDocument.createAttribute("type");
			nodeAttribute.setValue("tns:" + firstHasDataTypeTagName.getAttributes().getNamedItem("name").getTextContent());
			xselementChild.setAttributeNode(nodeAttribute);

			xssequence.appendChild(xselementChild);
			xscomplexType.appendChild(xssequence);
			xsSchema.appendChild(xscomplexType);
			
		}
	}
}

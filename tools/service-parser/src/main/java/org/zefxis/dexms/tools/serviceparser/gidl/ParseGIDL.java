package org.zefxis.dexms.tools.serviceparser.gidl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.json.simple.parser.JSONParser;

import eu.chorevolution.modelingnotations.gidl.ComplexType;
import eu.chorevolution.modelingnotations.gidl.ContextTypes;
import eu.chorevolution.modelingnotations.gidl.DataType;
import eu.chorevolution.modelingnotations.gidl.GIDLModel;
import eu.chorevolution.modelingnotations.gidl.InterfaceDescription;
import eu.chorevolution.modelingnotations.gidl.MediaTypes;
import eu.chorevolution.modelingnotations.gidl.OccurrencesTypes;
import eu.chorevolution.modelingnotations.gidl.SimpleType;
import eu.chorevolution.modelingnotations.gidl.SimpleTypes;
import eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl;

import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Interface;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.dex.protocols.generators.MediatorSubcomponentGenerator;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.Data.Context;
import org.zefxis.dexms.gmdl.utils.Data.MediaType;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;
import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.gmdl.utils.enums.QosType;
import org.zefxis.dexms.gmdl.utils.enums.RoleType;
import org.zefxis.dexms.gmdl.utils.enums.Verb;


public class ParseGIDL {

	static class Utils{
		
		public static GIDLModel loadGIDLModel(URI cltsURI) throws Exception {
			GidlPackageImpl.init();
			Resource resource = new XMIResourceFactoryImpl().createResource(cltsURI);
			try {
				// load the resource
				resource.load(null);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error to load the resource: " + resource.getURI().toFileString());
			}
			GIDLModel gidlModel = (GIDLModel) resource.getContents().get(0);
			return gidlModel;
		}
	}

	public GmServiceRepresentation parse(String gidl){
		GIDLModel model = null;
		try {
			
			model = Utils.loadGIDLModel(URI.createURI(new File(gidl).toURI().toString()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parse_gidl(model);
	}

	private static String GIDLDataTypeToJavaType(SimpleTypes data_type){
		String data_type_str = "";
		switch(data_type) {
		case BOOLEAN:
			data_type_str = "Boolean";
			break;
		case INTEGER:
			data_type_str = "Integer";
			break;
		case DATE:
			data_type_str = "Date";
			break;
		case DECIMAL:
			data_type_str = "Double";
			break;
		case STRING:
			data_type_str = "String";
			break;
		case TIME:
			data_type_str = "Time";
			break;
		case DOUBLE:
			data_type_str = "Double";
			break;
		case FLOAT:
			data_type_str = "Float";
			break;
		default:
			System.out.println("Unknown data type while parsing GIDL " + data_type);
		}
		return data_type_str;
	}

	private static void addDataObject(Operation op, String getOrPost, eu.chorevolution.modelingnotations.gidl.Data getData, GmServiceRepresentation serviceRepresentation) {
		String data_name = getData.getName();
		ContextTypes contextType = getData.getContext();
		MediaTypes  mediaTypes = getData.getMedia();
		//		Definition $ref = getData.getHasDefinition();

		Context con = null;
		switch(contextType) {
		case BODY:
			con = Context.BODY;
			break;
		case FORM:
			con = Context.FORM;
			break;
		case HEADER:
			con = Context.HEADER;
			break;
		case PATH:
			con = Context.PATH;
			break;
		case QUERY:
			con = Context.QUERY;
			break;
		}
		
		MediaType media = null;
		if(mediaTypes == null){
			
			media = MediaType.JSON;
			
		}else{
			
			
			switch(mediaTypes) {
			case JSON:
				media = MediaType.JSON;
				break;
			case XML:
				media = MediaType.XML;
				break;
		
			}
		}
		
		
		Data<?> data = null;

		EList<DataType> dataTypeList = getData.getHasDataType();

		for(DataType dataType: dataTypeList){
			
			if(dataType instanceof SimpleType){
				
				SimpleTypes dataTypeClass = ((SimpleType) dataType).getType();
				String dataTypeClassStr = GIDLDataTypeToJavaType(dataTypeClass);
				if(((SimpleType) dataType).getMaxOccurs() == OccurrencesTypes.UNBOUNDED){
					dataTypeClassStr = "List<"+dataTypeClassStr+">"; 
				}
				data = new Data(((SimpleType) dataType).getName(), dataTypeClassStr, true, media, con, true);
			}
			else if(dataType instanceof ComplexType){
				
				String dataTypeClassStr = ((ComplexType)dataType).getName().toUpperCase();
				data = new Data(((ComplexType)dataType).getName(), dataTypeClassStr, true, media, con, true);
				parseComplexType(data, ((ComplexType)dataType), serviceRepresentation);
				serviceRepresentation.addTypeDefinition(data);
				if(((ComplexType) dataType).getMaxOccurs() == OccurrencesTypes.UNBOUNDED){
					dataTypeClassStr = "List<"+dataTypeClassStr+">"; 
					data = new Data(((ComplexType)dataType).getName(), dataTypeClassStr, true, media, con, true);
				}
			}
			data.setMediaType(media);
			if(getOrPost.equals("get")){
				
				op.addGetData(data);
			}
			else if(getOrPost.equals("post")) {
				
				switch(serviceRepresentation.getProtocol()) {
				
				case REST:
				case SOAP:	
					
					op.addPostDatas(data);
					
				break;
					
				default:
					
					op.setPostData(data);
					
				break;
				
				}
				
			}
			
		}
	}

	private static void parseComplexType(Data<?> parentData, ComplexType dataTypeComplex, GmServiceRepresentation serviceRepresentation) {
		Data<?> childData = null;
		EList<DataType> dataTypeList = dataTypeComplex.getHasDataType();
		for(DataType dataType: dataTypeList) {
			if(dataType instanceof SimpleType) {
				SimpleTypes dataTypeClass = ((SimpleType) dataType).getType();
				String dataTypeClassStr = GIDLDataTypeToJavaType(dataTypeClass);
				if(((SimpleType) dataType).getMaxOccurs() == OccurrencesTypes.UNBOUNDED) {
					dataTypeClassStr = "List<" + dataTypeClassStr +">"; 
				}
				childData = new Data(((SimpleType) dataType).getName(), dataTypeClassStr, true, null, null, true);
			}
			else if(dataType instanceof ComplexType) {
				String dataTypeClassStr = ((ComplexType)dataType).getName().toUpperCase();
				childData = new Data(((ComplexType)dataType).getName(), dataTypeClassStr, true, null, null, true);
				parseComplexType(childData, ((ComplexType)dataType), serviceRepresentation);
				serviceRepresentation.addTypeDefinition(childData);
				if(((ComplexType) dataType).getMaxOccurs() == OccurrencesTypes.UNBOUNDED) {
					dataTypeClassStr = "List<" + dataTypeClassStr +">"; 
//					childData.setClassName(dataTypeClassStr);
					childData = new Data(((ComplexType)dataType).getName(), dataTypeClassStr, true, null, null, true);
				}
			}
			parentData.addAttribute(childData);
		}
	}

	public GmServiceRepresentation parse_gidl(GIDLModel gidlModel) {

		JSONParser parser = new JSONParser();
		Map<String, Data<?>> definitonMap = new HashMap<String, Data<?>>();
		GmServiceRepresentation serviceRepresentation = new GmServiceRepresentation();

		String host_address = gidlModel.getHostAddress();
		eu.chorevolution.modelingnotations.gidl.ProtocolTypes protocol = gidlModel.getProtocol();

		serviceRepresentation.setHostAddress(host_address);

		setProtocol(serviceRepresentation, protocol);

		EList<InterfaceDescription> interfaces = gidlModel.getHasInterfaces();

		for(InterfaceDescription inter: interfaces) {

			Interface interfaceObj = null;

			interfaceObj = initializeInterfaceObjectWithRole(inter);

			// Looping through all the operations and adding our constructed operation object to interface object 

			EList<eu.chorevolution.modelingnotations.gidl.Operation> ops = inter.getHasOperations();

			for(eu.chorevolution.modelingnotations.gidl.Operation opGidl: ops) {

				// Getting operation name
				String operation_name = opGidl.getName();

				// Getting operation type
				OperationType type = getOperationType(opGidl);

				// Getting operation QoS
				QosType qosType = getQos(opGidl);

				// Parsing Scope
				Scope scope = getScope(opGidl); 

				// Constructing operation object 
				Operation op = new Operation(operation_name, type, qosType, scope);      

				// Adding Input Data
				EList<eu.chorevolution.modelingnotations.gidl.Data> getDataGidl = opGidl.getInputData();
				for(eu.chorevolution.modelingnotations.gidl.Data getData: getDataGidl) {
					
					
					addDataObject(op, "get", getData, serviceRepresentation); 
					//					op.addGetData(data);
				}

				// Adding Output data
				EList<eu.chorevolution.modelingnotations.gidl.Data> postDataGidl = opGidl.getOutputData();
				for(eu.chorevolution.modelingnotations.gidl.Data postData: postDataGidl) {
					
					addDataObject(op, "post", postData, serviceRepresentation); 
				}

				serviceRepresentation.addOperation(op);
				interfaceObj.addOperation(op);

			}

			serviceRepresentation.addInterface(interfaceObj);

		}

		return serviceRepresentation;
	}

	private void setProtocol(GmServiceRepresentation serviceRepresentation,
			eu.chorevolution.modelingnotations.gidl.ProtocolTypes protocol) {
		switch(protocol) {
		case REST:
			serviceRepresentation.setProtocol(ProtocolType.REST);
			break;
		case SOAP:
			serviceRepresentation.setProtocol(ProtocolType.SOAP);
			break;
		case CO_AP:
			serviceRepresentation.setProtocol(ProtocolType.COAP);
			break;
		case DPWS:
			serviceRepresentation.setProtocol(ProtocolType.DPWS);
			break;
			//		case JMS:
			//			serviceDefinition.setProtocol(ProtocolType.JMS);
			//			break;
		case MQTT:
			serviceRepresentation.setProtocol(ProtocolType.MQTT);
			break;
			//		case PUB_NUB:
			//			serviceDefinition.setProtocol(ProtocolType.PUB_NUB);
			//			break;
		case SEMI_SPACE:
			serviceRepresentation.setProtocol(ProtocolType.SEMI_SPACE);
			break;
		case WEB_SOCKETS:
			serviceRepresentation.setProtocol(ProtocolType.WEB_SOCKETS);
			break;
		case ZERO_MQ:
			serviceRepresentation.setProtocol(ProtocolType.ZERO_MQ);
			break;
		}
	}

	private Scope getScope(
			eu.chorevolution.modelingnotations.gidl.Operation opGidl) {

		eu.chorevolution.modelingnotations.gidl.Scope scopeGidl = opGidl.getHasScope();

		Scope scope = new Scope();

		if(scopeGidl!=null) {
			scope.setName(scopeGidl.getName());
			switch(scopeGidl.getVerb()) {
			case "GET":
				scope.setVerb(Verb.GET);            
				break;
			case "POST":
				scope.setVerb(Verb.POST);            
				break;
			case "PUT":
				scope.setVerb(Verb.PUT);            
				break;
			case "PATCH":
				scope.setVerb(Verb.PATCH);            
				break;
			case "DELETE":
				scope.setVerb(Verb.DELETE);            
				break;
			default:
				System.out.println("Error: Unknown verb encountered during parsing scope block.");
			}
			scope.setUri(scopeGidl.getUri());
		}
		return scope;

	}

	private QosType getQos(
			eu.chorevolution.modelingnotations.gidl.Operation opGidl) {

		eu.chorevolution.modelingnotations.gidl.QosTypes qosTypeGidl = opGidl.getQos();
		QosType qosType = null;
		switch(qosTypeGidl) {
		case RELIABLE:
			qosType = QosType.RELIABLE;
			break;
		case UNRELIABLE:
			qosType = QosType.UNRELIABLE;
			break;
		}
		return qosType;

	}

	private OperationType getOperationType(
			eu.chorevolution.modelingnotations.gidl.Operation opGidl) {

		eu.chorevolution.modelingnotations.gidl.OperationTypes operation_type = opGidl.getType();  
		OperationType type = null;
		switch(operation_type) {
		case ONE_WAY:
			type = OperationType.ONE_WAY;
			break;
		case STREAM:
			type = OperationType.STREAM;
			break;
		case TWO_WAY_ASYNC:
			type = OperationType.TWO_WAY_ASYNC;
			break;
		case TWO_WAY_SYNC:
			type = OperationType.TWO_WAY_SYNC;
			break;
		}
		return type;

	}

	private Interface initializeInterfaceObjectWithRole(
			InterfaceDescription inter) {

		Interface interfaceObj = null;
		eu.chorevolution.modelingnotations.gidl.RoleTypes roleNameEnum = inter.getRole();
		switch(roleNameEnum) {
		case PROVIDER:
			interfaceObj = new Interface(RoleType.SERVER);
			break;
		case CONSUMER:	
			interfaceObj = new Interface(RoleType.CLIENT);
			break;
		}
		return interfaceObj;

	}
}

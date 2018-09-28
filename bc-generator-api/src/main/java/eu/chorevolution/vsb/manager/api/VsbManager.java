package eu.chorevolution.vsb.manager.api;

//import eu.chorevolution.vsb.bc.generators.JarGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.reflect.Parameter;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JVar;

import eu.chorevolution.vsb.artifact.generators.Generator;
import eu.chorevolution.vsb.artifact.generators.JarGenerator;
import eu.chorevolution.vsb.artifact.generators.WarGenerator;
import eu.chorevolution.vsb.bc.manager.BcManagerRestService;
import eu.chorevolution.vsb.bc.manager.VsbOutput;
import eu.chorevolution.vsb.gm.protocols.coap.BcCoapSubcomponent;
import eu.chorevolution.vsb.gm.protocols.dpws.BcDPWSSubcomponent;
import eu.chorevolution.vsb.gm.protocols.mqtt.BcMQTTSubcomponent;
import eu.chorevolution.vsb.gm.protocols.primitives.BcGmSubcomponent;
import eu.chorevolution.vsb.gm.protocols.rest.BcRestSubcomponent;
import eu.chorevolution.vsb.gm.protocols.soap.BcSoapGenerator;
import eu.chorevolution.vsb.gm.protocols.soap.BcSoapSubcomponent;
import eu.chorevolution.vsb.gm.protocols.websocket.BcWebsocketSubcomponent;
import eu.chorevolution.vsb.gmdl.tools.serviceparser.ServiceDescriptionParser;
import eu.chorevolution.vsb.gmdl.utils.BcConfiguration;
import eu.chorevolution.vsb.gmdl.utils.Constants;
import eu.chorevolution.vsb.gmdl.utils.PathResolver;
import eu.chorevolution.vsb.gmdl.utils.GmServiceRepresentation;
import eu.chorevolution.vsb.gmdl.utils.enums.ProtocolType;
import eu.chorevolution.vsb.logger.GLog;
import eu.chorevolution.vsb.logger.Logger;

public class VsbManager{
	
	private boolean STARTING_FROM_JAR = false;
	private ProtocolType serviceProtocol = null;
	private ProtocolType busProtocol = null;
	private Logger logger = GLog.initLogger();
	private static HashMap<String, String> mapParameter = new HashMap<String, String>();

	public VsbManager() {

		// Test if class is running from jar file or from classes files.
		if (getClass().getClassLoader().getResource("config.json").toString().startsWith("jar")){

			STARTING_FROM_JAR = true;
		}
	}

	/**********************************************
	 * Public method to call
	 ***********************************************/

	/**
	 * Returns VsbOutput object which contains the generated 1 byte array of
	 * archive war and 1 array bytes of wsdl file
	 * <p>
	 *
	 * @param interfaceDescriptionPath
	 *            string path of a gidl or gmdl file
	 * @param busProtocol
	 *            the protocole type to use (SOAP, REST, ...)
	 * @param service_name
	 *            the name of the service will be generated
	 * @return VsbOutput object
	 */

	public VsbOutput generateWar(String interfaceDescriptionPath, ProtocolType busProtocol, String service_name) {

		service_name = deleteSpecialChar(service_name);
		Constants.service_name = service_name;
		VsbOutput vsbOutput = generate(interfaceDescriptionPath, busProtocol);
		return vsbOutput;
	}

	/**
	 * Returns VsbOutput object which contains the generated 1 byte array of
	 * archive war and 1 array bytes of wsdl file
	 * <p>
	 *
	 * @param interfaceDescriptionByteArray
	 *            array of a gidl or gmdl file
	 * @param busProtocol
	 *            the protocole type to use (SOAP, REST, ...)
	 * @param service_name
	 *            the name of the service will be generated
	 * @return VsbOutput object
	 */

	public VsbOutput generateWar(byte[] interfaceDescriptionByteArray, ProtocolType busProtocol, String service_name) {

		service_name = deleteSpecialChar(service_name);
		Constants.service_name = service_name;
		String interfaceDescriptionPath = interfaceDescriptionBytesToFile(interfaceDescriptionByteArray);
		VsbOutput vsbOutput = generate(interfaceDescriptionPath, busProtocol);

		File interfaceDescriptionFile = new File("interfaceDescription.gidl");
		if (interfaceDescriptionFile.exists()){

			interfaceDescriptionFile.delete();
		}
		return vsbOutput;
	}

	/**
	 * Returns boolean, it deletes all generated temporary files
	 * <p>
	 * 
	 * @return boolean
	 */
	

	public boolean deleteGeneratedFiles() {

		File directory = new File(Constants.generatedCodePath);
		// make sure directory exists
		if (!directory.exists()) {

			logger.e(this.getClass().getName(), "Delete request failed: Directory does not exist.");
			return false;
		}
		PathResolver.deleteTempDir(directory);
		return true;
	}

	public void setServiceEndpoint(String host, String port){
		
		mapParameter.put("host_service", host);
		mapParameter.put("port_service", port);
	}
	
	
    public void setBusEndpoint(String host, String port){
		
    	mapParameter.put("host_bus", host);
    	mapParameter.put("port_bus", port);
	}
	
	/******************************************************************************************************/

	/***************
	 * Private method called by public method
	 ************************************************/

	private void setConstants(String interfaceDescriptionPath){

		Random rand = new Random();
		Constants.service_port = String
				.valueOf(rand.nextInt(Constants.service_port_max_range - Constants.service_port_min_range + 1)
						+ Constants.service_port_min_range);
		Constants.setinvaddr_service_port = String.valueOf(rand
				.nextInt(Constants.setinvaddr_service_port_max_range - Constants.setinvaddr_service_port_min_range + 1)
				+ Constants.setinvaddr_service_port_min_range);
		Constants.service_bc_port = String
				.valueOf(rand.nextInt(Constants.service_bc_port_max_range - Constants.service_bc_port_min_range + 1)
						+ Constants.service_bc_port_min_range);
		Constants.bc_manager_servlet_port = String.valueOf(rand
				.nextInt(Constants.bc_manager_servlet_port_max_range - Constants.bc_manager_servlet_port_min_range + 1)
				+ Constants.bc_manager_servlet_port_min_range);
		
		Constants.subcomponent_port = String.valueOf(rand
				.nextInt(Constants.subcomponent_port_max_range - Constants.subcomponent_port_min_range + 1)
				+ Constants.subcomponent_port_min_range);
		
		
		Constants.printer_service_port = String.valueOf(rand
				.nextInt(Constants.printer_service_port_max_range - Constants.printer_service_port_min_range + 1)
				+ Constants.printer_service_port_min_range);
		
		Constants.generatedCodePath = PathResolver.createTempDir();

		Class[] classe = { BcManagerRestService.class };
		Constants.configFilePath = PathResolver.myFilePath(BcManagerRestService.class, "config.json");

		Constants.interfaceDescriptionFilePath = interfaceDescriptionPath;
		String artifact_generators_copy = Constants.generatedCodePath + File.separator + "artifact-generators";

		// Test if class is running from jar or from class file.
		if (STARTING_FROM_JAR){
			
			URL webapp_src_artifact = getClass().getClassLoader().getResource("webapp");
			if(webapp_src_artifact == null) {
				
				logger.e(this.getClass().getName(), "Could not find artifact-generators resource");
				return;
			}
			PathResolver.extractDirectoryFromJar(webapp_src_artifact, artifact_generators_copy);

		} else{

			PathResolver.copyFolder(new File(".." + File.separator + "artifact-generators" + File.separator + "src"+ File.separator +"main").getAbsolutePath(),
					artifact_generators_copy);
		}

		PathResolver.updatePortServiceSetInvAddr(Constants.setinvaddr_service_port);
		Constants.webapp_src_artifact = new File(artifact_generators_copy + File.separator +  File.separator + "webapp").getAbsolutePath();
		Constants.warName = "war" + Constants.service_name + "BC";
		Constants.wsdlName = "wsdl" + Constants.service_name + "BC";
		Constants.warDestination = new File(Constants.generatedCodePath + File.separator + Constants.warName + ".war")
				.getAbsolutePath();
		Constants.target_namespace = "eu.chorevolution.vsb.bindingcomponent.generated";
		Constants.target_namespace_path = Constants.target_namespace.replace(".", File.separator);
		Constants.soap_service_name = "BC" + Constants.service_name + "SoapEndpoint";
		Constants.dpws_service_name = "BC" + Constants.service_name + "DPWSEndpoint";
		Constants.wsdlDestination = new File(Constants.generatedCodePath).getAbsolutePath();
	}

	private VsbOutput generate(String interfaceDescriptionPath, ProtocolType busProtocol) {

		if (!isInterfaceDescriptionFile(interfaceDescriptionPath)) {

			logger.e(this.getClass().getName(), "Interface Description file not found or not good file extension");
			throw new EmptyStackException();
		}

		setConstants(interfaceDescriptionPath);

		generateBindingComponent(interfaceDescriptionPath, busProtocol);

		WarGenerator warGenerator = new WarGenerator();
		JarGenerator jarGenerator = new JarGenerator();

		warGenerator.addPackage(eu.chorevolution.vsb.manager.api.VsbManager.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gmdl.tools.serviceparser.ServiceDescriptionParser.class.getPackage());


		warGenerator.addPackage(eu.chorevolution.vsb.gmdl.tools.serviceparser.gidl.ParseGIDL.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gmdl.tools.serviceparser.gmdl.ParseGMDL.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gmdl.utils.Operation.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gmdl.utils.enums.OperationType.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gm.protocols.dpws.BcDPWSGenerator.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gm.protocols.mqtt.BcMQTTGenerator.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gm.protocols.websocket.BcWebsocketGenerator.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gm.protocols.coap.BcCoapGenerator.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gm.protocols.soap.BcSoapGenerator.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gm.protocols.rest.BcRestGenerator.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.bc.manager.BcManagerRestService.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.artifact.war.RestServlet.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.artifact.generators.WarGenerator.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.bc.setinvaddrservice.BaseService.class.getPackage());
		warGenerator.addPackage(eu.chorevolution.vsb.gm.protocols.Manageable.class.getPackage());
		
		String gm_soap_pomxml;
		String gm_coap_pomxml;
		String gm_dpws_pomxml;
		String gm_websocket_pomxl;
		String gm_mqtt_pomxl;
		String gm_rest_pomxl;
		String gm_soap_war_pomxml;

		if (STARTING_FROM_JAR){

			gm_soap_pomxml = PathResolver.myFilePath(BcManagerRestService.class, "pom-gm-soap.xml");
			gm_coap_pomxml = PathResolver.myFilePath(BcManagerRestService.class, "pom-gm-coap.xml");
			gm_dpws_pomxml = PathResolver.myFilePath(BcManagerRestService.class, "pom-gm-dpws.xml");
			gm_websocket_pomxl = PathResolver.myFilePath(BcManagerRestService.class, "pom-gm-websocket.xml");
			gm_rest_pomxl = PathResolver.myFilePath(BcManagerRestService.class, "pom-gm-rest.xml");
			gm_mqtt_pomxl = PathResolver.myFilePath(BcManagerRestService.class, "pom-gm-mqtt.xml");
			gm_soap_war_pomxml = PathResolver.myFilePath(BcManagerRestService.class, "pom-gm-soap-war.xml");
			
			
	

		} else{

			gm_soap_pomxml = new File(".").getAbsolutePath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "pom-gm-soap.xml";
			gm_coap_pomxml = new File(".").getAbsolutePath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "pom-gm-coap.xml";
			gm_dpws_pomxml = new File(".").getAbsolutePath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "pom-gm-dpws.xml";
			gm_websocket_pomxl = new File(".").getAbsolutePath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "pom-gm-websocket.xml";
			gm_rest_pomxl = new File(".").getAbsolutePath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "pom-gm-rest.xml";
			gm_mqtt_pomxl = new File(".").getAbsolutePath() + File.separator + "src" + File.separator + "main"
							+ File.separator + "resources" + File.separator + "pom-gm-mqtt.xml";
			gm_soap_war_pomxml = new File(".").getAbsolutePath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "pom-gm-soap-war.xml";

		}

		HashMap<String, String> hmapPomXml = new HashMap<String, String>();

		hmapPomXml.put("coap", gm_coap_pomxml);
		hmapPomXml.put("dpws", gm_dpws_pomxml);
		hmapPomXml.put("mqtt", gm_mqtt_pomxl);
		hmapPomXml.put("websocket", gm_websocket_pomxl);
		hmapPomXml.put("soap", gm_soap_pomxml);
		hmapPomXml.put("rest", gm_rest_pomxl);
		hmapPomXml.put("soapwar", gm_soap_war_pomxml);


		Generator generator = new Generator(busProtocol);
		VsbOutput vsbOutput = new VsbOutput();
		vsbOutput.generatedCodePath = Constants.generatedCodePath;
		vsbOutput.service_name = Constants.service_name;
		
		Class[] classesOptions = new Class[]{

				BcManagerRestService.class, BcGmSubcomponent.class, BcGmSubcomponent.class, BcWebsocketSubcomponent.class, BcRestSubcomponent.class,
				BcSoapSubcomponent.class, BcCoapSubcomponent.class, BcDPWSSubcomponent.class, BcMQTTSubcomponent.class,
				ServiceDescriptionParser.class, BcConfiguration.class, BcSoapSubcomponent.class, ObjectMapper.class,
				TypeFactory.class, Versioned.class, ResolvedType.class, JsonProperty.class 
		};
		
		if(busProtocol == ProtocolType.SOAP ){
			
			
			warGenerator.addDependencyFiles(hmapPomXml.get("soapwar"));
			generator.compileGeneratedClasses(classesOptions);
			warGenerator.setBusProtocol(this.busProtocol);
			warGenerator.setServiceProtocol(this.serviceProtocol);	
			vsbOutput = generator.generateWar(warGenerator, busProtocol);
			vsbOutput.service_bc_port = Constants.service_bc_port;
			vsbOutput.setinvaddr_service_port = Constants.setinvaddr_service_port;
			vsbOutput.service_port = Constants.service_port;
			vsbOutput.bc_manager_servlet_port = Constants.bc_manager_servlet_port;
			vsbOutput.printer_service_port = Constants.printer_service_port;
			vsbOutput.subcomponent_port = Constants.subcomponent_port;
			
		}else{
			
			
			if(busProtocol == ProtocolType.SOAP){
				
				
				vsbOutput.service_bc_port = Constants.service_bc_port;
				vsbOutput.setinvaddr_service_port = Constants.setinvaddr_service_port;
				vsbOutput.service_port = Constants.service_port;
				vsbOutput.bc_manager_servlet_port = Constants.bc_manager_servlet_port;
				vsbOutput.printer_service_port = Constants.printer_service_port;
				vsbOutput.subcomponent_port = Constants.subcomponent_port;
			}
			generator.compileGeneratedClasses(classesOptions);
			jarGenerator.setBusProtocol(this.busProtocol);
			jarGenerator.setServiceProtocol(this.serviceProtocol);
			jarGenerator.addDependencyFiles(hmapPomXml);
			vsbOutput.jar = generator.generateJar(jarGenerator);
			
		}
		return vsbOutput;
	}

	private void generateBindingComponent(final String interfaceDescription, final ProtocolType busProtocol) {

		copyInterfaceDescription(interfaceDescription);

		BcConfiguration bcConfiguration = null;
		bcConfiguration = new BcConfiguration();

		bcConfiguration.setGeneratedCodePath(Constants.generatedCodePath);

		GmServiceRepresentation gmServiceRepresentation = null;

		String interfaceDescFileExtension = "";
		String[] interfaceDescFileNameComponents = interfaceDescription.split("\\.");
		interfaceDescFileExtension = interfaceDescFileNameComponents[interfaceDescFileNameComponents.length - 1];

		switch (interfaceDescFileExtension) {
		case "gmdl":
			gmServiceRepresentation = ServiceDescriptionParser.getRepresentationFromGMDL(interfaceDescription);
			break;
		case "gidl":
			gmServiceRepresentation = ServiceDescriptionParser.getRepresentationFromGIDL(interfaceDescription);
			break;
		}

		this.busProtocol = busProtocol;
		this.serviceProtocol = gmServiceRepresentation.getProtocol();
		
		
		if (busProtocol == ProtocolType.SOAP){

			bcConfiguration.setTargetNamespace(Constants.target_namespace);
			bcConfiguration.setServiceName(Constants.soap_service_name);

			BcSoapGenerator soapGenerator = (BcSoapGenerator) new BcSoapGenerator(gmServiceRepresentation,
					bcConfiguration).setDebug(false);
			soapGenerator.generatePOJOAndEndpoint();
			soapGenerator.generateWSDL();

		}
		generateBindingComponentClass(gmServiceRepresentation, busProtocol);
		if(busProtocol != ProtocolType.SOAP) {
			
			generateBindingComponentMainClass();
		}
		
		
	}

	private void copyInterfaceDescription(String interfaceDescription){

		try {

			File input = new File(interfaceDescription);
			File output = new File(Constants.webapp_src_artifact + File.separator + "config" + File.separator
					+ "serviceDescription.gxdl");
			Scanner sc = new Scanner(input);
			PrintWriter printer = new PrintWriter(output);
			while (sc.hasNextLine()) {

				String s = sc.nextLine();
				printer.write(s);
			}
			sc.close();
			printer.close();
		} catch (FileNotFoundException e) {

			logger.e(this.getClass().getName(), "Interface file not found.");
		}
	}

	private static void generateBindingComponentClass(GmServiceRepresentation gmServiceRepresentation,ProtocolType busProtocol){

		String configTemplatePath = "";
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		configTemplatePath = Constants.configFilePath;

		try {
			jsonObject = (JSONObject) parser.parse(new FileReader(configTemplatePath));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		/* Creating java code model classes */
		JCodeModel jCodeModel = new JCodeModel();
		/* Adding package here */
		JPackage jp = jCodeModel._package((String) jsonObject.get("target_namespace"));

		/* Giving Class Name to Generate */
		JDefinedClass jc = null;
		try {

			jc = jp._class("BindingComponent");

		} catch (JClassAlreadyExistsException e) {

			e.printStackTrace();
		}

		JClass BcGmSubcomponentClass = jCodeModel
				.ref(eu.chorevolution.vsb.gm.protocols.primitives.BcGmSubcomponent.class);
		JFieldVar ComponentsVar = jc.field(JMod.NONE, BcGmSubcomponentClass.array().array(), "subcomponent");

		JClass GmServiceRepresentationClass = jCodeModel
				.ref(eu.chorevolution.vsb.gmdl.utils.GmServiceRepresentation.class);
		JFieldVar GmServiceRepresentationVar = jc.field(JMod.NONE, GmServiceRepresentationClass,
				"gmServiceRepresentation", JExpr._null());

		JMethod jmCreate = jc.method(JMod.PUBLIC, void.class, "run");

		/* Adding method body */
		JBlock jBlock = jmCreate.body();

		JClass integerClass = jCodeModel.ref(java.lang.Integer.class);
		JClass StringClass = jCodeModel.ref(java.lang.String.class);

		JVar intFiveVar = jBlock.decl(integerClass, "intFive");
		jBlock.assign(JExpr.ref(intFiveVar.name()), jCodeModel.ref("Integer").staticInvoke("parseInt").arg("5"));

		JVar intOneVar = jBlock.decl(integerClass, "intOne");
		jBlock.assign(JExpr.ref(intOneVar.name()), jCodeModel.ref("Integer").staticInvoke("parseInt").arg("1"));

		JVar intNineVar = jBlock.decl(integerClass, "intNine");
		jBlock.assign(JExpr.ref(intNineVar.name()), jCodeModel.ref("Integer").staticInvoke("parseInt").arg("9"));

		JClass BcManagerClass = null;
		BcManagerClass = jCodeModel.ref(eu.chorevolution.vsb.bc.manager.BcManagerRestService.class);
		JClass ConstantsClass = jCodeModel.ref(eu.chorevolution.vsb.gmdl.utils.Constants.class);

		JClass FileClass = jCodeModel.ref(java.io.File.class);
		JClass BcManagerRestServiceClass = jCodeModel.ref(eu.chorevolution.vsb.bc.manager.BcManagerRestService.class);

		
		
		JClass pathResolverClass = null;
		pathResolverClass = jCodeModel.ref(eu.chorevolution.vsb.gmdl.utils.PathResolver.class);

		JVar pathResolverClassVar = jBlock.decl(pathResolverClass, "pathResolver").init(JExpr._new(pathResolverClass));

		JInvocation myFilePath = pathResolverClassVar.invoke("myFilePath").arg(jc.dotclass())
				.arg((JExpr._new(StringClass).arg("config")).plus(FileClass.staticRef("separator"))
						.plus(JExpr._new(StringClass).arg("serviceDescription.gxdl")));

		JVar interfaceDescriptionPathVar = null;
		interfaceDescriptionPathVar = jBlock.decl(StringClass, "interfaceDescFilePath");
		jBlock.assign(JExpr.ref(interfaceDescriptionPathVar.name()), myFilePath);

		JClass SystemClass = jCodeModel.ref(System.class);
		JInvocation printstmt = SystemClass.staticRef("out").invoke("println").arg(interfaceDescriptionPathVar);
		jBlock.add(printstmt);



		JClass serviceDescriptionClass = jCodeModel.ref(ServiceDescriptionParser.class);

		String interfaceDescriptionPath = Constants.interfaceDescriptionFilePath;
		String extension = "";
		String[] interfaceDescPieces = interfaceDescriptionPath.split("\\.");

		extension = interfaceDescPieces[interfaceDescPieces.length - 1];
		JInvocation getInterfaceRepresentation = null;

		switch (extension) {

		case "gmdl":
			getInterfaceRepresentation = serviceDescriptionClass.staticInvoke("getRepresentationFromGMDL")
					.arg(interfaceDescriptionPathVar);
			break;
		case "gidl":
			getInterfaceRepresentation = serviceDescriptionClass.staticInvoke("getRepresentationFromGIDL")
					.arg(interfaceDescriptionPathVar);
			break;
		}

		jBlock.assign(GmServiceRepresentationVar, getInterfaceRepresentation);

		jBlock.decl(jCodeModel.INT, "num_interfaces",
				GmServiceRepresentationVar.invoke("getInterfaces").invoke("size"));

		jBlock.assign(ComponentsVar, JExpr.ref("new BcGmSubcomponent[num_interfaces][2]"));

		JForLoop forLoop = jBlock._for();
		JVar ivar = forLoop.init(jCodeModel.INT, "i", JExpr.lit(0));
		forLoop.test(ivar.lt(GmServiceRepresentationVar.invoke("getInterfaces").invoke("size")));
		forLoop.update(ivar.assignPlus(JExpr.lit(1)));

		JBlock forBlock = forLoop.body();

		JClass InterfaceClass = jCodeModel.ref(eu.chorevolution.vsb.gmdl.utils.Interface.class);
		JVar InterfaceVar = forBlock.decl(InterfaceClass, "inter", JExpr._null());

		forBlock.assign(JExpr.ref(InterfaceVar.name()),
				GmServiceRepresentationVar.invoke("getInterfaces").invoke("get").arg(ivar));

		JClass RoleTypeClass = jCodeModel.ref(eu.chorevolution.vsb.gmdl.utils.enums.RoleType.class);
		JClass BcRestSubcomponentClass = jCodeModel
				.ref(eu.chorevolution.vsb.gm.protocols.rest.BcRestSubcomponent.class);
		JClass BcSoapSubcomponentClass = jCodeModel
				.ref(eu.chorevolution.vsb.gm.protocols.soap.BcSoapSubcomponent.class);
		JClass BcMQTTSubcomponentClass = jCodeModel
				.ref(eu.chorevolution.vsb.gm.protocols.mqtt.BcMQTTSubcomponent.class);
		JClass BcCoapSubcomponentClass = jCodeModel
				.ref(eu.chorevolution.vsb.gm.protocols.coap.BcCoapSubcomponent.class);
		JClass BcDpwsSubcomponentClass = jCodeModel
				.ref(eu.chorevolution.vsb.gm.protocols.dpws.BcDPWSSubcomponent.class);
		JClass BcWebSocketSubcomponentClass = jCodeModel
				.ref(eu.chorevolution.vsb.gm.protocols.websocket.BcWebsocketSubcomponent.class);

		JClass BcConfigurationClass = jCodeModel.ref(eu.chorevolution.vsb.gmdl.utils.BcConfiguration.class);
		

		JClass EnumClass = jCodeModel.ref("eu.chorevolution.vsb.gmdl.utils.enums.RoleType");

		JFieldRef RoleTypeServerEnum = null;
		JFieldRef RoleTypeClientEnum = null;

		RoleTypeServerEnum = EnumClass.staticRef("SERVER");
		RoleTypeClientEnum = EnumClass.staticRef("CLIENT");

		JVar RoleTypeClassVar = forBlock.decl(RoleTypeClass, "busRole");

		JConditional roleCondition = forBlock._if(InterfaceVar.invoke("getRole").eq(RoleTypeServerEnum));
		roleCondition._then().assign(JExpr.ref(RoleTypeClassVar.name()), RoleTypeClientEnum);
		roleCondition._else().assign(JExpr.ref(RoleTypeClassVar.name()), RoleTypeServerEnum);

		JVar bcConfig1Var = forBlock.decl(BcConfigurationClass, "bcConfiguration1", JExpr._new(BcConfigurationClass));
		JVar bcConfig2Var = forBlock.decl(BcConfigurationClass, "bcConfiguration2", JExpr._new(BcConfigurationClass));

		JInvocation setRole1 = bcConfig1Var.invoke("setSubcomponentRole").arg(InterfaceVar.invoke("getRole"));
		forBlock.add(setRole1);

		JInvocation setRole2 = bcConfig2Var.invoke("setSubcomponentRole").arg(RoleTypeClassVar);
		forBlock.add(setRole2);

		String packagePath = Constants.target_namespace;
		packagePath = packagePath.replace(".", File.separator);

		String generatedCodePath = Constants.generatedCodePath;
		generatedCodePath = generatedCodePath + File.separator;

		
		JInvocation myFilePath1 = pathResolverClassVar.invoke("myFilePath").arg(jc.dotclass())
				.arg((JExpr._new(StringClass).arg("config")).plus(FileClass.staticRef("separator"))
						.plus(JExpr._new(StringClass).arg("config_block1_interface_")).plus(jCodeModel
								.ref(java.lang.String.class).staticInvoke("valueOf").arg(ivar.plus(intOneVar))));

		JVar config_block1_interfacePathVar = forBlock.decl(StringClass, "config_block1_interfacePath");
		forBlock.assign(JExpr.ref(config_block1_interfacePathVar.name()), myFilePath1);

		JInvocation parseInvocation1 = bcConfig1Var.invoke("parseFromJSON").arg(GmServiceRepresentationVar)
				.arg(JExpr._new(StringClass).arg(config_block1_interfacePathVar));

	

		forBlock.add(parseInvocation1);

		JInvocation myFilePath2 = pathResolverClassVar.invoke("myFilePath").arg(jc.dotclass())
				.arg((JExpr._new(StringClass).arg("config")).plus(FileClass.staticRef("separator"))
						.plus(JExpr._new(StringClass).arg("config_block2_interface_")).plus(jCodeModel
								.ref(java.lang.String.class).staticInvoke("valueOf").arg(ivar.plus(intOneVar))));

		JVar config_block2_interfacePathVar = forBlock.decl(StringClass, "config_block2_interfacePath");
		forBlock.assign(JExpr.ref(config_block2_interfacePathVar.name()), myFilePath2);

		JInvocation parseInvocation2 = bcConfig2Var.invoke("parseFromJSON").arg(GmServiceRepresentationVar)
				.arg(JExpr._new(StringClass).arg(config_block2_interfacePathVar));


		forBlock.add(parseInvocation2);


		switch (busProtocol){
		case REST:

			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileBusProtocole(ProtocolType.REST, gmServiceRepresentation.getHostAddress(),Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block1_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][0]"),
					JExpr._new(BcRestSubcomponentClass).arg(bcConfig1Var).arg(GmServiceRepresentationVar));
			// BcGmSubcomponentVar1.init(JExpr._new(BcRestSubcomponentClass).arg(bcConfig1Var));
			break;
		
		case SOAP:
			for(int i=1; i<=gmServiceRepresentation.getInterfaces().size(); i++)  
				createConfigFileBusProtocole(ProtocolType.SOAP, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block1_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][0]"), JExpr._new(BcSoapSubcomponentClass).arg(bcConfig1Var).arg(GmServiceRepresentationVar));
			

			break;
		case MQTT:
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileBusProtocole(ProtocolType.MQTT, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block1_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][0]"),
					JExpr._new(BcMQTTSubcomponentClass).arg(bcConfig1Var).arg(GmServiceRepresentationVar));
			break;
		case COAP:
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileBusProtocole(ProtocolType.COAP, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block1_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][0]"),
					JExpr._new(BcCoapSubcomponentClass).arg(bcConfig1Var).arg(GmServiceRepresentationVar));
			break;

		case DPWS:
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileBusProtocole(ProtocolType.DPWS, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block1_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][0]"),
					JExpr._new(BcDpwsSubcomponentClass).arg(bcConfig1Var).arg(GmServiceRepresentationVar));
			break;

		case JMS:
			break;
		case PUB_NUB:
			break;
		case SEMI_SPACE:
			break;
		case WEB_SOCKETS:
			
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileBusProtocole(ProtocolType.WEB_SOCKETS, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block1_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][0]"),
					JExpr._new(BcWebSocketSubcomponentClass).arg(bcConfig1Var).arg(GmServiceRepresentationVar));
			
			
			break;
		case ZERO_MQ:
			break;
		default:
			break;
		}

		
		switch(gmServiceRepresentation.getProtocol()){
		
		case REST:
			for(int i=1; i<=gmServiceRepresentation.getInterfaces().size(); i++)  
				createConfigFileGmServiceProtocole(ProtocolType.REST, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator + "config" + File.separator + "config_block2_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][1]"), JExpr._new(BcRestSubcomponentClass).arg(bcConfig2Var).arg(GmServiceRepresentationVar));
			
			
			break;
		case SOAP:
			for(int i=1; i<=gmServiceRepresentation.getInterfaces().size(); i++)  
				createConfigFileGmServiceProtocole(ProtocolType.SOAP, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator + "config" + File.separator + "config_block2_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][1]"), JExpr._new(BcSoapSubcomponentClass).arg(bcConfig2Var).arg(GmServiceRepresentationVar));

			
			break;
		case MQTT:
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileGmServiceProtocole(ProtocolType.MQTT, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block2_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][1]"),
					JExpr._new(BcMQTTSubcomponentClass).arg(bcConfig2Var).arg(GmServiceRepresentationVar));
			break;
		case COAP:
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileGmServiceProtocole(ProtocolType.COAP, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block2_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][1]"),
					JExpr._new(BcCoapSubcomponentClass).arg(bcConfig2Var).arg(GmServiceRepresentationVar));
			break;

		case DPWS:
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileGmServiceProtocole(ProtocolType.DPWS, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block2_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][1]"),
					JExpr._new(BcDpwsSubcomponentClass).arg(bcConfig2Var).arg(GmServiceRepresentationVar));
			break;
			
		case WEB_SOCKETS:
				
			for (int i = 1; i <= gmServiceRepresentation.getInterfaces().size(); i++)
				createConfigFileGmServiceProtocole(ProtocolType.WEB_SOCKETS, gmServiceRepresentation.getHostAddress(), Constants.webapp_src_artifact + File.separator
						+ "config" + File.separator + "config_block2_interface_" + String.valueOf(i));
			forBlock.assign(JExpr.ref("subcomponent[i][1]"),
					JExpr._new(BcWebSocketSubcomponentClass).arg(bcConfig2Var).arg(GmServiceRepresentationVar));
			
			break;
			

		case JMS:
			break;
		case PUB_NUB:
			break;
		case SEMI_SPACE:
			break;
		case ZERO_MQ:
			break;
		default:
			break;
		}

		JVar BcGmSubcomponentVar1 = forBlock.decl(BcGmSubcomponentClass, "block1Component", JExpr.ref("subcomponent[i][0]"));
		JVar BcGmSubcomponentVar2 = forBlock.decl(BcGmSubcomponentClass, "block2Component", JExpr.ref("subcomponent[i][1]"));

		forBlock.add(BcGmSubcomponentVar1.invoke("setNextComponent").arg(BcGmSubcomponentVar2));
		forBlock.add(BcGmSubcomponentVar2.invoke("setNextComponent").arg(BcGmSubcomponentVar1));

		forBlock.add(BcGmSubcomponentVar1.invoke("start"));
		forBlock.add(BcGmSubcomponentVar2.invoke("start"));

		JMethod jmCreatePause = jc.method(JMod.PUBLIC, void.class, "pause");
		JBlock jBlockPause = jmCreatePause.body();

		JForLoop forLoopPause = jBlockPause._for();
		JVar ivarPause = forLoopPause.init(jCodeModel.INT, "i", JExpr.lit(0));
		forLoopPause.test(ivarPause.lt(GmServiceRepresentationVar.invoke("getInterfaces").invoke("size")));
		forLoopPause.update(ivarPause.assignPlus(JExpr.lit(1)));

		JBlock forBlockPause = forLoopPause.body();

		JInvocation printstmtPause = SystemClass.staticRef("out").invoke("println").arg("genfac stop iteration");
		forBlockPause.add(printstmtPause);

		JVar BcGmSubcomponentVar1Pause = forBlockPause.decl(BcGmSubcomponentClass, "block1Component",
				JExpr.ref("subcomponent[i][0]"));
		JVar BcGmSubcomponentVar2Pause = forBlockPause.decl(BcGmSubcomponentClass, "block2Component",
				JExpr.ref("subcomponent[i][1]"));

		forBlockPause.add(BcGmSubcomponentVar1Pause.invoke("stop"));
		forBlockPause.add(BcGmSubcomponentVar2Pause.invoke("stop"));

		try {

			jCodeModel.build(new File(generatedCodePath));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void generateBindingComponentMainClass(){

		String configTemplatePath = "";
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		configTemplatePath = Constants.configFilePath;

		try {
			jsonObject = (JSONObject) parser.parse(new FileReader(configTemplatePath));
		} catch (IOException | ParseException e){
			
			e.printStackTrace();
		}

		/* Creating java code model classes */
		JCodeModel jCodeModel = new JCodeModel();
		
		/* Adding package here */
		JPackage jp = jCodeModel._package((String) jsonObject.get("target_namespace"));

		/* Giving Class Name to Generate */
		JDefinedClass jc = null;
		try {

			jc = jp._class("BindingComponentMain");

		} catch (JClassAlreadyExistsException e) {

			e.printStackTrace();
		}

		JClass StringClass = jCodeModel.ref(String.class);
		JClass bindingComponent = jCodeModel.ref(Constants.target_namespace + ".BindingComponent");

		JMethod jmMain = jc.method(JMod.PUBLIC | JMod.STATIC, void.class, "main");
		jmMain.param(StringClass.array(), "args");
		JBlock jmMainBlock = jmMain.body();
		JVar bindingComponentVar = jmMainBlock.decl(bindingComponent, "bindingComponent")
				.init(JExpr._new(bindingComponent));

		JInvocation run = bindingComponentVar.invoke("run");
		jmMainBlock.add(run);
		try{
			
			jCodeModel.build(new File(Constants.generatedCodePath + File.separator));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	private static void createConfigFileBusProtocole(ProtocolType protocol, String host,  String filename){
		
		

    	
    	
		String configPath = PathResolver.myFilePath(BcManagerRestService.class, "config.json");
		JSONParser configParser = new JSONParser();
		JSONObject configJsonObject = null;

		String configTemplatePath = PathResolver.myFilePath(BcManagerRestService.class, "template-config.json");

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			configJsonObject = (JSONObject) configParser.parse(new FileReader(configPath));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		try {

			jsonObject = (JSONObject) parser.parse(new FileReader(configTemplatePath));

		} catch (IOException | ParseException e) {

			e.printStackTrace();
		}

		String host_bus = mapParameter.get("host_bus");
		String port = mapParameter.get("port_bus");
		
		switch (protocol) {

		case REST:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_bus);
			jsonObject.put("invocation_address", host_bus);

			break;
		case SOAP:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", Constants.service_port);
			jsonObject.put("bc_rest_service_port", Constants.service_bc_port);
			jsonObject.put("bc_manager_servlet_port", Constants.bc_manager_servlet_port);
			jsonObject.put("subcomponent_address", "http://localhost");
			jsonObject.put("invocation_address", host_bus);

			break;

		case MQTT:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_bus);
			jsonObject.put("invocation_address", host_bus);

			break;
		case COAP:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_bus);
			jsonObject.put("invocation_address", host_bus);

			break;

		case DPWS:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_bus);
			jsonObject.put("invocation_address", host_bus);

			break;

		case WEB_SOCKETS:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_bus);
			jsonObject.put("invocation_address", host_bus);

			break;

		}

		// temporarily disabled
		try (FileWriter file = new FileWriter(filename)) {
			file.write(jsonObject.toJSONString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void createConfigFileGmServiceProtocole(ProtocolType protocol, String host, String filename) {

		String configPath = PathResolver.myFilePath(BcManagerRestService.class, "config.json");
		JSONParser configParser = new JSONParser();
		JSONObject configJsonObject = null;

		String configTemplatePath = PathResolver.myFilePath(BcManagerRestService.class, "template-config.json");
		;
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {

			configJsonObject = (JSONObject) configParser.parse(new FileReader(configPath));

		} catch (IOException | ParseException e){

			e.printStackTrace();
		}

		try {

			jsonObject = (JSONObject) parser.parse(new FileReader(configTemplatePath));

		} catch (IOException | ParseException e) {

			e.printStackTrace();
		}
		
		String host_service = mapParameter.get("host_service");
		String port = mapParameter.get("port_service");
		
		switch (protocol) {

		case REST:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", "1112");
			jsonObject.put("service_port", "1113");
			jsonObject.put("subcomponent_address", host_service+":"+port);
			jsonObject.put("invocation_address", "http://"+host_service+":"+port+"/");


			break;
		case SOAP:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", Constants.service_port);
			jsonObject.put("bc_rest_service_port", Constants.service_bc_port);
			jsonObject.put("bc_manager_servlet_port", Constants.bc_manager_servlet_port);
			jsonObject.put("subcomponent_address", "http://localhost");
			jsonObject.put("invocation_address", host_service);

			break;

		case MQTT:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_service);
			jsonObject.put("invocation_address", host_service);

			break;
		case COAP:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_service);
			jsonObject.put("invocation_address", host_service);

			break;

		case DPWS:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_service);
			jsonObject.put("invocation_address",host_service);

			break;

		case WEB_SOCKETS:

			jsonObject.put("target_namespace", Constants.target_namespace);
			jsonObject.put("service_name", Constants.soap_service_name);
			jsonObject.put("subcomponent_port", port);
			jsonObject.put("service_port", port);
			jsonObject.put("subcomponent_address", host_service);
			jsonObject.put("invocation_address", host_service);

			break;

		}

		// temporarily disabled
		try (FileWriter file = new FileWriter(filename)){

			file.write(jsonObject.toJSONString());

		} catch (IOException e) {System.out.println(e.getMessage());}
	}

	private static boolean isInterfaceDescriptionFile(String interfaceDescriptionPath) {

		boolean isGoodFile = false;
		File interfaceDescriptionFile = new File(interfaceDescriptionPath);

		if (!interfaceDescriptionFile.isFile()) {

			return isGoodFile;
		}
		String[] interfaceDescription = interfaceDescriptionFile.getName().split("\\.");
		String interfaceDescriptionExtension = interfaceDescription[interfaceDescription.length - 1];

		switch (interfaceDescriptionExtension){

		case "gmdl":

			isGoodFile = true;
			break;

		case "gidl":

			isGoodFile = true;
			break;
		}
		return isGoodFile;
	}

	private static String interfaceDescriptionBytesToFile(byte[] interfaceDescriptionByteArray) {

		File interfaceDescriptionFile = new File("interfaceDescription.gidl");
		FileOutputStream fos;
		try {

			fos = new FileOutputStream(interfaceDescriptionFile);
			fos.write(interfaceDescriptionByteArray);
			fos.flush();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return interfaceDescriptionFile.getAbsolutePath();
	}

	private static String deleteSpecialChar(String s) {

		StringBuilder result = new StringBuilder();
		boolean firstChar = true;

		for (char ch : s.toCharArray()) {
			if (firstChar) {
				firstChar = false;
				if (Character.isJavaIdentifierStart(ch)) {
					result.append(ch);
				}
				continue;
			}
			if (Character.isJavaIdentifierPart(ch)) {
				result.append(ch);
			}
		}
		return result.toString();
	}
	/******************************************************************************************************/

}

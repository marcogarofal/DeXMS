/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zefxis.dexms.artifact.generators;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.impl.base.exporter.zip.ZipExporterImpl;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.BuiltProject;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.EmbeddedMaven;
import org.zefxis.dexms.gmdl.utils.Constants;
import org.zefxis.dexms.gmdl.utils.PathResolver;
import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.mediator.manager.MediatorOutput;



public class WarGenerator {

	private WebArchive archive = null;
	private ProtocolType serviceProtocol = null;
	private ProtocolType busProtocol = null;
	
	public WarGenerator() {
		
		String warDestination = Constants.warDestination;
		archive = ShrinkWrap.create(WebArchive.class, warDestination);
		
	}

	public void addPackage(Package pack){
		
		archive.addPackage(pack);
	}
	
	
	public ProtocolType getServiceProtocol(){
		
		return serviceProtocol;
	}

	public void setServiceProtocol(ProtocolType serviceProtocol){
		
		this.serviceProtocol = serviceProtocol;
	}

	public ProtocolType getBusProtocol() {
		return busProtocol;
	}

	public void setBusProtocol(ProtocolType busProtocol) {

		this.busProtocol = busProtocol;
	}
	
	public void addDependencyFiles(HashMap<String, String> hmapPomXml){
		
		if (busProtocol == ProtocolType.SOAP || serviceProtocol == ProtocolType.SOAP) {

			buidAndMergeArchives( hmapPomXml.get("soap"));
		}

		if (busProtocol == ProtocolType.COAP || serviceProtocol == ProtocolType.COAP) {

			buidAndMergeArchives( hmapPomXml.get("coap"));
		}

		if (busProtocol == ProtocolType.REST || serviceProtocol == ProtocolType.REST) {

			buidAndMergeArchives( hmapPomXml.get("rest"));
		}

		if (busProtocol == ProtocolType.MQTT || serviceProtocol == ProtocolType.MQTT) {

			buidAndMergeArchives( hmapPomXml.get("mqtt"));
		}

		if (busProtocol == ProtocolType.WEB_SOCKETS || serviceProtocol == ProtocolType.WEB_SOCKETS) {

			buidAndMergeArchives( hmapPomXml.get("websocket"));
		}

		if (busProtocol == ProtocolType.DPWS || serviceProtocol == ProtocolType.DPWS) {

			buidAndMergeArchives( hmapPomXml.get("dpws"));
		}
		
	}
	public void addDependencyFiles(String pathToPom){
		
		BuiltProject builtProject = EmbeddedMaven.forProject(new File(pathToPom))
		   .useMaven3Version("3.3.9")
		   .setGoals("package")
		   .build();
		
		List<Archive> jars =  builtProject.getArchives();
		for(Archive jar : jars ){
			
			archive = archive.merge(jar);
		}
		PathResolver.deleteTempDir(new File(builtProject.getTargetDirectory().getAbsolutePath()));
		
	}
	
	public MediatorOutput generate(boolean isBusProtocolSoap){
		
		String packBindingcomponent = "eu.chorevolution.vsb.bindingcomponent".replace(".", File.separator);
		archive.addPackage(Constants.generatedCodePath+File.separator+packBindingcomponent);
		
		String WEBAPP_SRC_ARTIFACT = Constants.webapp_src_artifact;
		
		archive.setWebXML(new File(WEBAPP_SRC_ARTIFACT, "WEB-INF" + File.separator + "web.xml"));
		
		archive.addAsWebResource(new File(WEBAPP_SRC_ARTIFACT, "index.jsp"));

		for (File f : new File(WEBAPP_SRC_ARTIFACT + File.separator + "WEB-INF").listFiles()) {
			
			if(f.getName().equals("setinvaddr-context.xml")){
				
				archive.addAsWebResource(f, "WEB-INF" + "/" + f.getName());	
			}
			
		}
		
		
       for (File f : new File(Constants.generatedCodePath + File.separator + Constants.target_namespace_path).listFiles()) {
				
    	    	archive.addAsWebResource(f, "WEB-INF" +"/"+  "classes" + "/"+ Constants.target_namespace_path + "/"+ f.getName());
		}


		for (File f : new File(WEBAPP_SRC_ARTIFACT + File.separator + "config").listFiles()) {
			
			archive.addAsWebResource(f, "config" + "/" + f.getName());
		}

		for (File f : new File(WEBAPP_SRC_ARTIFACT + File.separator + "assets" + File.separator + "css").listFiles()) {
			archive.addAsWebResource(f, "assets" + "/" + "css" + "/" + f.getName());
		}

		for (File f : new File(WEBAPP_SRC_ARTIFACT + File.separator + "assets" + File.separator + "fonts").listFiles()) {
			archive.addAsWebResource(f, "assets" + "/" + "fonts" + "/" + f.getName());
		}

		for (File f : new File(WEBAPP_SRC_ARTIFACT + File.separator + "assets" + File.separator + "img").listFiles()) {
			archive.addAsWebResource(f, "assets" + "/" + "img" + "/" + f.getName());
		}

		for (File f : new File(WEBAPP_SRC_ARTIFACT + File.separator + "assets" + File.separator + "js").listFiles()) {
			archive.addAsWebResource(f, "assets" + "/" + "js" + "/" + f.getName());
		}

		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		new ZipExporterImpl(archive).exportTo(bos);

    	new ZipExporterImpl(archive).exportTo(new File(archive.getName()), true);

		MediatorOutput vsbOutput = new MediatorOutput();
		vsbOutput.war = bos.toByteArray();
		if(isBusProtocolSoap){
			
			String path1  = Constants.wsdlDestination +File.separator+Constants.wsdlName+".wsdl";
			Path path = Paths.get(path1);
			
			try {
				vsbOutput.wsdl = Files.readAllBytes(path);
			} catch (IOException e){
				
				e.printStackTrace();
			}
		}
		
		return vsbOutput;
	}
	
	public void addArchive(Archive archive){
		
		this.archive = this.archive.merge(archive);
	}
	
	private void buidAndMergeArchives(String pomfile) {

		BuiltProject builtProject = EmbeddedMaven
				                             .forProject(pomfile)
				                             .useMaven3Version("3.3.9")
				                             .setGoals("package")
				                             .build();
		
		List<Archive> jars =  builtProject.getArchives();
		for (Archive jar : jars) {

			archive = archive.merge(jar);
		}
		PathResolver.deleteTempDir(builtProject.getTargetDirectory());

	}
	
}

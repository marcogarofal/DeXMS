package org.zefxis.dexms.artifact.generators;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.impl.base.exporter.zip.ZipExporterImpl;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.BuiltProject;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.EmbeddedMaven;
import org.zefxis.dexms.gmdl.utils.Constants;
import org.zefxis.dexms.gmdl.utils.PathResolver;
import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;


/* TODO
 * @see http://stackoverflow.com/questions/8522443/generate-jar-file-during-runtime
 * @see http://www.hostettler.net/blog/2012/04/05/programmatically-build-web-archives-using-shrinkwrap/
 * @see https://github.com/shrinkwrap
 * @see http://arquillian.org/guides/shrinkwrap_introduction/
 * */

public class JarGenerator {

	private JavaArchive archive = null;
	private ProtocolType serviceProtocol = null;
	private ProtocolType busProtocol = null;

	public JarGenerator() {

		
		archive = ShrinkWrap.create(JavaArchive.class, Constants.service_name + ".jar");

	}

	public byte[] generate() {
		
		
		JavaArchive archiveTmp = ShrinkWrap.create(JavaArchive.class, Constants.service_name + ".jar");
		archiveTmp.as(ExplodedImporter.class)
				.importDirectory(new File(Constants.webapp_src_artifact + File.separator + "config"));
		archive.merge(archiveTmp, ArchivePaths.create("config"));

		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(new Attributes.Name("Manifest-Version"), "1.0");
		manifest.getMainAttributes().put(new Attributes.Name("Created-By"), "Mediator Generator");
		manifest.getMainAttributes().put(new Attributes.Name("Main-Class"),"org.zefxis.dexms.mediator.generated.MediatorMain");
		manifest.getMainAttributes().put(new Attributes.Name("Class-Path"),". WEB-INF/lib/");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{

			manifest.write(out);

		}catch (IOException e){

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayAsset byteArrayManifestAsset = new ByteArrayAsset(out.toByteArray());
		archive.add(byteArrayManifestAsset, JarFile.MANIFEST_NAME);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		new ZipExporterImpl(archive).exportTo(bos);
		new ZipExporterImpl(archive).exportTo(
				new File(Constants.generatedCodePath + File.separator + Constants.service_name + ".jar"), true);
		return bos.toByteArray();
	}

	public ProtocolType getServiceProtocol(){
		
		return serviceProtocol;
	}

	public void setServiceProtocol(ProtocolType serviceProtocol){
		
		this.serviceProtocol = serviceProtocol;
	}

	public ProtocolType getBusProtocol(){
		
		return busProtocol;
	}

	public void setBusProtocol(ProtocolType busProtocol) {

		this.busProtocol = busProtocol;
	}

	public void addDependencyFiles(HashMap<String, String> hmapPomXml) {

		
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

			buidAndMergeArchives(hmapPomXml.get("dpws"));
		}
		if (busProtocol == ProtocolType.HTTPS || serviceProtocol == ProtocolType.HTTPS) {

			buidAndMergeArchives(hmapPomXml.get("https"));
		}
		if (busProtocol == ProtocolType.COAPS || serviceProtocol == ProtocolType.COAPS) {

			buidAndMergeArchives(hmapPomXml.get("coaps"));
		}
		if (busProtocol == ProtocolType.MQTTS || serviceProtocol == ProtocolType.MQTTS) {

			buidAndMergeArchives(hmapPomXml.get("mqtts"));
		}
	}
	
	
	public void addArchive(Archive archive){
		
		this.archive = this.archive.merge(archive);
	}
	
	private void buidAndMergeArchives(String pomfile){

		BuiltProject builtProject = EmbeddedMaven.forProject(pomfile).useMaven3Version("3.3.9").setGoals("package")
				.build();

		List<Archive> jars =  builtProject.getArchives();
		for (Archive jar : jars) {

			archive = archive.merge(jar);
		}
		PathResolver.deleteTempDir(builtProject.getTargetDirectory());

	}

}
package org.zefxis.dexms.artifact.generators;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.BuiltProject;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.EmbeddedMaven;
import org.zefxis.dexms.gmdl.utils.Constants;
import org.zefxis.dexms.gmdl.utils.PathResolver;
import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.mediator.manager.MediatorOutput;
import org.zefxis.dexms.tools.compiler.RunTimeCompiler;



public class Generator {
	
	private JavaArchive archive; 
	private ProtocolType busProtocol;
	private Class<?> bc = null;
	public Generator(ProtocolType busProtocol){
		
		this.busProtocol = busProtocol;	
	} 
	
	public void compileGeneratedClasses(Class[] classesOptions){
		
		
		 String sourceFilesPath = Constants.generatedCodePath + File.separator+ Constants.target_namespace_path;
		 RunTimeCompiler compiler = new RunTimeCompiler(sourceFilesPath,Constants.generatedCodePath,classesOptions);
		 compiler.compile();
		 File sourceDir = new File(sourceFilesPath); 
		 for(File f: sourceDir.listFiles()){
			 
			 if(f.getName().endsWith("java")){
				 
				 f.delete();
			 }
			       
		 }  
		 PathResolver.setClassPath(Constants.generatedCodePath);
		 if(this.busProtocol != ProtocolType.SOAP){
				try {

					bc = Class.forName(Constants.target_namespace + ".MediatorMain");
					
				} catch (ClassNotFoundException e){
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }

	}
	
	public MediatorOutput generateWar(WarGenerator warGenerator, ProtocolType busProtocol){
		
		MediatorOutput vsbOutput = warGenerator.generate(busProtocol == ProtocolType.SOAP);
		return vsbOutput;
	}
	
	public  byte[] generateJar(JarGenerator jarGenerator){
		
		archive = ShrinkWrap.create(JavaArchive.class);
		if(bc != null) {
			
			archive.addPackage(bc.getPackage());
		}
		archive.addPackage(org.zefxis.dexms.tools.serviceparser.gidl.ParseGIDL.class.getPackage());
		archive.addPackage(org.zefxis.dexms.gmdl.utils.Operation.class.getPackage());
		archive.addPackage(org.zefxis.dexms.gmdl.utils.enums.OperationType.class.getPackage());
		archive.addPackage(org.zefxis.dexms.mediator.manager.MediatorManagerRestService.class.getPackage());
		archive.addPackage(org.zefxis.dexms.artifact.war.RestServlet.class.getPackage());
		archive.addPackage(org.zefxis.dexms.artifact.war.StartBcManagerServlet.class.getPackage());
		archive.addPackage(org.zefxis.dexms.artifact.generators.WarGenerator.class.getPackage());
		archive.addPackage(org.zefxis.dexms.mediator.manager.setinvaddrservice.BaseService.class.getPackage());
		archive.addPackage(org.zefxis.dexms.dex.protocols.Manageable.class.getPackage());
		archive.addPackage(org.zefxis.dexms.tools.serviceparser.ServiceDescriptionParser.class.getPackage());
		jarGenerator.addArchive(archive);
		return jarGenerator.generate();
	}
	
	public void buidAndMergeArchives(String pomfile){

		BuiltProject builtProject = EmbeddedMaven.forProject(pomfile).useMaven3Version("3.3.9").setGoals("package")
				.build();

		for (Archive jar : builtProject.getArchives()){

			archive = archive.merge(jar);
		}
		PathResolver.deleteTempDir(builtProject.getTargetDirectory());

	}
	
	public void addArchive(Archive archive){
		
		this.archive = this.archive.merge(archive);
	}
	
}

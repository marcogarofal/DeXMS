package eu.chorevolution.vsb.artifact.generators;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.BuiltProject;
import org.jboss.shrinkwrap.resolver.api.maven.embedded.EmbeddedMaven;

import eu.chorevolution.vsb.bc.manager.VsbOutput;
import eu.chorevolution.vsb.compiler.RunTimeCompiler;
import eu.chorevolution.vsb.gmdl.utils.Constants;
import eu.chorevolution.vsb.gmdl.utils.PathResolver;
import eu.chorevolution.vsb.gmdl.utils.enums.ProtocolType;

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

					bc = Class.forName(Constants.target_namespace + ".BindingComponentMain");
					
				} catch (ClassNotFoundException e){
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }

	}
	
	public VsbOutput generateWar(WarGenerator warGenerator, ProtocolType busProtocol){
		
		VsbOutput vsbOutput = warGenerator.generate(busProtocol == ProtocolType.SOAP);
		return vsbOutput;
	}
	
	public  byte[] generateJar(JarGenerator jarGenerator){
		
		archive = ShrinkWrap.create(JavaArchive.class);
		if(bc != null) {
			
			archive.addPackage(bc.getPackage());
		}
		archive.addPackage(eu.chorevolution.vsb.gmdl.tools.serviceparser.gidl.ParseGIDL.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.gmdl.tools.serviceparser.gmdl.ParseGMDL.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.gmdl.utils.Operation.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.gmdl.utils.enums.OperationType.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.bc.manager.BcManagerRestService.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.artifact.war.RestServlet.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.artifact.war.StartBcManagerServlet.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.artifact.generators.WarGenerator.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.bc.setinvaddrservice.BaseService.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.gm.protocols.Manageable.class.getPackage());
		archive.addPackage(eu.chorevolution.vsb.gmdl.tools.serviceparser.ServiceDescriptionParser.class.getPackage());
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

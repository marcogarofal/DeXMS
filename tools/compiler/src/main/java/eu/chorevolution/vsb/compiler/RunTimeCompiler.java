package eu.chorevolution.vsb.compiler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import eu.chorevolution.vsb.gmdl.utils.PathResolver;
import eu.chorevolution.vsb.logger.GLog;
import eu.chorevolution.vsb.logger.Logger;

import javax.tools.JavaCompiler.CompilationTask;

public class RunTimeCompiler{
	
	private String sourceFilesPath = null;
	private String outputPath =  null;
	private Class[] classesOptions = null;
	private Logger logger = GLog.initLogger();
	
	public RunTimeCompiler(String sourceFilesPath , String outputPath, Class[] classesOptions){
		
		this.sourceFilesPath = sourceFilesPath;
		this.outputPath = outputPath;
		this.classesOptions = classesOptions;
	}
	
	public void compile(){
		 
		File classDir = new File(outputPath);
		String[] compileOptions = new String[] { "-d", classDir.getAbsolutePath(), "-cp",
				PathResolver.myClassPath(classesOptions) };
		
		  JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		  if (compiler == null){
				
				System.setProperty("java.home", System.getenv("JAVA_HOME"));
				compiler = ToolProvider.getSystemJavaCompiler();
				if (compiler == null){
					try{
						
						throw new Exception("Set JAVA_HOME env variable to point to JDK");
						
					}catch(Exception e){
						
						e.printStackTrace();
					}
				}
			}

			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.getDefault(), null);

			File sourceDir = new File(sourceFilesPath);

			List<JavaFileObject> javaObjects = scanRecursivelyForJavaFiles(sourceDir, fileManager);

			if (javaObjects.size() == 0) {
				
				logger.e(this.getClass().getName(), "There are no source files to compile in " + sourceDir.getAbsolutePath());
				
			}

			
			Iterable<String> compilationOptions = Arrays.asList(compileOptions);
			CompilationTask compilerTask = compiler.getTask(null, fileManager, null, compilationOptions, null,
					javaObjects);

			if (!compilerTask.call()){
				
				logger.e(this.getClass().getName(), "Could not compile project");
			}

			
	  }
	
	private static List<JavaFileObject> scanRecursivelyForJavaFiles(File dir, StandardJavaFileManager fileManager) {
		List<JavaFileObject> javaObjects = new LinkedList<JavaFileObject>();
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {

				javaObjects.addAll(scanRecursivelyForJavaFiles(file, fileManager));
			} else if (file.isFile() && file.getName().toLowerCase().endsWith(".java")) {

				javaObjects.add(readJavaObject(file, fileManager));
			}
		}
		return javaObjects;
	}
	
	
	private static JavaFileObject readJavaObject(File file, StandardJavaFileManager fileManager) {
		
		Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(file);
		Iterator<? extends JavaFileObject> it = javaFileObjects.iterator();
		if (it.hasNext()){
			
			return it.next();
		}
		throw new RuntimeException("Could not load " + file.getAbsolutePath() + " java file object");
	}
	
}

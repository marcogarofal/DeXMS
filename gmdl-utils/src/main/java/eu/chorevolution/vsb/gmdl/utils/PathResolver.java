package eu.chorevolution.vsb.gmdl.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PathResolver {

	/**
	 * Returns a string classpath of all classes given in parameter
	 * <p>
	 * @param classes
	 *            an classes array
	 * @return classpath to string of given classes in array
	 */

	public static String myClassPath(Class[] classes) {

		StringBuilder classpath = new StringBuilder();

		for (Class classe : classes) {

			classpath.append(new File(classe.getProtectionDomain().getCodeSource().getLocation().getPath()).toString());
			classpath.append(System.getProperty("path.separator"));
		}
		if (classpath.length() == 0) {

			return "";
		}
		return classpath.toString();
	}

	/**
	 * Returns a string file path of a filename in the project package of a
	 * given class name
	 * <p>
	 *
	 * @param className
	 *            a given class name
	 * @param fileName
	 *            a given filename
	 * @return class path to string of a class name given in param
	 */

public static String myFilePath(Class className, String fileName){
		
		
		String[] fileNameToArray = fileName.split("\\.");
		String fileNameExtension = fileNameToArray[fileNameToArray.length - 1];
		File file = null;
		URL res = className.getClassLoader().getResource(fileName);
		if (res == null) {

			System.err.println("Null pointer class name");
			return "";
		}

		if (res.toString().startsWith("jar:")){

			try{

				java.io.InputStream input = className.getClassLoader().getResourceAsStream(fileName);
				System.out.println(" fileNameExtension "+fileNameExtension);
				if(fileNameExtension.contains("/")){
					
					fileNameExtension = "conf";
				}
				
				file = File.createTempFile("tempfile", "." + fileNameExtension);
				
				OutputStream out = new FileOutputStream(file);
				int read;
				byte[] bytes = new byte[1024];

				while ((read = input.read(bytes)) != -1) {

					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
				input.close();
				// file.deleteOnExit();
			} catch (IOException ex) {

				ex.printStackTrace();
			}
		} else {

			file = new File(res.getFile());
		}

		return file.getPath();
	}



	/**
	 * Returns a String path of a created temporary directory
	 * <p>
	 *
	 * @return String
	 */

	public static String createTempDir() {

		String pathFolderToString = "";
		Path pathFolder = FileSystems.getDefault().getPath(System.getProperty("java.io.tmpdir"));
		Path tempFolder = null;
		try {

			tempFolder = Files.createTempDirectory(pathFolder, "GeneratorFolder");
			pathFolderToString = tempFolder.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pathFolderToString;

	}

	/**
	 * Returns void and copy directory from source path to destination path
	 * <p>
	 *
	 * @param sourcePath
	 *            directory string path
	 * @param destinationPath
	 *            destination string path
	 */

	public static void copyFolder(String sourcePath, String destinationPath) {

		File sourceFolder = new File(sourcePath);
		File destinationFolder = new File(destinationPath);
		doCopyJob(sourceFolder, destinationFolder);
	}

	/**
	 * Returns void and copy directory from source path to destination path it
	 * is a private method used in public method doCopyJob
	 * <p>
	 *
	 * @param sourcePath
	 *            directory path as a file object
	 * @param destinationPath
	 *            destination string path
	 */

	private static void doCopyJob(File sourceFolder, File destinationFolder) {

		if (sourceFolder.isDirectory()) {
			// Verify if destinationFolder is already present; If not then
			// create it
			if (!destinationFolder.exists()) {
				destinationFolder.mkdir();
				// System.out.println("Directory created :: " +
				// destinationFolder);
			}

			// Get all files from source directory
			String files[] = sourceFolder.list();

			// Iterate over all files and copy them to destinationFolder one by
			// one
			for (String file : files) {
				File srcFile = new File(sourceFolder, file);
				File destFile = new File(destinationFolder, file);

				// Recursive function call
				doCopyJob(srcFile, destFile);
			}
		} else {
			// Copy the file content from one place to another
			try {
				Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Returns void and extract directory from a jar file and copy it to a
	 * destination path
	 * <p>
	 *
	 * @param urlSourcePath
	 *            the jar location url
	 * @param destinationPath
	 *            destination string path
	 */

	public static void extractDirectoryFromJar(URL urlSourcePath, String destinationPath) {

		// make sure write directory exists
		if (!new File(destinationPath).exists()) {

			new File(destinationPath).mkdirs();

		}
		
		try {
			extract(urlSourcePath, destinationPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Returns void and extract directory from a jar file and copy it to a
	 * destination path a private method called in the public method
	 * extractDirectoryFromJar
	 * <p>
	 *
	 * @param urlSourcePath
	 *            the jar location url
	 * @param destinationPath
	 *            destination string path
	 */

	private static void extract(URL urlSourcePath, String destinationPath) throws IOException {

		String sourceDirectory = urlSourcePath.getPath();
		final URL dirURL = urlSourcePath;
		String[] sourceDirectoryToArray = sourceDirectory.split("/");
		final String path = sourceDirectoryToArray[sourceDirectoryToArray.length - 1];

		if ((dirURL != null) && dirURL.getProtocol().equals("jar")) {
			final JarURLConnection jarConnection = (JarURLConnection) dirURL.openConnection();

			final ZipFile jar = jarConnection.getJarFile();

			final Enumeration<? extends ZipEntry> entries = jar.entries(); // gives
																			// ALL
																			// entries
																			// in
																			// jar

			while (entries.hasMoreElements()) {
				final ZipEntry entry = entries.nextElement();
				final String name = entry.getName();

				if (!name.startsWith(path)) {
					// entry in wrong subdir -- don't copy
					continue;
				}
				final String entryTail = name;

				final File f = new File(destinationPath + File.separator + entryTail);
				if (entry.isDirectory()) {
					// if its a directory, create it
					final boolean bMade = f.mkdir();
//					System.out.println((bMade ? "  creating " : "  unable to create ") + name);
				} else {

					final InputStream is = jar.getInputStream(entry);
					final OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
					final byte buffer[] = new byte[4096];
					int readCount;
					// write contents of 'is' to 'os'
					while ((readCount = is.read(buffer)) > 0) {
						os.write(buffer, 0, readCount);
					}
					os.close();
					is.close();
				}
			}

		} else{
			
			
			final File f = new File(urlSourcePath.getPath().toString());
			if(f.isDirectory()){
				
				copyFolder(urlSourcePath.getPath().toString(),destinationPath+File.separator + path);
				
				
			}else{
				
				throw new IllegalStateException("don't know how to handle extracting from " + dirURL);
			}
			
		
		}

	}

	/**
	 * Returns void and delete a given file or diectory in parameter
	 * <p>
	 * 
	 * @param file
	 *            object file to delete
	 */

	public static void deleteTempDir(File file) {

		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
			} else {
				// list all the directory contents
				String files[] = file.list();

				for (String fileName : files) {
					// construct the file structure
					File fileDelete = new File(file, fileName);
					// recursive delete
					deleteTempDir(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0){
					
					file.delete();
				}
			}
		} else {
			// if file, then delete it
			file.delete();
		}
	}

	public static void setClassPath(String generatedClasspath){

		File classpathFile = new File(generatedClasspath);		
	
	    URL u = null;
		try {
			u = classpathFile.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	    Class urlClass = URLClassLoader.class;
	    Method method = null;
		try {
			method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    method.setAccessible(true);
	    try {
			method.invoke(urlClassLoader, new Object[]{u});
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();


	}

	public static void updatePortServiceSetInvAddr(String port) {

		String contextFilePath = Constants.generatedCodePath + File.separator
				+ "artifact-generators" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "setinvaddr-context.xml";
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;
        
		try {

			docBuilder = docFactory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {

			doc = docBuilder.parse(contextFilePath);

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		Node beansNode = doc.getFirstChild();
		Node jaxwsEndpointNode = null;

		for (int i = 0; i < beansNode.getChildNodes().getLength(); i++) {

			String nodeName = beansNode.getChildNodes().item(i).getNodeName();

			if (nodeName.equals("jaxws:endpoint"))
				jaxwsEndpointNode = beansNode.getChildNodes().item(i);

		}

		NamedNodeMap attr = jaxwsEndpointNode.getAttributes();
		Node nodeAttr = attr.getNamedItem("address");
		String address = nodeAttr.getTextContent();
		Constants.setinvaddr_service_url = "http://localhost:" + port + "/BaseService/setinvaddr";
		nodeAttr.setTextContent("http://localhost:" + port + "/BaseService/setinvaddr");

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		DOMSource contextFileDocUpdated = new DOMSource(doc);
		StreamResult contextFile = new StreamResult(new File(contextFilePath));

		try {

			transformer = transformerFactory.newTransformer();

		} catch (TransformerConfigurationException e){
			e.printStackTrace();
		}

		try {
			transformer.transform(contextFileDocUpdated, contextFile);

		} catch (TransformerException e) {
			e.printStackTrace();
		}

		// System.out.println(address+" update to
		// http://localhost:"+port+"/BaseService/setinvaddr");

	}

	public static void cleanWSDL(String wsdlFile) {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;

		try {

			docBuilder = docFactory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {

			doc = docBuilder.parse(wsdlFile);

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		Node rootNode = doc.getFirstChild();

		for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {

			goDeeply(rootNode.getChildNodes().item(i));

		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		DOMSource contextFileDocUpdated = new DOMSource(doc);
		StreamResult contextFile = new StreamResult(new File(wsdlFile));

		try {

			transformer = transformerFactory.newTransformer();

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}

		try {
			transformer.transform(contextFileDocUpdated, contextFile);

		} catch (TransformerException e) {
			
			e.printStackTrace();
		}

	}

	private static void goDeeply(Node rootNode) {

		if (rootNode.hasChildNodes()){

			for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {

				Node node = rootNode.getChildNodes().item(i);
				nodeHasAttributes(node);
				goDeeply(node);
			}
		} else {

			nodeHasAttributes(rootNode);
		}

	}

	private static void nodeHasAttributes(Node node) {

		if (node.hasAttributes()) {

			NamedNodeMap attr = node.getAttributes();
			if (attr.getNamedItem("type") != null) {

				Node nodeAttrType = attr.getNamedItem("type");
				String type = nodeAttrType.getTextContent();
				if (type.contains("ns:")) {

					String updateType = type.split(":")[1];
					nodeAttrType.setTextContent("tns:"+updateType);
				}

			}

			if (attr.getNamedItem("element") != null) {

				Node nodeAttrElement = attr.getNamedItem("element");
				String element = nodeAttrElement.getTextContent();
				if (element.contains("ns:")) {

					String updateElement = element.split(":")[1];
					nodeAttrElement.setTextContent("tns:"+updateElement);
				}

			}

			if (attr.getNamedItem("message") != null) {

				Node nodeAttrMessage = attr.getNamedItem("message");
				String message = nodeAttrMessage.getTextContent();
				if (message.contains("ns:")) {

					String updateMessage = message.split(":")[1];
					nodeAttrMessage.setTextContent("tns:"+updateMessage);
				}

			}

		}else{;;}

	}


}

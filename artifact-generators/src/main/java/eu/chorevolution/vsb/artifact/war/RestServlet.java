package eu.chorevolution.vsb.artifact.war;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;

import eu.chorevolution.vsb.bc.manager.BcManagerRestService;
import eu.chorevolution.vsb.gmdl.utils.Constants;
import pl.ncdc.differentia.antlr.JavaParser.catches_return;

/**
 * @author Georgios Bouloukakis (boulouk@gmail.com)
 * 
 *         CallStarter.java Created: 27 janv. 2016 Description:
 */
public class RestServlet extends HttpServlet {
	private Client client = null;
	private String previousMsg = "";

	public Client getClient() {
		if (client == null) {
			return client = new Client(Protocol.HTTP);
		} else
			return client;
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *          servlet request
	 * @param response
	 *          servlet response
	 * @throws ServletException
	 *           if a servlet-specific error occurs
	 * @throws IOException
	 *           if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try{
			
			PrintWriter out = response.getWriter();
			if (request.getParameter("param").equals("get")) {
				this.getClient();
				// client = new Client(Protocol.HTTP);
				String restServicePort = getRestServicePort();
				Request request1 = new Request(Method.GET, "http://localhost:"+restServicePort+"/getmessage");
				Response response1 = client.handle(request1);

				if (response1.getStatus().isSuccess()) {
					Representation result = response1.getEntity();
					String message = result.getText();
					if (!message.equals("empty")){
						if(!message.equals(previousMsg)) {
							out.print(StringUtils.abbreviate(message, 480));
							previousMsg = message;
						}
					}
				}
			} else
				out.print("Wrong parameter!");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *          servlet request
	 * @param response
	 *          servlet response
	 * @throws ServletException
	 *           if a servlet-specific error occurs
	 * @throws IOException
	 *           if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *          servlet request
	 * @param response
	 *          servlet response
	 * @throws ServletException
	 *           if a servlet-specific error occurs
	 * @throws IOException
	 *           if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo(){
		return "Short description";
	}// </editor-fold>
	
	
	private String getRestServicePort(){
		
		String service_port = "";
		File dir = new File(BcManagerRestService.class.getClassLoader().getResource("example.json").toExternalForm().substring(9)).getParentFile().getParentFile().getParentFile().getParentFile();
		File configFilePath = new File(dir.getAbsolutePath() + File.separator + "config"+ File.separator + "config_block2_interface_1");
		JSONParser parser = new JSONParser();
	    JSONObject jsonObject = new JSONObject();
	    try {
	        FileReader fileReader = new FileReader(configFilePath);
	        jsonObject = ((JSONObject) parser.parse(fileReader));
	    } catch (Exception _x) {
	    }
	    
	    if(jsonObject.get("service_port") != null){
	    	service_port = (String) jsonObject.get("service_port");
	    }
		return service_port;
	}
}

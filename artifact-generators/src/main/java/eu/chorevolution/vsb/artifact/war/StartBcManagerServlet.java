package eu.chorevolution.vsb.artifact.war;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import eu.chorevolution.vsb.bc.manager.BcManagerRestService;
import eu.chorevolution.vsb.gmdl.utils.Constants;

public class StartBcManagerServlet extends HttpServlet {
    
	BcManagerRestService server = null;
	
	public StartBcManagerServlet(){
		
		int portBcManagerServlet = Integer.valueOf(getPort());
		server = new BcManagerRestService(portBcManagerServlet);
		
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
        
		this.getServletContext().setAttribute("role", "role--1");
		this.getServletContext().setAttribute("name", "name--1");
		this.getServletContext().setAttribute("endpoints", "endpoints--1");
		String op = request.getParameter("op");
		if(op.equals("start")){
			
			server.runBC();
			response.getWriter().println("BC started!");
		}
		else if(op.equals("stop")){
			
			server.pauseBC();
			response.getWriter().println("BC stopped!");
		} 
		else if(op.equals("startbcm")){
			
			
			int portBcManagerServlet = Integer.valueOf(getPort());
			if(!server.isBcSarted()){
				
				server = new BcManagerRestService(portBcManagerServlet);
				
				response.getWriter().println("-> BC Manager started!");
			}else{
				
				response.getWriter().println("-> BC Manager already started!");
			}
			
		}
		else if(op.equals("stopbcm")){
			
			if(server!=null){
				
				server.stop();
				server = null;
				response.getWriter().println("stopped!");
			}
			else{
				
				response.getWriter().println("server is not running stopped!");
			}
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

	public static void main(String[] args){}
	
	
    private String getPort(){
		
		String bc_manager_servlet_port = "";
		File dir = new File(BcManagerRestService.class.getClassLoader().getResource("example.json").toExternalForm().substring(9)).getParentFile().getParentFile().getParentFile().getParentFile();
		File configFilePath = new File(dir.getAbsolutePath() + File.separator + "config"+ File.separator + "config_block1_interface_1");
		JSONParser parser = new JSONParser();
	    JSONObject jsonObject = new JSONObject();
	    try{
	    	
	        FileReader fileReader = new FileReader(configFilePath);
	        jsonObject = ((JSONObject) parser.parse(fileReader));
	        
	    } catch (Exception _x){
	    }
	    
	    if(jsonObject.get("bc_manager_servlet_port") != null){
	    	bc_manager_servlet_port = (String) jsonObject.get("bc_manager_servlet_port");
	    }
		return bc_manager_servlet_port;
	}
	
}

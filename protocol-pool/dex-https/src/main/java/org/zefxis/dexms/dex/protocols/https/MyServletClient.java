package org.zefxis.dexms.dex.protocols.https;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.zefxis.dexms.dex.protocols.https.MediatorHttpsSubcomponent;
 


import java.io.BufferedReader;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


// This class is used to manage the Servlet. It is used in the MediatorHttpsSubcomponent

public class MyServletClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private BlockingQueue<String> waitingQueue = new LinkedBlockingDeque<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
		try {
	        String data_queue = ((LinkedBlockingDeque<String>) waitingQueue).peekLast();

	        // Parse the data_queue JSON string into a JsonObject
	        JsonObject dataQueueJson = JsonParser.parseString(data_queue).getAsJsonObject();

	        // Set the content type to JSON
	        resp.setContentType("application/json");

	        // Write the JSON object to the response output stream
	        resp.getWriter().println(dataQueueJson);
	    } catch (IOException e) {
	        // Handle exceptions here
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        resp.getWriter().println("{\"error\": \"An error occurred.\"}");
	    }
		/*String data_queue;
		resp.setContentType("application/json");

		data_queue = ((LinkedBlockingDeque<String>) waitingQueue).peekLast();
		// Create a JSON object
		JsonObject jsonResponse = new JsonObject();
		// Serialize the JSON object to a JSON string
		String jsonResponseString = new Gson().toJson(jsonResponse);
        resp.setStatus(HttpStatus.OK_200);
        resp.getWriter().println(jsonResponseString);*/

    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		//The received data from protocol X is posted/exposed on the HTTPS server
		System.out.println("This is POST of HTTPS");
		// Get the content of the POST request 
		StringBuilder requestBody = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		String receivedText = requestBody.toString();
		
		// Add the received data to the queue --> the same queue is used in the GET to read the data 
		waitingQueue.offer(receivedText);
		
		Object data = receivedText;
		// Managing the response of the POST request
		System.out.println("I received data");
		System.out.println(receivedText);
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);

		// Create a JSON object with status, message, and data fields
		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("status", "success");
		jsonResponse.addProperty("message", "Data received successfully");
		// Get the response writer and write JSON data
		try (PrintWriter writer = response.getWriter()) {
		    writer.print(jsonResponse.toString());
		}

    }
}




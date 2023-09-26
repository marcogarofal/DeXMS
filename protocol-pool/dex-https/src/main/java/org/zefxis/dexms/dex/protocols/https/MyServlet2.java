package org.zefxis.dexms.dex.protocols.https;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.eclipse.jetty.http.HttpStatus;


import java.io.BufferedReader;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

// This class is used to manage the Servlet. It is used in the MediatorHttpsSubcomponent

public class MyServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private BlockingQueue<String> waitingQueue = new LinkedBlockingDeque<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String data_queue;

		data_queue = ((LinkedBlockingDeque<String>) waitingQueue).peekLast();
        resp.setStatus(HttpStatus.OK_200);

        resp.getWriter().println(data_queue);
    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		//The received data from protocol X is posted/exposed on the HTTPS server
		
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
		
		// Managing the response of the POST request		
		response.setContentType("text/plain");
		response.setStatus(HttpServletResponse.SC_OK);
	    String jsonResponse = "{\"message\": \"Data received successfully\"}"+waitingQueue.element();
	    PrintWriter writer = response.getWriter();
	    writer.print(jsonResponse);
	    writer.close();

    }
}




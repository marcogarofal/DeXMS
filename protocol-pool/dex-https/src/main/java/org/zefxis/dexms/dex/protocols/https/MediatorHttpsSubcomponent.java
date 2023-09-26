package org.zefxis.dexms.dex.protocols.https;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.simple.JSONObject;

import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class MediatorHttpsSubcomponent extends MediatorGmSubcomponent {
	
	private Server server = null;
	private static MediatorHttpsSubcomponent bcHttpsSubcomponent;
	private static String op_name = null;
	private static Operation op = null;
	private static String message;
	private static SslContextFactory SslContextFactory = null;
	private static SSLContext sslContext;
	
	public MediatorHttpsSubcomponent(MediatorConfiguration bcConfiguration,
			GmServiceRepresentation serviceRepresentation) {
		
		super(bcConfiguration);

		System.out.println("MediatorHttpsSubcomponent --> "+this.bcConfiguration.getSubcomponentRole());

		setGmServiceRepresentation(serviceRepresentation);
		System.out.print("serviceRepresentation:"+serviceRepresentation.getInterfaces().get(0).getOperations().entrySet());
		for (Entry<String, Operation> en : serviceRepresentation.getInterfaces().get(0).getOperations().entrySet()) {

			op_name = en.getKey();
			op = en.getValue();
		}
		this.bcHttpsSubcomponent = this;
		
		
		int restservicePort = Integer.valueOf(bcConfiguration.getServicePort());
		System.out.println("port:" + restservicePort);
		System.out.println("op_name:" + op_name);
		System.out.println("Host:" + this.bcConfiguration.getSubcomponentAddress());
		
		switch (this.bcConfiguration.getSubcomponentRole()) {

		case SERVER:

		server = new Server();
		try {
			SSLconf(restservicePort, this.bcConfiguration.getSubcomponentAddress().toString(), server);
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	break;
    	
	}
	}
	
	// This is used to configure the SSL certificate
	public static void SSLconf (int port, String host, Server server) throws FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyStoreException {
    	// Configure SSL
        // Load your keystore (containing your SSL certificate) and truststore if needed
        KeyStore keyStore = null;
		try {
			keyStore = KeyStore.getInstance("JKS");
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try (FileInputStream keyStoreFile = new FileInputStream("/Users/zbenomar/eclipse-workspace/Testjetty2/src/main/java/testjetty2/mykeystore.jks")) {
            keyStore.load(keyStoreFile, "testtest".toCharArray());
        }
        
     // Initialize KeyManagerFactory with the keystore
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, "testtest".toCharArray());
       
     // Initialize TrustManagerFactory with your truststore (if needed)
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        //TrustManager[] trustAllCertificates = new TrustManager[]{new TrustAllCertificates()};

     // Initialize SSLContext with the KeyManagerFactory and TrustManagerFactory
        sslContext = SSLContext.getInstance("TLS");
        try {
			sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
			//sslContext.init(null, trustAllCertificates, null);

        } catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     // Create an SslConnectionFactory with the SSL context
        SslContextFactory sslFactory = new SslContextFactory();
        sslFactory.setSslContext(sslContext);

        // Create a secure connector
        ServerConnector sslConnector = new ServerConnector(server, sslFactory);
        
        sslConnector.setPort(port); // Port for HTTPS
        sslConnector.setHost(host); // Host for HTTPS
        // Add the connector to the server
        server.addConnector(sslConnector);
        try {
			SslContextFactory = sslFactory;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	// When the JAR file of the mediator is started
	@Override
	public void start() {
		// TODO Auto-generated method stub
		try {
	    	ServletContextHandler handler = new ServletContextHandler(server, "/"+op_name);
	    	handler.addServlet(MyServlet2.class, "");
			server.start();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// When the JAR file of the mediator is stoped
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		try {
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setGmServiceRepresentation(GmServiceRepresentation serviceRepresentation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GmServiceRepresentation getGmServiceRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Posting data on the Server so as to be exposed later
	@Override
	public void postOneway(String destination, Scope scope, List<Data<?>> data, long lease) {
		// TODO Auto-generated method stub
		
		// Handle the received data 
		// ------------------------------
		JSONObject msgObj = new JSONObject();
		String message_id = "";
		for (Data d : data) {

			if (!d.getName().equals("message_id")) {

				msgObj.put(d.getName(), d.getObject().toString());

			} else {

				message_id = d.getObject().toString();
			}

		}
		
		message = msgObj.toJSONString();
		if(!message_id.equals("")){
			
			message = msgObj.toJSONString() + "-" + message_id;
		}
		// ------------------------------
	
		// Use an HTTPS client to post the data on the URL
		String URL = "https://"+this.bcConfiguration.getServiceAddress().toString()+":"+bcConfiguration.getSubcomponentPort()+"/"+op_name;

	    // Create an HttpClient that supports SSL/TLS
		// Create an HttpClient that supports SSL/TLS
      @SuppressWarnings("deprecation")
	CloseableHttpClient httpClient = HttpClients.custom()
              .setSslcontext(sslContext)
              .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
              .build();
		
      //HttpClient httpClient = HttpClients.createDefault();
      HttpPost httpPost = new HttpPost(URL);
      String postData = message;
      try {
			httpPost.setEntity(new StringEntity(postData));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      try {
			HttpResponse response = httpClient.execute(httpPost);
	        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
	     // Check for redirection
	        if (response.getStatusLine().getStatusCode() == 302) {
	            org.apache.http.Header locationHeader = response.getFirstHeader("Location");
	            if (locationHeader != null) {
	                System.out.println("Redirected to: " + locationHeader.getValue());
	            }
	        }

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	

	@Override
	public void mgetOneway(Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);

	}

	@Override
	public void xmgetOneway(String source, Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		this.nextComponent.postOneway(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange, 0);

	}

	@Override
	public <T> T postTwowaySync(String destination, Scope scope, List<Data<?>> datas, long lease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void xtgetTwowaySync(String destination, Scope scope, long timeout, Object response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T mgetTwowaySync(Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		return this.nextComponent.postTwowaySync(this.bcConfiguration.getServiceAddress(), scope,
				(List<Data<?>>) exchange, 0);
	}

	@Override
	public void postTwowayAsync(String destination, Scope scope, List<Data<?>> data, long lease) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void xgetTwowayAsync(String destination, Scope scope, Object response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mgetTwowayAsync(Scope scope, Object exchange) {
		// TODO Auto-generated method stub
		this.nextComponent.postTwowayAsync(this.bcConfiguration.getServiceAddress(), scope, (List<Data<?>>) exchange,
				0);
		
	}

	@Override
	public void postBackTwowayAsync(String source, Scope scope, Data<?> data, long lease, Object exchange) {
		// TODO Auto-generated method stub
		
	}
}


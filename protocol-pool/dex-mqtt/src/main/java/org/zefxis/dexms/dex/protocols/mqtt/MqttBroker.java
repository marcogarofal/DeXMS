/**
 * Broker.java
 * Created on: 29 févr. 2016
 */
package org.zefxis.dexms.dex.protocols.mqtt;


import org.apache.activemq.broker.*;
import org.fusesource.mqtt.client.BlockingConnection;

public class MqttBroker{
  
  private BrokerService broker;
  private int port;
  private String ip;
 
  
  public MqttBroker(){
	  
    this.broker = new BrokerService();
    this.port = 1883;
    this.ip = "127.0.0.1";
  }
  
  public MqttBroker(String ip, int port){
	  
    this.broker = new BrokerService();
    this.ip = ip;
    this.port = port;
  }
  
  public void start() {
	
    try {
      broker.addConnector("mqtt://"+ ip + ":" + port);
    } catch (Exception e1){
    	
      e1.printStackTrace();
    }
    broker.setDataDirectory("target");
    try {
      broker.start();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    Runtime.getRuntime().addShutdownHook(new Thread(){
		@Override
		
		public void run() {
			try {
				broker.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
  }
  
  public void stop() {
    try {
      broker.stop();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  
}

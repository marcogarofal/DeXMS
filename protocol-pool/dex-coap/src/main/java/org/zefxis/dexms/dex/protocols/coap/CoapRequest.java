package org.zefxis.dexms.dex.protocols.coap;

public class CoapRequest {

	private String destination;
	private String scope;
	private String data;
	public static final String resource = "listener";
	
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}

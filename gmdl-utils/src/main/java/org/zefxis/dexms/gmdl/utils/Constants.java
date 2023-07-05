package org.zefxis.dexms.gmdl.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class Constants {

	public static String generatedCodePath;
	public static String configFilePath;
	public static String interfaceDescriptionFilePath;
	public static String wsdlDestination;
	public static String warDestination;
	public static String warName;
	public static String wsdlName;
	public static String service_name;

	public static String subcomponent_port;
	public static int subcomponent_port_min_range = 1200;
	public static int subcomponent_port_max_range = 1299;

	public static String printer_service_port;
	public static int printer_service_port_min_range = 1300;
	public static int printer_service_port_max_range = 1399;

	public static String service_port;
	public static int service_port_min_range = 5000;
	public static int service_port_max_range = 5999;

	public static String setinvaddr_service_port;
	public static String setinvaddr_service_url;
	public static int setinvaddr_service_port_min_range = 8800;
	public static int setinvaddr_service_port_max_range = 8899;

	public static String service_bc_port;
	public static int service_bc_port_min_range = 8500;
	public static int service_bc_port_max_range = 8599;

	public static String bc_manager_servlet_port;
	public static int bc_manager_servlet_port_min_range = 2200;
	public static int bc_manager_servlet_port_max_range = 2299;

	public static String soap_service_name;
	public static String dpws_service_name;
	public static String webapp_src_bc;
	public static String webapp_src_artifact;
	public static String target_namespace;
	public static String target_namespace_path;
	public static String jarDestination;

	public static final Pattern class_name_checker = Pattern.compile("[A-Za-z_$]+[a-zA-Z0-9_$]*");

	public static int getObserverPort(String name) {

		int port = 0;

		switch (name) {

		case "BcDPWSSubcomponent":

			port = 9901;

			break;

		case "BcCoapSubcomponent":

			port = 9902;

			break;

		case "BcMQTTSubcomponent":

			port = 9903;

			break;

		case "BcRestSubcomponent":

			port = 9904;

			break;

		case "BcSoapSubcomponent":

			port = 9905;
			break;

		case "BcWebsocketSubcomponent":

			port = 9906;

			break;

		default:
			break;
		}
		return port;

	}

	public static String getObserverHostIp() {

		String HostIp = null;
		try {

			InetAddress ownIP = InetAddress.getLocalHost();
			HostIp = ownIP.getHostAddress();

		} catch (UnknownHostException e) {
			HostIp = "127.0.0.1";
		}
		return HostIp;

	}

}
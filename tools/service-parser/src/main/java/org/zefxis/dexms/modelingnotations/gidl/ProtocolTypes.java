package org.zefxis.dexms.modelingnotations.gidl;

/**
 * Copyright Text    
 *  Copyright 2015 The CHOReVOLUTION project
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Protocol Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getProtocolTypes()
 *  
 *   
 */
public enum ProtocolTypes implements Enumerator {
	/**
	 * The '<em><b>COAPS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #COAPS_VALUE
	 *   
	 *  
	 */
	COAPS(12, "COAPS", "COAPS"),
	
	/**
	 * The '<em><b>HTTPS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #HTTPS_VALUE
	 *   
	 *  
	 */
	HTTPS(11, "HTTPS", "HTTPS"),
	
	/**
	 * The '<em><b>REST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #REST_VALUE
	 *   
	 *  
	 */
	REST(0, "REST", "REST"),

	/**
	 * The '<em><b>SOAP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #SOAP_VALUE
	 *   
	 *  
	 */
	SOAP(1, "SOAP", "SOAP"),

	/**
	 * The '<em><b>Co AP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #CO_AP_VALUE
	 *   
	 *  
	 */
	CO_AP(2, "CoAP", "CoAP"),

	/**
	 * The '<em><b>MQTT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #MQTT_VALUE
	 *   
	 *  
	 */
	MQTT(3, "MQTT", "MQTT"),

	/**
	 * The '<em><b>AMQP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #AMQP_VALUE
	 *   
	 *  
	 */
	AMQP(4, "AMQP", "AMQP"),

	/**
	 * The '<em><b>DDS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #DDS_VALUE
	 *   
	 *  
	 */
	DDS(5, "DDS", "DDS"),

	/**
	 * The '<em><b>Semi Space</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #SEMI_SPACE_VALUE
	 *   
	 *  
	 */
	SEMI_SPACE(6, "SemiSpace", "SemiSpace"),

	/**
	 * The '<em><b>Zero MQ</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #ZERO_MQ_VALUE
	 *   
	 *  
	 */
	ZERO_MQ(7, "ZeroMQ", "ZeroMQ"),

	/**
	 * The '<em><b>Web Sockets</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #WEB_SOCKETS_VALUE
	 *   
	 *  
	 */
	WEB_SOCKETS(8, "WebSockets", "WebSockets"),

	/**
	 * The '<em><b>DPWS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #DPWS_VALUE
	 *   
	 *  
	 */
	DPWS(9, "DPWS", "DPWS"),

	/**
	 * The '<em><b>XMPP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #XMPP_VALUE
	 *   
	 *  
	 */
	XMPP(10, "XMPP", "XMPP");

	/**
	 * The '<em><b>REST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #REST
	 *  
	 *   
	 *  
	 */
	public static final int REST_VALUE = 0;

	/**
	 * The '<em><b>SOAP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SOAP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #SOAP
	 *  
	 *   
	 *  
	 */
	public static final int SOAP_VALUE = 1;

	/**
	 * The '<em><b>Co AP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Co AP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #CO_AP
	 *   name="CoAP"
	 *   
	 *  
	 */
	public static final int CO_AP_VALUE = 2;

	/**
	 * The '<em><b>MQTT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MQTT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #MQTT
	 *  
	 *   
	 *  
	 */
	public static final int MQTT_VALUE = 3;

	/**
	 * The '<em><b>AMQP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AMQP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #AMQP
	 *  
	 *   
	 *  
	 */
	public static final int AMQP_VALUE = 4;

	/**
	 * The '<em><b>DDS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DDS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #DDS
	 *  
	 *   
	 *  
	 */
	public static final int DDS_VALUE = 5;

	/**
	 * The '<em><b>Semi Space</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Semi Space</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #SEMI_SPACE
	 *   name="SemiSpace"
	 *   
	 *  
	 */
	public static final int SEMI_SPACE_VALUE = 6;

	/**
	 * The '<em><b>Zero MQ</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Zero MQ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #ZERO_MQ
	 *   name="ZeroMQ"
	 *   
	 *  
	 */
	public static final int ZERO_MQ_VALUE = 7;

	/**
	 * The '<em><b>Web Sockets</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Web Sockets</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #WEB_SOCKETS
	 *   name="WebSockets"
	 *   
	 *  
	 */
	public static final int WEB_SOCKETS_VALUE = 8;

	/**
	 * The '<em><b>DPWS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DPWS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #DPWS
	 *  
	 *   
	 *  
	 */
	public static final int DPWS_VALUE = 9;

	/**
	 * The '<em><b>XMPP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XMPP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #XMPP
	 *  
	 *   
	 *  
	 */
	public static final int XMPP_VALUE = 10;

	/**
	 * An array of all the '<em><b>Protocol Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	
	public static final int HTTPS_VALUE = 11;

	/**
	 * An array of all the '<em><b>Protocol Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	public static final int COAPS_VALUE = 12;

	/**
	 * An array of all the '<em><b>Protocol Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	private static final ProtocolTypes[] VALUES_ARRAY =
		new ProtocolTypes[] {
			REST,
			SOAP,
			CO_AP,
			MQTT,
			AMQP,
			DDS,
			SEMI_SPACE,
			ZERO_MQ,
			WEB_SOCKETS,
			DPWS,
			XMPP,
			HTTPS,
			COAPS,
		};

	/**
	 * A public read-only list of all the '<em><b>Protocol Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	public static final List<ProtocolTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Protocol Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   literal the literal.
	 *    the matching enumerator or <code>null</code>.
	 *   
	 */
	public static ProtocolTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ProtocolTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Protocol Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   name the name.
	 *    the matching enumerator or <code>null</code>.
	 *   
	 */
	public static ProtocolTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ProtocolTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Protocol Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   value the integer value.
	 *    the matching enumerator or <code>null</code>.
	 *   
	 */
	public static ProtocolTypes get(int value) {
		switch (value) {
			case REST_VALUE: return REST;
			case SOAP_VALUE: return SOAP;
			case CO_AP_VALUE: return CO_AP;
			case MQTT_VALUE: return MQTT;
			case AMQP_VALUE: return AMQP;
			case DDS_VALUE: return DDS;
			case SEMI_SPACE_VALUE: return SEMI_SPACE;
			case ZERO_MQ_VALUE: return ZERO_MQ;
			case WEB_SOCKETS_VALUE: return WEB_SOCKETS;
			case DPWS_VALUE: return DPWS;
			case XMPP_VALUE: return XMPP;
			case HTTPS_VALUE: return HTTPS;
			case COAPS_VALUE: return COAPS;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	private ProtocolTypes(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ProtocolTypes

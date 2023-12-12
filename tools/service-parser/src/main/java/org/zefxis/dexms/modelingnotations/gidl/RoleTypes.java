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
 * A representation of the literals of the enumeration '<em><b>Role Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 *  eu.chorevolution.modelingnotations.gidl.GidlPackage#getRoleTypes()
 * 
 * 
 */
public enum RoleTypes implements Enumerator {
	/**
	 * The '<em><b>Provider</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #PROVIDER_VALUE
	 * 
	 * 
	 */
	PROVIDER(0, "provider", "provider"),

	/**
	 * The '<em><b>Consumer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #CONSUMER_VALUE
	 * 
	 * 
	 */
	CONSUMER(1, "consumer", "consumer");

	/**
	 * The '<em><b>Provider</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Provider</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #PROVIDER
	 *  name="provider"
	 * 
	 * 
	 */
	public static final int PROVIDER_VALUE = 0;

	/**
	 * The '<em><b>Consumer</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Consumer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #CONSUMER
	 *  name="consumer"
	 * 
	 * 
	 */
	public static final int CONSUMER_VALUE = 1;

	/**
	 * An array of all the '<em><b>Role Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	private static final RoleTypes[] VALUES_ARRAY =
		new RoleTypes[] {
			PROVIDER,
			CONSUMER,
		};

	/**
	 * A public read-only list of all the '<em><b>Role Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	public static final List<RoleTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Role Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  literal the literal.
	 *  the matching enumerator or <code>null</code>.
	 * 
	 */
	public static RoleTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RoleTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Role Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  name the name.
	 *  the matching enumerator or <code>null</code>.
	 * 
	 */
	public static RoleTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RoleTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Role Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  value the integer value.
	 *  the matching enumerator or <code>null</code>.
	 * 
	 */
	public static RoleTypes get(int value) {
		switch (value) {
			case PROVIDER_VALUE: return PROVIDER;
			case CONSUMER_VALUE: return CONSUMER;
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
	private RoleTypes(int value, String name, String literal) {
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
	
} //RoleTypes

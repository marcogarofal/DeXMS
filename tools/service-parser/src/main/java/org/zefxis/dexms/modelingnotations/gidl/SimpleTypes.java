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
 * A representation of the literals of the enumeration '<em><b>Simple Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 *  eu.chorevolution.modelingnotations.gidl.GidlPackage#getSimpleTypes()
 * 
 * 
 */
public enum SimpleTypes implements Enumerator {
	/**
	 * The '<em><b>String</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #STRING_VALUE
	 * 
	 * 
	 */
	STRING(0, "string", "string"),

	/**
	 * The '<em><b>Integer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #INTEGER_VALUE
	 * 
	 * 
	 */
	INTEGER(1, "integer", "integer"),

	/**
	 * The '<em><b>Boolean</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #BOOLEAN_VALUE
	 * 
	 * 
	 */
	BOOLEAN(2, "boolean", "boolean"),

	/**
	 * The '<em><b>Decimal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #DECIMAL_VALUE
	 * 
	 * 
	 */
	DECIMAL(3, "decimal", "decimal"),

	/**
	 * The '<em><b>Date</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #DATE_VALUE
	 * 
	 * 
	 */
	DATE(4, "date", "date"),

	/**
	 * The '<em><b>Time</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #TIME_VALUE
	 * 
	 * 
	 */
	TIME(5, "time", "time"),

	/**
	 * The '<em><b>Double</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #DOUBLE_VALUE
	 * 
	 * 
	 */
	DOUBLE(6, "double", "double"),

	/**
	 * The '<em><b>Float</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  #FLOAT_VALUE
	 * 
	 * 
	 */
	FLOAT(7, "float", "float");

	/**
	 * The '<em><b>String</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>String</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #STRING
	 *  name="string"
	 * 
	 * 
	 */
	public static final int STRING_VALUE = 0;

	/**
	 * The '<em><b>Integer</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Integer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #INTEGER
	 *  name="integer"
	 * 
	 * 
	 */
	public static final int INTEGER_VALUE = 1;

	/**
	 * The '<em><b>Boolean</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Boolean</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #BOOLEAN
	 *  name="boolean"
	 * 
	 * 
	 */
	public static final int BOOLEAN_VALUE = 2;

	/**
	 * The '<em><b>Decimal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Decimal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #DECIMAL
	 *  name="decimal"
	 * 
	 * 
	 */
	public static final int DECIMAL_VALUE = 3;

	/**
	 * The '<em><b>Date</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Date</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #DATE
	 *  name="date"
	 * 
	 * 
	 */
	public static final int DATE_VALUE = 4;

	/**
	 * The '<em><b>Time</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Time</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #TIME
	 *  name="time"
	 * 
	 * 
	 */
	public static final int TIME_VALUE = 5;

	/**
	 * The '<em><b>Double</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Double</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #DOUBLE
	 *  name="double"
	 * 
	 * 
	 */
	public static final int DOUBLE_VALUE = 6;

	/**
	 * The '<em><b>Float</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Float</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  #FLOAT
	 *  name="float"
	 * 
	 * 
	 */
	public static final int FLOAT_VALUE = 7;

	/**
	 * An array of all the '<em><b>Simple Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	private static final SimpleTypes[] VALUES_ARRAY =
		new SimpleTypes[] {
			STRING,
			INTEGER,
			BOOLEAN,
			DECIMAL,
			DATE,
			TIME,
			DOUBLE,
			FLOAT,
		};

	/**
	 * A public read-only list of all the '<em><b>Simple Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	public static final List<SimpleTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Simple Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  literal the literal.
	 *  the matching enumerator or <code>null</code>.
	 * 
	 */
	public static SimpleTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SimpleTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Simple Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  name the name.
	 *  the matching enumerator or <code>null</code>.
	 * 
	 */
	public static SimpleTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SimpleTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Simple Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  value the integer value.
	 *  the matching enumerator or <code>null</code>.
	 * 
	 */
	public static SimpleTypes get(int value) {
		switch (value) {
			case STRING_VALUE: return STRING;
			case INTEGER_VALUE: return INTEGER;
			case BOOLEAN_VALUE: return BOOLEAN;
			case DECIMAL_VALUE: return DECIMAL;
			case DATE_VALUE: return DATE;
			case TIME_VALUE: return TIME;
			case DOUBLE_VALUE: return DOUBLE;
			case FLOAT_VALUE: return FLOAT;
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
	private SimpleTypes(int value, String name, String literal) {
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
	
} //SimpleTypes


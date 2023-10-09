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
 * A representation of the literals of the enumeration '<em><b>Context Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->

 */
public enum ContextTypes implements Enumerator {
	/**
	 * The '<em><b>Body</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	BODY(0, "body", "body"),

	/**
	 * The '<em><b>Path</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	PATH(1, "path", "path"),

	/**
	 * The '<em><b>Query</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	QUERY(2, "query", "query"),

	/**
	 * The '<em><b>Form</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	FORM(3, "form", "form"),

	/**
	 * The '<em><b>Header</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	HEADER(4, "header", "header");

	/**
	 * The '<em><b>Body</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Body</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->

	 */
	public static final int BODY_VALUE = 0;

	/**
	 * The '<em><b>Path</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Path</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->

	 */
	public static final int PATH_VALUE = 1;

	/**
	 * The '<em><b>Query</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Query</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->

	 */
	public static final int QUERY_VALUE = 2;

	/**
	 * The '<em><b>Form</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Form</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->

	 */
	public static final int FORM_VALUE = 3;

	/**
	 * The '<em><b>Header</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Header</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->

	 */
	public static final int HEADER_VALUE = 4;

	/**
	 * An array of all the '<em><b>Context Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private static final ContextTypes[] VALUES_ARRAY =
		new ContextTypes[] {
			BODY,
			PATH,
			QUERY,
			FORM,
			HEADER,
		};

	/**
	 * A public read-only list of all the '<em><b>Context Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public static final List<ContextTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Context Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public static ContextTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContextTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Context Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public static ContextTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContextTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Context Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public static ContextTypes get(int value) {
		switch (value) {
			case BODY_VALUE: return BODY;
			case PATH_VALUE: return PATH;
			case QUERY_VALUE: return QUERY;
			case FORM_VALUE: return FORM;
			case HEADER_VALUE: return HEADER;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private ContextTypes(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ContextTypes

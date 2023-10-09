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
 * A representation of the literals of the enumeration '<em><b>Occurrences Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOccurrencesTypes()
 *   
 *    
 */
public enum OccurrencesTypes implements Enumerator {
	/**
	 * The '<em><b>Zero</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #ZERO_VALUE
	 *    
	 *     
	 */
	ZERO(0, "zero", "zero"),

	/**
	 * The '<em><b>One</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #ONE_VALUE
	 *    
	 *     
	 */
	ONE(0, "one", "one"),

	/**
	 * The '<em><b>Unbounded</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #UNBOUNDED_VALUE
	 *    
	 *     
	 */
	UNBOUNDED(1, "unbounded", "unbounded");

	/**
	 * The '<em><b>Zero</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Zero</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #ZERO
	 *    name="zero"
	 *    
	 *     
	 */
	public static final int ZERO_VALUE = 0;

	/**
	 * The '<em><b>One</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>One</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #ONE
	 *    name="one"
	 *    
	 *     
	 */
	public static final int ONE_VALUE = 0;

	/**
	 * The '<em><b>Unbounded</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unbounded</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #UNBOUNDED
	 *    name="unbounded"
	 *    
	 *     
	 */
	public static final int UNBOUNDED_VALUE = 1;

	/**
	 * An array of all the '<em><b>Occurrences Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    
	 */
	private static final OccurrencesTypes[] VALUES_ARRAY =
		new OccurrencesTypes[] {
			ZERO,
			ONE,
			UNBOUNDED,
		};

	/**
	 * A public read-only list of all the '<em><b>Occurrences Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    
	 */
	public static final List<OccurrencesTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Occurrences Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 *   the matching enumerator or <code>null</code>.
	 *    
	 */
	public static OccurrencesTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OccurrencesTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Occurrences Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 *   the matching enumerator or <code>null</code>.
	 *    
	 */
	public static OccurrencesTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OccurrencesTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Occurrences Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 *   the matching enumerator or <code>null</code>.
	 *    
	 */
	public static OccurrencesTypes get(int value) {
		switch (value) {
			case ZERO_VALUE: return ZERO;
			case UNBOUNDED_VALUE: return UNBOUNDED;
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
	private OccurrencesTypes(int value, String name, String literal) {
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
	
} //OccurrencesTypes

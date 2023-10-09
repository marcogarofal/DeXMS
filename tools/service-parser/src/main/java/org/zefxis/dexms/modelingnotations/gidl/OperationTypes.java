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
 * A representation of the literals of the enumeration '<em><b>Operation Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperationTypes()
 *  
 *  
 */
public enum OperationTypes implements Enumerator {
	/**
	 * The '<em><b>One way</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #ONE_WAY_VALUE
	 *  
	 *  
	 */
	ONE_WAY(0, "one_way", "one_way"),

	/**
	 * The '<em><b>Two way sync</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #TWO_WAY_SYNC_VALUE
	 *  
	 *  
	 */
	TWO_WAY_SYNC(1, "two_way_sync", "two_way_sync"),

	/**
	 * The '<em><b>Two way async</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #TWO_WAY_ASYNC_VALUE
	 *  
	 *  
	 */
	TWO_WAY_ASYNC(2, "two_way_async", "two_way_async"),

	/**
	 * The '<em><b>Stream</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *   #STREAM_VALUE
	 *  
	 *  
	 */
	STREAM(3, "stream", "stream");

	/**
	 * The '<em><b>One way</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>One way</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #ONE_WAY
	 *   name="one_way"
	 *  
	 *  
	 */
	public static final int ONE_WAY_VALUE = 0;

	/**
	 * The '<em><b>Two way sync</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Two way sync</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #TWO_WAY_SYNC
	 *   name="two_way_sync"
	 *  
	 *  
	 */
	public static final int TWO_WAY_SYNC_VALUE = 1;

	/**
	 * The '<em><b>Two way async</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Two way async</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #TWO_WAY_ASYNC
	 *   name="two_way_async"
	 *  
	 *  
	 */
	public static final int TWO_WAY_ASYNC_VALUE = 2;

	/**
	 * The '<em><b>Stream</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stream</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *   #STREAM
	 *   name="stream"
	 *  
	 *  
	 */
	public static final int STREAM_VALUE = 3;

	/**
	 * An array of all the '<em><b>Operation Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  
	 */
	private static final OperationTypes[] VALUES_ARRAY =
		new OperationTypes[] {
			ONE_WAY,
			TWO_WAY_SYNC,
			TWO_WAY_ASYNC,
			STREAM,
		};

	/**
	 * A public read-only list of all the '<em><b>Operation Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  
	 */
	public static final List<OperationTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Operation Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 *   the matching enumerator or <code>null</code>.
	 *  
	 */
	public static OperationTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OperationTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Operation Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 *   the matching enumerator or <code>null</code>.
	 *  
	 */
	public static OperationTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OperationTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Operation Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 *   the matching enumerator or <code>null</code>.
	 *  
	 */
	public static OperationTypes get(int value) {
		switch (value) {
			case ONE_WAY_VALUE: return ONE_WAY;
			case TWO_WAY_SYNC_VALUE: return TWO_WAY_SYNC;
			case TWO_WAY_ASYNC_VALUE: return TWO_WAY_ASYNC;
			case STREAM_VALUE: return STREAM;
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
	private OperationTypes(int value, String name, String literal) {
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
	
} //OperationTypes

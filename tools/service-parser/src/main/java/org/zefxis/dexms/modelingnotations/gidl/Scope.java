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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Scope#getName <em>Name</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Scope#getVerb <em>Verb</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Scope#getUri <em>Uri</em>}</li>
 * </ul>
 *
 *  eu.chorevolution.modelingnotations.gidl.GidlPackage#getScope()
 * 
 * 
 */
public interface Scope extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  the value of the '<em>Name</em>' attribute.
	 *  #setName(String)
	 *  eu.chorevolution.modelingnotations.gidl.GidlPackage#getScope_Name()
	 *  required="true"
	 * 
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.chorevolution.modelingnotations.gidl.Scope#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  value the new value of the '<em>Name</em>' attribute.
	 *  #getName()
	 * 
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Verb</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verb</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  the value of the '<em>Verb</em>' attribute.
	 *  #setVerb(String)
	 *  eu.chorevolution.modelingnotations.gidl.GidlPackage#getScope_Verb()
	 * 
	 * 
	 */
	String getVerb();

	/**
	 * Sets the value of the '{@link eu.chorevolution.modelingnotations.gidl.Scope#getVerb <em>Verb</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  value the new value of the '<em>Verb</em>' attribute.
	 *  #getVerb()
	 * 
	 */
	void setVerb(String value);

	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *  the value of the '<em>Uri</em>' attribute.
	 *  #setUri(String)
	 *  eu.chorevolution.modelingnotations.gidl.GidlPackage#getScope_Uri()
	 * 
	 * 
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link eu.chorevolution.modelingnotations.gidl.Scope#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *  value the new value of the '<em>Uri</em>' attribute.
	 *  #getUri()
	 * 
	 */
	void setUri(String value);

} // Scope

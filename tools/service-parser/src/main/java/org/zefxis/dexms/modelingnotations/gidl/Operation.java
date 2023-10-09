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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Operation#getName <em>Name</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Operation#getType <em>Type</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Operation#getHasScope <em>Has Scope</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Operation#getInputData <em>Input Data</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Operation#getOutputData <em>Output Data</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.Operation#getQos <em>Qos</em>}</li>
 * </ul>
 *
 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperation()
 *   
 *    
 */
public interface Operation extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *      the value of the '<em>Name</em>' attribute.
	 *   #setName(String)
	 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperation_Name()
	 *    default="" required="true"
	 *    
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.chorevolution.modelingnotations.gidl.Operation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 *   #getName()
	 *    
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *      the value of the '<em>Type</em>' attribute.
	 *   #setType(OperationTypes)
	 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperation_Type()
	 *    dataType="eu.chorevolution.modelingnotations.gidl.OperationTypesObject" required="true"
	 *    
	 */
	OperationTypes getType();

	/**
	 * Sets the value of the '{@link eu.chorevolution.modelingnotations.gidl.Operation#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 *   #getType()
	 *    
	 */
	void setType(OperationTypes value);

	/**
	 * Returns the value of the '<em><b>Has Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Scope</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *      the value of the '<em>Has Scope</em>' containment reference.
	 *   #setHasScope(Scope)
	 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperation_HasScope()
	 *    containment="true" required="true"
	 *    
	 */
	Scope getHasScope();

	/**
	 * Sets the value of the '{@link eu.chorevolution.modelingnotations.gidl.Operation#getHasScope <em>Has Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Scope</em>' containment reference.
	 *   #getHasScope()
	 *    
	 */
	void setHasScope(Scope value);

	/**
	 * Returns the value of the '<em><b>Input Data</b></em>' containment reference list.
	 * The list contents are of type {@link eu.chorevolution.modelingnotations.gidl.Data}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *      the value of the '<em>Input Data</em>' containment reference list.
	 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperation_InputData()
	 *    containment="true"
	 *    
	 */
	EList<Data> getInputData();

	/**
	 * Returns the value of the '<em><b>Output Data</b></em>' containment reference list.
	 * The list contents are of type {@link eu.chorevolution.modelingnotations.gidl.Data}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *      the value of the '<em>Output Data</em>' containment reference list.
	 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperation_OutputData()
	 *    containment="true"
	 *    
	 */
	EList<Data> getOutputData();

	/**
	 * Returns the value of the '<em><b>Qos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qos</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *      the value of the '<em>Qos</em>' attribute.
	 *   #setQos(QosTypes)
	 *   eu.chorevolution.modelingnotations.gidl.GidlPackage#getOperation_Qos()
	 *    dataType="eu.chorevolution.modelingnotations.gidl.QosTypesObject" required="true"
	 *    
	 */
	QosTypes getQos();

	/**
	 * Sets the value of the '{@link eu.chorevolution.modelingnotations.gidl.Operation#getQos <em>Qos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qos</em>' attribute.
	 *   #getQos()
	 *    
	 */
	void setQos(QosTypes value);

} // Operation

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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 *  eu.chorevolution.modelingnotations.gidl.GidlPackage
 * 
 */
public interface GidlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	GidlFactory eINSTANCE = org.zefxis.dexms.modelingnotations.gidl.impl.GidlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>GIDL Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	GIDLModel createGIDLModel();

	/**
	 * Returns a new object of class '<em>Interface Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	InterfaceDescription createInterfaceDescription();

	/**
	 * Returns a new object of class '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	Operation createOperation();

	/**
	 * Returns a new object of class '<em>Scope</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	Scope createScope();

	/**
	 * Returns a new object of class '<em>Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	Data createData();

	/**
	 * Returns a new object of class '<em>Simple Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	SimpleType createSimpleType();

	/**
	 * Returns a new object of class '<em>Complex Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	ComplexType createComplexType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	GidlPackage getGidlPackage();

} //GidlFactory

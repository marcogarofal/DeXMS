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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->

 */
public interface GidlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	String eNAME = "gidl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	String eNS_URI = "http://eu.chorevolution/modelingnotations/gidl";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	String eNS_PREFIX = "gidl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	GidlPackage eINSTANCE = org.zefxis.dexms.modelingnotations.gidl.impl.GidlPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.impl.GIDLModelImpl <em>GIDL Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int GIDL_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Host Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int GIDL_MODEL__HOST_ADDRESS = 0;

	/**
	 * The feature id for the '<em><b>Protocol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int GIDL_MODEL__PROTOCOL = 1;

	/**
	 * The feature id for the '<em><b>Has Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int GIDL_MODEL__HAS_INTERFACES = 2;

	/**
	 * The number of structural features of the '<em>GIDL Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int GIDL_MODEL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>GIDL Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int GIDL_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.impl.InterfaceDescriptionImpl <em>Interface Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int INTERFACE_DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int INTERFACE_DESCRIPTION__ROLE = 0;

	/**
	 * The feature id for the '<em><b>Has Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int INTERFACE_DESCRIPTION__HAS_OPERATIONS = 1;

	/**
	 * The number of structural features of the '<em>Interface Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int INTERFACE_DESCRIPTION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Interface Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int INTERFACE_DESCRIPTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Has Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->

	 */
	int OPERATION__HAS_SCOPE = 2;

	/**
	 * The feature id for the '<em><b>Input Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION__INPUT_DATA = 3;

	/**
	 * The feature id for the '<em><b>Output Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION__OUTPUT_DATA = 4;

	/**
	 * The feature id for the '<em><b>Qos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION__QOS = 5;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int OPERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.impl.ScopeImpl <em>Scope</em>}' class.
	 * <!-- begin-user-doc -->

	 */
	int SCOPE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int SCOPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Verb</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int SCOPE__VERB = 1;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int SCOPE__URI = 2;

	/**
	 * The number of structural features of the '<em>Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int SCOPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int SCOPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.impl.DataImpl <em>Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int DATA = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int DATA__NAME = 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int DATA__CONTEXT = 1;

	/**
	 * The feature id for the '<em><b>Has Data Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int DATA__HAS_DATA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Media</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int DATA__MEDIA = 3;

	/**
	 * The number of structural features of the '<em>Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	int DATA_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int DATA_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.DataType <em>Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.DataType
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getDataType()
	 * 
	 */
	int DATA_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int DATA_TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Min Occurs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int DATA_TYPE__MIN_OCCURS = 1;

	/**
	 * The feature id for the '<em><b>Max Occurs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int DATA_TYPE__MAX_OCCURS = 2;

	/**
	 * The number of structural features of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int DATA_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int DATA_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl <em>Simple Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getSimpleType()
	 * 
	 */
	int SIMPLE_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int SIMPLE_TYPE__NAME = DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Min Occurs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int SIMPLE_TYPE__MIN_OCCURS = DATA_TYPE__MIN_OCCURS;

	/**
	 * The feature id for the '<em><b>Max Occurs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int SIMPLE_TYPE__MAX_OCCURS = DATA_TYPE__MAX_OCCURS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int SIMPLE_TYPE__TYPE = DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Simple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int SIMPLE_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Simple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int SIMPLE_TYPE_OPERATION_COUNT = DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.impl.ComplexTypeImpl <em>Complex Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.impl.ComplexTypeImpl
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getComplexType()
	 * 
	 */
	int COMPLEX_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int COMPLEX_TYPE__NAME = DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Min Occurs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int COMPLEX_TYPE__MIN_OCCURS = DATA_TYPE__MIN_OCCURS;

	/**
	 * The feature id for the '<em><b>Max Occurs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int COMPLEX_TYPE__MAX_OCCURS = DATA_TYPE__MAX_OCCURS;

	/**
	 * The feature id for the '<em><b>Has Data Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int COMPLEX_TYPE__HAS_DATA_TYPE = DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Complex Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int COMPLEX_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Complex Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 *  
	 */
	int COMPLEX_TYPE_OPERATION_COUNT = DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.RoleTypes <em>Role Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.RoleTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getRoleTypes()
	 * 
	 */
	int ROLE_TYPES = 8;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.OperationTypes <em>Operation Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.OperationTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOperationTypes()
	 * 
	 */
	int OPERATION_TYPES = 9;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.ProtocolTypes <em>Protocol Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.ProtocolTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getProtocolTypes()
	 * 
	 */
	int PROTOCOL_TYPES = 10;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.SimpleTypes <em>Simple Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.SimpleTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getSimpleTypes()
	 * 
	 */
	int SIMPLE_TYPES = 11;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.ContextTypes <em>Context Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.ContextTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getContextTypes()
	 * 
	 */
	int CONTEXT_TYPES = 12;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.OccurrencesTypes <em>Occurrences Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.OccurrencesTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOccurrencesTypes()
	 * 
	 */
	int OCCURRENCES_TYPES = 13;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.QosTypes <em>Qos Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.QosTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getQosTypes()
	 * 
	 */
	int QOS_TYPES = 14;

	/**
	 * The meta object id for the '{@link eu.chorevolution.modelingnotations.gidl.MediaTypes <em>Media Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.MediaTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getMediaTypes()
	 * 
	 */
	int MEDIA_TYPES = 15;

	/**
	 * The meta object id for the '<em>Role Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.RoleTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getRoleTypesObject()
	 * 
	 */
	int ROLE_TYPES_OBJECT = 16;

	/**
	 * The meta object id for the '<em>Protocol Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.ProtocolTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getProtocolTypesObject()
	 * 
	 */
	int PROTOCOL_TYPES_OBJECT = 17;

	/**
	 * The meta object id for the '<em>Operation Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.OperationTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOperationTypesObject()
	 * 
	 */
	int OPERATION_TYPES_OBJECT = 18;

	/**
	 * The meta object id for the '<em>Simple Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.SimpleTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getSimpleTypesObject()
	 * 
	 */
	int SIMPLE_TYPES_OBJECT = 19;

	/**
	 * The meta object id for the '<em>Context Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.ContextTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getContextTypesObject()
	 * 
	 */
	int CONTEXT_TYPES_OBJECT = 20;

	/**
	 * The meta object id for the '<em>Occurrences Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.OccurrencesTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOccurrencesTypesObject()
	 * 
	 */
	int OCCURRENCES_TYPES_OBJECT = 21;

	/**
	 * The meta object id for the '<em>Qos Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.QosTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getQosTypesObject()
	 * 
	 */
	int QOS_TYPES_OBJECT = 22;

	/**
	 * The meta object id for the '<em>Media Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *    eu.chorevolution.modelingnotations.gidl.MediaTypes
	 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getMediaTypesObject()
	 * 
	 */
	int MEDIA_TYPES_OBJECT = 23;


	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.GIDLModel <em>GIDL Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>GIDL Model</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.GIDLModel
	 * 
	 */
	EClass getGIDLModel();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.GIDLModel#getHostAddress <em>Host Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Host Address</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.GIDLModel#getHostAddress()
	 *    #getGIDLModel()
	 * 
	 */
	EAttribute getGIDLModel_HostAddress();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.GIDLModel#getProtocol <em>Protocol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Protocol</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.GIDLModel#getProtocol()
	 *    #getGIDLModel()
	 * 
	 */
	EAttribute getGIDLModel_Protocol();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.chorevolution.modelingnotations.gidl.GIDLModel#getHasInterfaces <em>Has Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the containment reference list '<em>Has Interfaces</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.GIDLModel#getHasInterfaces()
	 *    #getGIDLModel()
	 * 
	 */
	EReference getGIDLModel_HasInterfaces();

	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.InterfaceDescription <em>Interface Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>Interface Description</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.InterfaceDescription
	 * 
	 */
	EClass getInterfaceDescription();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.InterfaceDescription#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Role</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.InterfaceDescription#getRole()
	 *    #getInterfaceDescription()
	 * 
	 */
	EAttribute getInterfaceDescription_Role();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.chorevolution.modelingnotations.gidl.InterfaceDescription#getHasOperations <em>Has Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the containment reference list '<em>Has Operations</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.InterfaceDescription#getHasOperations()
	 *    #getInterfaceDescription()
	 * 
	 */
	EReference getInterfaceDescription_HasOperations();

	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>Operation</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Operation
	 * 
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Operation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Name</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Operation#getName()
	 *    #getOperation()
	 * 
	 */
	EAttribute getOperation_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Operation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Type</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Operation#getType()
	 *    #getOperation()
	 * 
	 */
	EAttribute getOperation_Type();

	/**
	 * Returns the meta object for the containment reference '{@link eu.chorevolution.modelingnotations.gidl.Operation#getHasScope <em>Has Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the containment reference '<em>Has Scope</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Operation#getHasScope()
	 *    #getOperation()
	 * 
	 */
	EReference getOperation_HasScope();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.chorevolution.modelingnotations.gidl.Operation#getInputData <em>Input Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the containment reference list '<em>Input Data</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Operation#getInputData()
	 *    #getOperation()
	 * 
	 */
	EReference getOperation_InputData();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.chorevolution.modelingnotations.gidl.Operation#getOutputData <em>Output Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the containment reference list '<em>Output Data</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Operation#getOutputData()
	 *    #getOperation()
	 * 
	 */
	EReference getOperation_OutputData();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Operation#getQos <em>Qos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Qos</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Operation#getQos()
	 *    #getOperation()
	 * 
	 */
	EAttribute getOperation_Qos();

	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.Scope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>Scope</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Scope
	 * 
	 */
	EClass getScope();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Scope#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Name</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Scope#getName()
	 *    #getScope()
	 * 
	 */
	EAttribute getScope_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Scope#getVerb <em>Verb</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Verb</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Scope#getVerb()
	 *    #getScope()
	 * 
	 */
	EAttribute getScope_Verb();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Scope#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Uri</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Scope#getUri()
	 *    #getScope()
	 * 
	 */
	EAttribute getScope_Uri();

	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.Data <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>Data</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Data
	 * 
	 */
	EClass getData();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Data#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Name</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Data#getName()
	 *    #getData()
	 * 
	 */
	EAttribute getData_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Data#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Context</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Data#getContext()
	 *    #getData()
	 * 
	 */
	EAttribute getData_Context();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.chorevolution.modelingnotations.gidl.Data#getHasDataType <em>Has Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the containment reference list '<em>Has Data Type</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Data#getHasDataType()
	 *    #getData()
	 * 
	 */
	EReference getData_HasDataType();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.Data#getMedia <em>Media</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Media</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.Data#getMedia()
	 *    #getData()
	 * 
	 */
	EAttribute getData_Media();

	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>Data Type</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.DataType
	 * 
	 */
	EClass getDataType();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.DataType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Name</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.DataType#getName()
	 *    #getDataType()
	 * 
	 */
	EAttribute getDataType_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.DataType#getMinOccurs <em>Min Occurs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Min Occurs</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.DataType#getMinOccurs()
	 *    #getDataType()
	 * 
	 */
	EAttribute getDataType_MinOccurs();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.DataType#getMaxOccurs <em>Max Occurs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Max Occurs</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.DataType#getMaxOccurs()
	 *    #getDataType()
	 * 
	 */
	EAttribute getDataType_MaxOccurs();

	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.SimpleType <em>Simple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>Simple Type</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.SimpleType
	 * 
	 */
	EClass getSimpleType();

	/**
	 * Returns the meta object for the attribute '{@link eu.chorevolution.modelingnotations.gidl.SimpleType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the attribute '<em>Type</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.SimpleType#getType()
	 *    #getSimpleType()
	 * 
	 */
	EAttribute getSimpleType_Type();

	/**
	 * Returns the meta object for class '{@link eu.chorevolution.modelingnotations.gidl.ComplexType <em>Complex Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for class '<em>Complex Type</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.ComplexType
	 * 
	 */
	EClass getComplexType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.chorevolution.modelingnotations.gidl.ComplexType#getHasDataType <em>Has Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for the containment reference list '<em>Has Data Type</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.ComplexType#getHasDataType()
	 *    #getComplexType()
	 * 
	 */
	EReference getComplexType_HasDataType();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.RoleTypes <em>Role Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Role Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.RoleTypes
	 * 
	 */
	EEnum getRoleTypes();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.OperationTypes <em>Operation Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Operation Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.OperationTypes
	 * 
	 */
	EEnum getOperationTypes();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.ProtocolTypes <em>Protocol Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Protocol Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.ProtocolTypes
	 * 
	 */
	EEnum getProtocolTypes();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.SimpleTypes <em>Simple Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Simple Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.SimpleTypes
	 * 
	 */
	EEnum getSimpleTypes();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.ContextTypes <em>Context Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Context Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.ContextTypes
	 * 
	 */
	EEnum getContextTypes();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.OccurrencesTypes <em>Occurrences Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Occurrences Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.OccurrencesTypes
	 * 
	 */
	EEnum getOccurrencesTypes();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.QosTypes <em>Qos Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Qos Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.QosTypes
	 * 
	 */
	EEnum getQosTypes();

	/**
	 * Returns the meta object for enum '{@link eu.chorevolution.modelingnotations.gidl.MediaTypes <em>Media Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for enum '<em>Media Types</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.MediaTypes
	 * 
	 */
	EEnum getMediaTypes();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.RoleTypes <em>Role Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Role Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.RoleTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.RoleTypes"
	 *        extendedMetaData="baseType='RoleTypes'"
	 * 
	 */
	EDataType getRoleTypesObject();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.ProtocolTypes <em>Protocol Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Protocol Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.ProtocolTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.ProtocolTypes"
	 *        extendedMetaData="baseType='ProtocolTypes'"
	 * 
	 */
	EDataType getProtocolTypesObject();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.OperationTypes <em>Operation Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Operation Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.OperationTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.OperationTypes"
	 *        extendedMetaData="baseType='OperationTypes'"
	 * 
	 */
	EDataType getOperationTypesObject();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.SimpleTypes <em>Simple Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Simple Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.SimpleTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.SimpleTypes"
	 *        extendedMetaData="baseType='SimpleTypes'"
	 * 
	 */
	EDataType getSimpleTypesObject();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.ContextTypes <em>Context Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Context Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.ContextTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.ContextTypes"
	 *        extendedMetaData="baseType='ContextTypes'"
	 * 
	 */
	EDataType getContextTypesObject();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.OccurrencesTypes <em>Occurrences Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Occurrences Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.OccurrencesTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.OccurrencesTypes"
	 *        extendedMetaData="baseType='OccurrencesTypes'"
	 * 
	 */
	EDataType getOccurrencesTypesObject();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.QosTypes <em>Qos Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Qos Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.QosTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.QosTypes"
	 *        extendedMetaData="baseType='QosTypes'"
	 * 
	 */
	EDataType getQosTypesObject();

	/**
	 * Returns the meta object for data type '{@link eu.chorevolution.modelingnotations.gidl.MediaTypes <em>Media Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the meta object for data type '<em>Media Types Object</em>'.
	 *    eu.chorevolution.modelingnotations.gidl.MediaTypes
	 *   instanceClass="eu.chorevolution.modelingnotations.gidl.MediaTypes"
	 *        extendedMetaData="baseType='MediaTypes'"
	 * 
	 */
	EDataType getMediaTypesObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *     the factory that creates the instances of the model.
	 * 
	 */
	GidlFactory getGidlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.impl.GIDLModelImpl <em>GIDL Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.impl.GIDLModelImpl
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getGIDLModel()
		 * 
		 */
		EClass GIDL_MODEL = eINSTANCE.getGIDLModel();

		/**
		 * The meta object literal for the '<em><b>Host Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute GIDL_MODEL__HOST_ADDRESS = eINSTANCE.getGIDLModel_HostAddress();

		/**
		 * The meta object literal for the '<em><b>Protocol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute GIDL_MODEL__PROTOCOL = eINSTANCE.getGIDLModel_Protocol();

		/**
		 * The meta object literal for the '<em><b>Has Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EReference GIDL_MODEL__HAS_INTERFACES = eINSTANCE.getGIDLModel_HasInterfaces();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.impl.InterfaceDescriptionImpl <em>Interface Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.impl.InterfaceDescriptionImpl
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getInterfaceDescription()
		 * 
		 */
		EClass INTERFACE_DESCRIPTION = eINSTANCE.getInterfaceDescription();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute INTERFACE_DESCRIPTION__ROLE = eINSTANCE.getInterfaceDescription_Role();

		/**
		 * The meta object literal for the '<em><b>Has Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EReference INTERFACE_DESCRIPTION__HAS_OPERATIONS = eINSTANCE.getInterfaceDescription_HasOperations();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.impl.OperationImpl
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOperation()
		 * 
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute OPERATION__NAME = eINSTANCE.getOperation_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute OPERATION__TYPE = eINSTANCE.getOperation_Type();

		/**
		 * The meta object literal for the '<em><b>Has Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EReference OPERATION__HAS_SCOPE = eINSTANCE.getOperation_HasScope();

		/**
		 * The meta object literal for the '<em><b>Input Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EReference OPERATION__INPUT_DATA = eINSTANCE.getOperation_InputData();

		/**
		 * The meta object literal for the '<em><b>Output Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EReference OPERATION__OUTPUT_DATA = eINSTANCE.getOperation_OutputData();

		/**
		 * The meta object literal for the '<em><b>Qos</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute OPERATION__QOS = eINSTANCE.getOperation_Qos();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.impl.ScopeImpl <em>Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.impl.ScopeImpl
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getScope()
		 * 
		 */
		EClass SCOPE = eINSTANCE.getScope();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute SCOPE__NAME = eINSTANCE.getScope_Name();

		/**
		 * The meta object literal for the '<em><b>Verb</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute SCOPE__VERB = eINSTANCE.getScope_Verb();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute SCOPE__URI = eINSTANCE.getScope_Uri();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.impl.DataImpl <em>Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.impl.DataImpl
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getData()
		 * 
		 */
		EClass DATA = eINSTANCE.getData();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute DATA__NAME = eINSTANCE.getData_Name();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute DATA__CONTEXT = eINSTANCE.getData_Context();

		/**
		 * The meta object literal for the '<em><b>Has Data Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EReference DATA__HAS_DATA_TYPE = eINSTANCE.getData_HasDataType();

		/**
		 * The meta object literal for the '<em><b>Media</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute DATA__MEDIA = eINSTANCE.getData_Media();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.DataType <em>Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.DataType
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getDataType()
		 * 
		 */
		EClass DATA_TYPE = eINSTANCE.getDataType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute DATA_TYPE__NAME = eINSTANCE.getDataType_Name();

		/**
		 * The meta object literal for the '<em><b>Min Occurs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute DATA_TYPE__MIN_OCCURS = eINSTANCE.getDataType_MinOccurs();

		/**
		 * The meta object literal for the '<em><b>Max Occurs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute DATA_TYPE__MAX_OCCURS = eINSTANCE.getDataType_MaxOccurs();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl <em>Simple Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getSimpleType()
		 * 
		 */
		EClass SIMPLE_TYPE = eINSTANCE.getSimpleType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EAttribute SIMPLE_TYPE__TYPE = eINSTANCE.getSimpleType_Type();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.impl.ComplexTypeImpl <em>Complex Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.impl.ComplexTypeImpl
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getComplexType()
		 * 
		 */
		EClass COMPLEX_TYPE = eINSTANCE.getComplexType();

		/**
		 * The meta object literal for the '<em><b>Has Data Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 */
		EReference COMPLEX_TYPE__HAS_DATA_TYPE = eINSTANCE.getComplexType_HasDataType();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.RoleTypes <em>Role Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.RoleTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getRoleTypes()
		 * 
		 */
		EEnum ROLE_TYPES = eINSTANCE.getRoleTypes();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.OperationTypes <em>Operation Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.OperationTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOperationTypes()
		 * 
		 */
		EEnum OPERATION_TYPES = eINSTANCE.getOperationTypes();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.ProtocolTypes <em>Protocol Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.ProtocolTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getProtocolTypes()
		 * 
		 */
		EEnum PROTOCOL_TYPES = eINSTANCE.getProtocolTypes();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.SimpleTypes <em>Simple Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.SimpleTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getSimpleTypes()
		 * 
		 */
		EEnum SIMPLE_TYPES = eINSTANCE.getSimpleTypes();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.ContextTypes <em>Context Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.ContextTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getContextTypes()
		 * 
		 */
		EEnum CONTEXT_TYPES = eINSTANCE.getContextTypes();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.OccurrencesTypes <em>Occurrences Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.OccurrencesTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOccurrencesTypes()
		 * 
		 */
		EEnum OCCURRENCES_TYPES = eINSTANCE.getOccurrencesTypes();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.QosTypes <em>Qos Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.QosTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getQosTypes()
		 * 
		 */
		EEnum QOS_TYPES = eINSTANCE.getQosTypes();

		/**
		 * The meta object literal for the '{@link eu.chorevolution.modelingnotations.gidl.MediaTypes <em>Media Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.MediaTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getMediaTypes()
		 * 
		 */
		EEnum MEDIA_TYPES = eINSTANCE.getMediaTypes();

		/**
		 * The meta object literal for the '<em>Role Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.RoleTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getRoleTypesObject()
		 * 
		 */
		EDataType ROLE_TYPES_OBJECT = eINSTANCE.getRoleTypesObject();

		/**
		 * The meta object literal for the '<em>Protocol Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.ProtocolTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getProtocolTypesObject()
		 * 
		 */
		EDataType PROTOCOL_TYPES_OBJECT = eINSTANCE.getProtocolTypesObject();

		/**
		 * The meta object literal for the '<em>Operation Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.OperationTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOperationTypesObject()
		 * 
		 */
		EDataType OPERATION_TYPES_OBJECT = eINSTANCE.getOperationTypesObject();

		/**
		 * The meta object literal for the '<em>Simple Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.SimpleTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getSimpleTypesObject()
		 * 
		 */
		EDataType SIMPLE_TYPES_OBJECT = eINSTANCE.getSimpleTypesObject();

		/**
		 * The meta object literal for the '<em>Context Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.ContextTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getContextTypesObject()
		 * 
		 */
		EDataType CONTEXT_TYPES_OBJECT = eINSTANCE.getContextTypesObject();

		/**
		 * The meta object literal for the '<em>Occurrences Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.OccurrencesTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getOccurrencesTypesObject()
		 * 
		 */
		EDataType OCCURRENCES_TYPES_OBJECT = eINSTANCE.getOccurrencesTypesObject();

		/**
		 * The meta object literal for the '<em>Qos Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.QosTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getQosTypesObject()
		 * 
		 */
		EDataType QOS_TYPES_OBJECT = eINSTANCE.getQosTypesObject();

		/**
		 * The meta object literal for the '<em>Media Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *    eu.chorevolution.modelingnotations.gidl.MediaTypes
		 *    eu.chorevolution.modelingnotations.gidl.impl.GidlPackageImpl#getMediaTypesObject()
		 * 
		 */
		EDataType MEDIA_TYPES_OBJECT = eINSTANCE.getMediaTypesObject();

	}

} //GidlPackage

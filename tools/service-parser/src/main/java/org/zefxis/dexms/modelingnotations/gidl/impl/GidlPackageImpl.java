package org.zefxis.dexms.modelingnotations.gidl.impl;

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

import org.zefxis.dexms.modelingnotations.gidl.ComplexType;
import org.zefxis.dexms.modelingnotations.gidl.ContextTypes;
import org.zefxis.dexms.modelingnotations.gidl.Data;
import org.zefxis.dexms.modelingnotations.gidl.DataType;
import org.zefxis.dexms.modelingnotations.gidl.GIDLModel;
import org.zefxis.dexms.modelingnotations.gidl.GidlFactory;
import org.zefxis.dexms.modelingnotations.gidl.GidlPackage;
import org.zefxis.dexms.modelingnotations.gidl.InterfaceDescription;
import org.zefxis.dexms.modelingnotations.gidl.MediaTypes;
import org.zefxis.dexms.modelingnotations.gidl.OccurrencesTypes;
import org.zefxis.dexms.modelingnotations.gidl.Operation;
import org.zefxis.dexms.modelingnotations.gidl.OperationTypes;
import org.zefxis.dexms.modelingnotations.gidl.ProtocolTypes;
import org.zefxis.dexms.modelingnotations.gidl.QosTypes;
import org.zefxis.dexms.modelingnotations.gidl.RoleTypes;
import org.zefxis.dexms.modelingnotations.gidl.Scope;
import org.zefxis.dexms.modelingnotations.gidl.SimpleType;
import org.zefxis.dexms.modelingnotations.gidl.SimpleTypes;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->

 */
public class GidlPackageImpl extends EPackageImpl implements GidlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass gidlModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass interfaceDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass scopeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass dataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass dataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass simpleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EClass complexTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum roleTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum operationTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum protocolTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum simpleTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum contextTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum occurrencesTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum qosTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EEnum mediaTypesEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType roleTypesObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType protocolTypesObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType operationTypesObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType simpleTypesObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType contextTypesObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType occurrencesTypesObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType qosTypesObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private EDataType mediaTypesObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private GidlPackageImpl() {
		super(eNS_URI, GidlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link GidlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public static GidlPackage init() {
		if (isInited) return (GidlPackage)EPackage.Registry.INSTANCE.getEPackage(GidlPackage.eNS_URI);

		// Obtain or create and register package
		GidlPackageImpl theGidlPackage = (GidlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GidlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GidlPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theGidlPackage.createPackageContents();

		// Initialize created meta-data
		theGidlPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGidlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GidlPackage.eNS_URI, theGidlPackage);
		return theGidlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getGIDLModel() {
		return gidlModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getGIDLModel_HostAddress() {
		return (EAttribute)gidlModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getGIDLModel_Protocol() {
		return (EAttribute)gidlModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EReference getGIDLModel_HasInterfaces() {
		return (EReference)gidlModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getInterfaceDescription() {
		return interfaceDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getInterfaceDescription_Role() {
		return (EAttribute)interfaceDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EReference getInterfaceDescription_HasOperations() {
		return (EReference)interfaceDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getOperation_Name() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getOperation_Type() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EReference getOperation_HasScope() {
		return (EReference)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EReference getOperation_InputData() {
		return (EReference)operationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EReference getOperation_OutputData() {
		return (EReference)operationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getOperation_Qos() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getScope() {
		return scopeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getScope_Name() {
		return (EAttribute)scopeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getScope_Verb() {
		return (EAttribute)scopeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getScope_Uri() {
		return (EAttribute)scopeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getData() {
		return dataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getData_Name() {
		return (EAttribute)dataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getData_Context() {
		return (EAttribute)dataEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->

	 */
	public EReference getData_HasDataType() {
		return (EReference)dataEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getData_Media() {
		return (EAttribute)dataEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getDataType() {
		return dataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->

	 */
	public EAttribute getDataType_Name() {
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getDataType_MinOccurs() {
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getDataType_MaxOccurs() {
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getSimpleType() {
		return simpleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EAttribute getSimpleType_Type() {
		return (EAttribute)simpleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EClass getComplexType() {
		return complexTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EReference getComplexType_HasDataType() {
		return (EReference)complexTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EEnum getRoleTypes() {
		return roleTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EEnum getOperationTypes() {
		return operationTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EEnum getProtocolTypes() {
		return protocolTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EEnum getSimpleTypes() {
		return simpleTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EEnum getContextTypes() {
		return contextTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EEnum getOccurrencesTypes() {
		return occurrencesTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->

	 */
	public EEnum getQosTypes() {
		return qosTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EEnum getMediaTypes() {
		return mediaTypesEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EDataType getRoleTypesObject() {
		return roleTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EDataType getProtocolTypesObject() {
		return protocolTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EDataType getOperationTypesObject() {
		return operationTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->

	 */
	public EDataType getSimpleTypesObject() {
		return simpleTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EDataType getContextTypesObject() {
		return contextTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EDataType getOccurrencesTypesObject() {
		return occurrencesTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EDataType getQosTypesObject() {
		return qosTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EDataType getMediaTypesObject() {
		return mediaTypesObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public GidlFactory getGidlFactory() {
		return (GidlFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		gidlModelEClass = createEClass(GIDL_MODEL);
		createEAttribute(gidlModelEClass, GIDL_MODEL__HOST_ADDRESS);
		createEAttribute(gidlModelEClass, GIDL_MODEL__PROTOCOL);
		createEReference(gidlModelEClass, GIDL_MODEL__HAS_INTERFACES);

		interfaceDescriptionEClass = createEClass(INTERFACE_DESCRIPTION);
		createEAttribute(interfaceDescriptionEClass, INTERFACE_DESCRIPTION__ROLE);
		createEReference(interfaceDescriptionEClass, INTERFACE_DESCRIPTION__HAS_OPERATIONS);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__NAME);
		createEAttribute(operationEClass, OPERATION__TYPE);
		createEReference(operationEClass, OPERATION__HAS_SCOPE);
		createEReference(operationEClass, OPERATION__INPUT_DATA);
		createEReference(operationEClass, OPERATION__OUTPUT_DATA);
		createEAttribute(operationEClass, OPERATION__QOS);

		scopeEClass = createEClass(SCOPE);
		createEAttribute(scopeEClass, SCOPE__NAME);
		createEAttribute(scopeEClass, SCOPE__VERB);
		createEAttribute(scopeEClass, SCOPE__URI);

		dataEClass = createEClass(DATA);
		createEAttribute(dataEClass, DATA__NAME);
		createEAttribute(dataEClass, DATA__CONTEXT);
		createEReference(dataEClass, DATA__HAS_DATA_TYPE);
		createEAttribute(dataEClass, DATA__MEDIA);

		dataTypeEClass = createEClass(DATA_TYPE);
		createEAttribute(dataTypeEClass, DATA_TYPE__NAME);
		createEAttribute(dataTypeEClass, DATA_TYPE__MIN_OCCURS);
		createEAttribute(dataTypeEClass, DATA_TYPE__MAX_OCCURS);

		simpleTypeEClass = createEClass(SIMPLE_TYPE);
		createEAttribute(simpleTypeEClass, SIMPLE_TYPE__TYPE);

		complexTypeEClass = createEClass(COMPLEX_TYPE);
		createEReference(complexTypeEClass, COMPLEX_TYPE__HAS_DATA_TYPE);

		// Create enums
		roleTypesEEnum = createEEnum(ROLE_TYPES);
		operationTypesEEnum = createEEnum(OPERATION_TYPES);
		protocolTypesEEnum = createEEnum(PROTOCOL_TYPES);
		simpleTypesEEnum = createEEnum(SIMPLE_TYPES);
		contextTypesEEnum = createEEnum(CONTEXT_TYPES);
		occurrencesTypesEEnum = createEEnum(OCCURRENCES_TYPES);
		qosTypesEEnum = createEEnum(QOS_TYPES);
		mediaTypesEEnum = createEEnum(MEDIA_TYPES);

		// Create data types
		roleTypesObjectEDataType = createEDataType(ROLE_TYPES_OBJECT);
		protocolTypesObjectEDataType = createEDataType(PROTOCOL_TYPES_OBJECT);
		operationTypesObjectEDataType = createEDataType(OPERATION_TYPES_OBJECT);
		simpleTypesObjectEDataType = createEDataType(SIMPLE_TYPES_OBJECT);
		contextTypesObjectEDataType = createEDataType(CONTEXT_TYPES_OBJECT);
		occurrencesTypesObjectEDataType = createEDataType(OCCURRENCES_TYPES_OBJECT);
		qosTypesObjectEDataType = createEDataType(QOS_TYPES_OBJECT);
		mediaTypesObjectEDataType = createEDataType(MEDIA_TYPES_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		simpleTypeEClass.getESuperTypes().add(this.getDataType());
		complexTypeEClass.getESuperTypes().add(this.getDataType());

		// Initialize classes, features, and operations; add parameters
		initEClass(gidlModelEClass, GIDLModel.class, "GIDLModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGIDLModel_HostAddress(), ecorePackage.getEString(), "hostAddress", null, 1, 1, GIDLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGIDLModel_Protocol(), this.getProtocolTypesObject(), "protocol", null, 1, 1, GIDLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGIDLModel_HasInterfaces(), this.getInterfaceDescription(), null, "hasInterfaces", null, 1, -1, GIDLModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(interfaceDescriptionEClass, InterfaceDescription.class, "InterfaceDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInterfaceDescription_Role(), this.getRoleTypesObject(), "role", null, 1, 1, InterfaceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInterfaceDescription_HasOperations(), this.getOperation(), null, "hasOperations", null, 1, -1, InterfaceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_Name(), ecorePackage.getEString(), "name", "", 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Type(), this.getOperationTypesObject(), "type", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_HasScope(), this.getScope(), null, "hasScope", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_InputData(), this.getData(), null, "inputData", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_OutputData(), this.getData(), null, "outputData", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Qos(), this.getQosTypesObject(), "qos", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeEClass, Scope.class, "Scope", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScope_Name(), ecorePackage.getEString(), "name", null, 1, 1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScope_Verb(), ecorePackage.getEString(), "verb", null, 0, 1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScope_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, Scope.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataEClass, Data.class, "Data", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getData_Name(), ecorePackage.getEString(), "name", null, 1, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getData_Context(), this.getContextTypesObject(), "context", null, 0, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getData_HasDataType(), this.getDataType(), null, "hasDataType", null, 1, -1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getData_Media(), this.getMediaTypesObject(), "media", null, 0, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataTypeEClass, DataType.class, "DataType", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataType_Name(), ecorePackage.getEString(), "name", null, 1, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataType_MinOccurs(), this.getOccurrencesTypesObject(), "minOccurs", null, 1, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataType_MaxOccurs(), this.getOccurrencesTypesObject(), "maxOccurs", null, 1, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simpleTypeEClass, SimpleType.class, "SimpleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSimpleType_Type(), this.getSimpleTypesObject(), "type", null, 1, 1, SimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(complexTypeEClass, ComplexType.class, "ComplexType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComplexType_HasDataType(), this.getDataType(), null, "hasDataType", null, 1, -1, ComplexType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(roleTypesEEnum, RoleTypes.class, "RoleTypes");
		addEEnumLiteral(roleTypesEEnum, RoleTypes.PROVIDER);
		addEEnumLiteral(roleTypesEEnum, RoleTypes.CONSUMER);

		initEEnum(operationTypesEEnum, OperationTypes.class, "OperationTypes");
		addEEnumLiteral(operationTypesEEnum, OperationTypes.ONE_WAY);
		addEEnumLiteral(operationTypesEEnum, OperationTypes.TWO_WAY_SYNC);
		addEEnumLiteral(operationTypesEEnum, OperationTypes.TWO_WAY_ASYNC);
		addEEnumLiteral(operationTypesEEnum, OperationTypes.STREAM);

		initEEnum(protocolTypesEEnum, ProtocolTypes.class, "ProtocolTypes");
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.REST);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.SOAP);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.CO_AP);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.MQTT);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.AMQP);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.DDS);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.SEMI_SPACE);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.ZERO_MQ);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.WEB_SOCKETS);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.DPWS);
		addEEnumLiteral(protocolTypesEEnum, ProtocolTypes.HTTPS);
		

		initEEnum(simpleTypesEEnum, SimpleTypes.class, "SimpleTypes");
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.STRING);
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.INTEGER);
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.BOOLEAN);
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.DECIMAL);
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.DATE);
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.TIME);
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.DOUBLE);
		addEEnumLiteral(simpleTypesEEnum, SimpleTypes.FLOAT);

		initEEnum(contextTypesEEnum, ContextTypes.class, "ContextTypes");
		addEEnumLiteral(contextTypesEEnum, ContextTypes.BODY);
		addEEnumLiteral(contextTypesEEnum, ContextTypes.PATH);
		addEEnumLiteral(contextTypesEEnum, ContextTypes.QUERY);
		addEEnumLiteral(contextTypesEEnum, ContextTypes.FORM);
		addEEnumLiteral(contextTypesEEnum, ContextTypes.HEADER);

		initEEnum(occurrencesTypesEEnum, OccurrencesTypes.class, "OccurrencesTypes");
		addEEnumLiteral(occurrencesTypesEEnum, OccurrencesTypes.ZERO);
		addEEnumLiteral(occurrencesTypesEEnum, OccurrencesTypes.ONE);
		addEEnumLiteral(occurrencesTypesEEnum, OccurrencesTypes.UNBOUNDED);

		initEEnum(qosTypesEEnum, QosTypes.class, "QosTypes");
		addEEnumLiteral(qosTypesEEnum, QosTypes.UNRELIABLE);
		addEEnumLiteral(qosTypesEEnum, QosTypes.RELIABLE);

		initEEnum(mediaTypesEEnum, MediaTypes.class, "MediaTypes");
		addEEnumLiteral(mediaTypesEEnum, MediaTypes.JSON);
		addEEnumLiteral(mediaTypesEEnum, MediaTypes.XML);

		// Initialize data types
		initEDataType(roleTypesObjectEDataType, RoleTypes.class, "RoleTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(protocolTypesObjectEDataType, ProtocolTypes.class, "ProtocolTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(operationTypesObjectEDataType, OperationTypes.class, "OperationTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(simpleTypesObjectEDataType, SimpleTypes.class, "SimpleTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(contextTypesObjectEDataType, ContextTypes.class, "ContextTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(occurrencesTypesObjectEDataType, OccurrencesTypes.class, "OccurrencesTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(qosTypesObjectEDataType, QosTypes.class, "QosTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(mediaTypesObjectEDataType, MediaTypes.class, "MediaTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
		addAnnotation
		  (roleTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "RoleTypes"
		   });	
		addAnnotation
		  (protocolTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "ProtocolTypes"
		   });	
		addAnnotation
		  (operationTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "OperationTypes"
		   });	
		addAnnotation
		  (simpleTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "SimpleTypes"
		   });	
		addAnnotation
		  (contextTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "ContextTypes"
		   });	
		addAnnotation
		  (occurrencesTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "OccurrencesTypes"
		   });	
		addAnnotation
		  (qosTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "QosTypes"
		   });	
		addAnnotation
		  (mediaTypesObjectEDataType, 
		   source, 
		   new String[] {
			 "baseType", "MediaTypes"
		   });
	}

} //GidlPackageImpl

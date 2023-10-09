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

import org.zefxis.dexms.modelingnotations.gidl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->

 */
public class GidlFactoryImpl extends EFactoryImpl implements GidlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public static GidlFactory init() {
		try {
			GidlFactory theGidlFactory = (GidlFactory)EPackage.Registry.INSTANCE.getEFactory(GidlPackage.eNS_URI);
			if (theGidlFactory != null) {
				return theGidlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GidlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public GidlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case GidlPackage.GIDL_MODEL: return createGIDLModel();
			case GidlPackage.INTERFACE_DESCRIPTION: return createInterfaceDescription();
			case GidlPackage.OPERATION: return createOperation();
			case GidlPackage.SCOPE: return createScope();
			case GidlPackage.DATA: return createData();
			case GidlPackage.SIMPLE_TYPE: return createSimpleType();
			case GidlPackage.COMPLEX_TYPE: return createComplexType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case GidlPackage.ROLE_TYPES:
				return createRoleTypesFromString(eDataType, initialValue);
			case GidlPackage.OPERATION_TYPES:
				return createOperationTypesFromString(eDataType, initialValue);
			case GidlPackage.PROTOCOL_TYPES:
				return createProtocolTypesFromString(eDataType, initialValue);
			case GidlPackage.SIMPLE_TYPES:
				return createSimpleTypesFromString(eDataType, initialValue);
			case GidlPackage.CONTEXT_TYPES:
				return createContextTypesFromString(eDataType, initialValue);
			case GidlPackage.OCCURRENCES_TYPES:
				return createOccurrencesTypesFromString(eDataType, initialValue);
			case GidlPackage.QOS_TYPES:
				return createQosTypesFromString(eDataType, initialValue);
			case GidlPackage.MEDIA_TYPES:
				return createMediaTypesFromString(eDataType, initialValue);
			case GidlPackage.ROLE_TYPES_OBJECT:
				return createRoleTypesObjectFromString(eDataType, initialValue);
			case GidlPackage.PROTOCOL_TYPES_OBJECT:
				return createProtocolTypesObjectFromString(eDataType, initialValue);
			case GidlPackage.OPERATION_TYPES_OBJECT:
				return createOperationTypesObjectFromString(eDataType, initialValue);
			case GidlPackage.SIMPLE_TYPES_OBJECT:
				return createSimpleTypesObjectFromString(eDataType, initialValue);
			case GidlPackage.CONTEXT_TYPES_OBJECT:
				return createContextTypesObjectFromString(eDataType, initialValue);
			case GidlPackage.OCCURRENCES_TYPES_OBJECT:
				return createOccurrencesTypesObjectFromString(eDataType, initialValue);
			case GidlPackage.QOS_TYPES_OBJECT:
				return createQosTypesObjectFromString(eDataType, initialValue);
			case GidlPackage.MEDIA_TYPES_OBJECT:
				return createMediaTypesObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case GidlPackage.ROLE_TYPES:
				return convertRoleTypesToString(eDataType, instanceValue);
			case GidlPackage.OPERATION_TYPES:
				return convertOperationTypesToString(eDataType, instanceValue);
			case GidlPackage.PROTOCOL_TYPES:
				return convertProtocolTypesToString(eDataType, instanceValue);
			case GidlPackage.SIMPLE_TYPES:
				return convertSimpleTypesToString(eDataType, instanceValue);
			case GidlPackage.CONTEXT_TYPES:
				return convertContextTypesToString(eDataType, instanceValue);
			case GidlPackage.OCCURRENCES_TYPES:
				return convertOccurrencesTypesToString(eDataType, instanceValue);
			case GidlPackage.QOS_TYPES:
				return convertQosTypesToString(eDataType, instanceValue);
			case GidlPackage.MEDIA_TYPES:
				return convertMediaTypesToString(eDataType, instanceValue);
			case GidlPackage.ROLE_TYPES_OBJECT:
				return convertRoleTypesObjectToString(eDataType, instanceValue);
			case GidlPackage.PROTOCOL_TYPES_OBJECT:
				return convertProtocolTypesObjectToString(eDataType, instanceValue);
			case GidlPackage.OPERATION_TYPES_OBJECT:
				return convertOperationTypesObjectToString(eDataType, instanceValue);
			case GidlPackage.SIMPLE_TYPES_OBJECT:
				return convertSimpleTypesObjectToString(eDataType, instanceValue);
			case GidlPackage.CONTEXT_TYPES_OBJECT:
				return convertContextTypesObjectToString(eDataType, instanceValue);
			case GidlPackage.OCCURRENCES_TYPES_OBJECT:
				return convertOccurrencesTypesObjectToString(eDataType, instanceValue);
			case GidlPackage.QOS_TYPES_OBJECT:
				return convertQosTypesObjectToString(eDataType, instanceValue);
			case GidlPackage.MEDIA_TYPES_OBJECT:
				return convertMediaTypesObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public GIDLModel createGIDLModel() {
		GIDLModelImpl gidlModel = new GIDLModelImpl();
		return gidlModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public InterfaceDescription createInterfaceDescription() {
		InterfaceDescriptionImpl interfaceDescription = new InterfaceDescriptionImpl();
		return interfaceDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public Scope createScope() {
		ScopeImpl scope = new ScopeImpl();
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public Data createData() {
		DataImpl data = new DataImpl();
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public SimpleType createSimpleType() {
		SimpleTypeImpl simpleType = new SimpleTypeImpl();
		return simpleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public ComplexType createComplexType() {
		ComplexTypeImpl complexType = new ComplexTypeImpl();
		return complexType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public RoleTypes createRoleTypesFromString(EDataType eDataType, String initialValue) {
		RoleTypes result = RoleTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertRoleTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public OperationTypes createOperationTypesFromString(EDataType eDataType, String initialValue) {
		OperationTypes result = OperationTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertOperationTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->

	 */
	public ProtocolTypes createProtocolTypesFromString(EDataType eDataType, String initialValue) {
		ProtocolTypes result = ProtocolTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertProtocolTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public SimpleTypes createSimpleTypesFromString(EDataType eDataType, String initialValue) {
		SimpleTypes result = SimpleTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertSimpleTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public ContextTypes createContextTypesFromString(EDataType eDataType, String initialValue) {
		ContextTypes result = ContextTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertContextTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public OccurrencesTypes createOccurrencesTypesFromString(EDataType eDataType, String initialValue) {
		OccurrencesTypes result = OccurrencesTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertOccurrencesTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public QosTypes createQosTypesFromString(EDataType eDataType, String initialValue) {
		QosTypes result = QosTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertQosTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public MediaTypes createMediaTypesFromString(EDataType eDataType, String initialValue) {
		MediaTypes result = MediaTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertMediaTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public RoleTypes createRoleTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createRoleTypesFromString(GidlPackage.Literals.ROLE_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertRoleTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertRoleTypesToString(GidlPackage.Literals.ROLE_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public ProtocolTypes createProtocolTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createProtocolTypesFromString(GidlPackage.Literals.PROTOCOL_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertProtocolTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertProtocolTypesToString(GidlPackage.Literals.PROTOCOL_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public OperationTypes createOperationTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createOperationTypesFromString(GidlPackage.Literals.OPERATION_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertOperationTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertOperationTypesToString(GidlPackage.Literals.OPERATION_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public SimpleTypes createSimpleTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createSimpleTypesFromString(GidlPackage.Literals.SIMPLE_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertSimpleTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSimpleTypesToString(GidlPackage.Literals.SIMPLE_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public ContextTypes createContextTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createContextTypesFromString(GidlPackage.Literals.CONTEXT_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertContextTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertContextTypesToString(GidlPackage.Literals.CONTEXT_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public OccurrencesTypes createOccurrencesTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createOccurrencesTypesFromString(GidlPackage.Literals.OCCURRENCES_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertOccurrencesTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertOccurrencesTypesToString(GidlPackage.Literals.OCCURRENCES_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public QosTypes createQosTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createQosTypesFromString(GidlPackage.Literals.QOS_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertQosTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertQosTypesToString(GidlPackage.Literals.QOS_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public MediaTypes createMediaTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createMediaTypesFromString(GidlPackage.Literals.MEDIA_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String convertMediaTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertMediaTypesToString(GidlPackage.Literals.MEDIA_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public GidlPackage getGidlPackage() {
		return (GidlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Deprecated
	public static GidlPackage getPackage() {
		return GidlPackage.eINSTANCE;
	}

} //GidlFactoryImpl

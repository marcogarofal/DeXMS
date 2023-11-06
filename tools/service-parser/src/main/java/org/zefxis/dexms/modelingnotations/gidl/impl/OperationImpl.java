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

import org.zefxis.dexms.modelingnotations.gidl.Data;
import org.zefxis.dexms.modelingnotations.gidl.GidlPackage;
import org.zefxis.dexms.modelingnotations.gidl.Operation;
import org.zefxis.dexms.modelingnotations.gidl.OperationTypes;
import org.zefxis.dexms.modelingnotations.gidl.QosTypes;
import org.zefxis.dexms.modelingnotations.gidl.Scope;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl#getType <em>Type</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl#getHasScope <em>Has Scope</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl#getInputData <em>Input Data</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl#getOutputData <em>Output Data</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.OperationImpl#getQos <em>Qos</em>}</li>
 * </ul>
 *

 */
public class OperationImpl extends MinimalEObjectImpl.Container implements Operation {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final OperationTypes TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected OperationTypes type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHasScope() <em>Has Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected Scope hasScope;

	/**
	 * The cached value of the '{@link #getInputData() <em>Input Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected EList<Data> inputData;

	/**
	 * The cached value of the '{@link #getOutputData() <em>Output Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected EList<Data> outputData;

	/**
	 * The default value of the '{@link #getQos() <em>Qos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final QosTypes QOS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQos() <em>Qos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected QosTypes qos = QOS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	protected EClass eStaticClass() {
		return GidlPackage.Literals.OPERATION;
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.OPERATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public OperationTypes getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setType(OperationTypes newType) {
		OperationTypes oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.OPERATION__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public Scope getHasScope() {
		return hasScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public NotificationChain basicSetHasScope(Scope newHasScope, NotificationChain msgs) {
		Scope oldHasScope = hasScope;
		hasScope = newHasScope;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GidlPackage.OPERATION__HAS_SCOPE, oldHasScope, newHasScope);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setHasScope(Scope newHasScope) {
		if (newHasScope != hasScope) {
			NotificationChain msgs = null;
			if (hasScope != null)
				msgs = ((InternalEObject)hasScope).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GidlPackage.OPERATION__HAS_SCOPE, null, msgs);
			if (newHasScope != null)
				msgs = ((InternalEObject)newHasScope).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GidlPackage.OPERATION__HAS_SCOPE, null, msgs);
			msgs = basicSetHasScope(newHasScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.OPERATION__HAS_SCOPE, newHasScope, newHasScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EList<Data> getInputData() {
		if (inputData == null) {
			inputData = new EObjectContainmentEList<Data>(Data.class, this, GidlPackage.OPERATION__INPUT_DATA);
		}
		return inputData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EList<Data> getOutputData() {
		if (outputData == null) {
			outputData = new EObjectContainmentEList<Data>(Data.class, this, GidlPackage.OPERATION__OUTPUT_DATA);
		}
		return outputData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public QosTypes getQos() {
		return qos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setQos(QosTypes newQos) {
		QosTypes oldQos = qos;
		qos = newQos;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.OPERATION__QOS, oldQos, qos));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GidlPackage.OPERATION__HAS_SCOPE:
				return basicSetHasScope(null, msgs);
			case GidlPackage.OPERATION__INPUT_DATA:
				return ((InternalEList<?>)getInputData()).basicRemove(otherEnd, msgs);
			case GidlPackage.OPERATION__OUTPUT_DATA:
				return ((InternalEList<?>)getOutputData()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GidlPackage.OPERATION__NAME:
				return getName();
			case GidlPackage.OPERATION__TYPE:
				return getType();
			case GidlPackage.OPERATION__HAS_SCOPE:
				return getHasScope();
			case GidlPackage.OPERATION__INPUT_DATA:
				return getInputData();
			case GidlPackage.OPERATION__OUTPUT_DATA:
				return getOutputData();
			case GidlPackage.OPERATION__QOS:
				return getQos();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GidlPackage.OPERATION__NAME:
				setName((String)newValue);
				return;
			case GidlPackage.OPERATION__TYPE:
				setType((OperationTypes)newValue);
				return;
			case GidlPackage.OPERATION__HAS_SCOPE:
				setHasScope((Scope)newValue);
				return;
			case GidlPackage.OPERATION__INPUT_DATA:
				getInputData().clear();
				getInputData().addAll((Collection<? extends Data>)newValue);
				return;
			case GidlPackage.OPERATION__OUTPUT_DATA:
				getOutputData().clear();
				getOutputData().addAll((Collection<? extends Data>)newValue);
				return;
			case GidlPackage.OPERATION__QOS:
				setQos((QosTypes)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GidlPackage.OPERATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case GidlPackage.OPERATION__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case GidlPackage.OPERATION__HAS_SCOPE:
				setHasScope((Scope)null);
				return;
			case GidlPackage.OPERATION__INPUT_DATA:
				getInputData().clear();
				return;
			case GidlPackage.OPERATION__OUTPUT_DATA:
				getOutputData().clear();
				return;
			case GidlPackage.OPERATION__QOS:
				setQos(QOS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GidlPackage.OPERATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case GidlPackage.OPERATION__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case GidlPackage.OPERATION__HAS_SCOPE:
				return hasScope != null;
			case GidlPackage.OPERATION__INPUT_DATA:
				return inputData != null && !inputData.isEmpty();
			case GidlPackage.OPERATION__OUTPUT_DATA:
				return outputData != null && !outputData.isEmpty();
			case GidlPackage.OPERATION__QOS:
				return QOS_EDEFAULT == null ? qos != null : !QOS_EDEFAULT.equals(qos);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", qos: ");
		result.append(qos);
		result.append(')');
		return result.toString();
	}

} //OperationImpl

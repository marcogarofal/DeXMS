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

import org.zefxis.dexms.modelingnotations.gidl.GIDLModel;
import org.zefxis.dexms.modelingnotations.gidl.GidlPackage;
import org.zefxis.dexms.modelingnotations.gidl.InterfaceDescription;
import org.zefxis.dexms.modelingnotations.gidl.ProtocolTypes;

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
 * An implementation of the model object '<em><b>GIDL Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.GIDLModelImpl#getHostAddress <em>Host Address</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.GIDLModelImpl#getProtocol <em>Protocol</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.GIDLModelImpl#getHasInterfaces <em>Has Interfaces</em>}</li>
 * </ul>
 *

 */
public class GIDLModelImpl extends MinimalEObjectImpl.Container implements GIDLModel {
	/**
	 * The default value of the '{@link #getHostAddress() <em>Host Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHostAddress() <em>Host Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected String hostAddress = HOST_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getProtocol() <em>Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final ProtocolTypes PROTOCOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProtocol() <em>Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected ProtocolTypes protocol = PROTOCOL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHasInterfaces() <em>Has Interfaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected EList<InterfaceDescription> hasInterfaces;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected GIDLModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	protected EClass eStaticClass() {
		return GidlPackage.Literals.GIDL_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String getHostAddress() {
		return hostAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setHostAddress(String newHostAddress) {
		String oldHostAddress = hostAddress;
		hostAddress = newHostAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.GIDL_MODEL__HOST_ADDRESS, oldHostAddress, hostAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public ProtocolTypes getProtocol() {
		return protocol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setProtocol(ProtocolTypes newProtocol) {
		ProtocolTypes oldProtocol = protocol;
		protocol = newProtocol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.GIDL_MODEL__PROTOCOL, oldProtocol, protocol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EList<InterfaceDescription> getHasInterfaces() {
		if (hasInterfaces == null) {
			hasInterfaces = new EObjectContainmentEList<InterfaceDescription>(InterfaceDescription.class, this, GidlPackage.GIDL_MODEL__HAS_INTERFACES);
		}
		return hasInterfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GidlPackage.GIDL_MODEL__HAS_INTERFACES:
				return ((InternalEList<?>)getHasInterfaces()).basicRemove(otherEnd, msgs);
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
			case GidlPackage.GIDL_MODEL__HOST_ADDRESS:
				return getHostAddress();
			case GidlPackage.GIDL_MODEL__PROTOCOL:
				return getProtocol();
			case GidlPackage.GIDL_MODEL__HAS_INTERFACES:
				return getHasInterfaces();
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
			case GidlPackage.GIDL_MODEL__HOST_ADDRESS:
				setHostAddress((String)newValue);
				return;
			case GidlPackage.GIDL_MODEL__PROTOCOL:
				setProtocol((ProtocolTypes)newValue);
				return;
			case GidlPackage.GIDL_MODEL__HAS_INTERFACES:
				getHasInterfaces().clear();
				getHasInterfaces().addAll((Collection<? extends InterfaceDescription>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GidlPackage.GIDL_MODEL__HOST_ADDRESS:
				setHostAddress(HOST_ADDRESS_EDEFAULT);
				return;
			case GidlPackage.GIDL_MODEL__PROTOCOL:
				setProtocol(PROTOCOL_EDEFAULT);
				return;
			case GidlPackage.GIDL_MODEL__HAS_INTERFACES:
				getHasInterfaces().clear();
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
			case GidlPackage.GIDL_MODEL__HOST_ADDRESS:
				return HOST_ADDRESS_EDEFAULT == null ? hostAddress != null : !HOST_ADDRESS_EDEFAULT.equals(hostAddress);
			case GidlPackage.GIDL_MODEL__PROTOCOL:
				return PROTOCOL_EDEFAULT == null ? protocol != null : !PROTOCOL_EDEFAULT.equals(protocol);
			case GidlPackage.GIDL_MODEL__HAS_INTERFACES:
				return hasInterfaces != null && !hasInterfaces.isEmpty();
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
		result.append(" (hostAddress: ");
		result.append(hostAddress);
		result.append(", protocol: ");
		result.append(protocol);
		result.append(')');
		return result.toString();
	}

} //GIDLModelImpl

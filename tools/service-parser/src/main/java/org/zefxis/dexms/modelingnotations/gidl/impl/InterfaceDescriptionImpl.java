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

import org.zefxis.dexms.modelingnotations.gidl.GidlPackage;
import org.zefxis.dexms.modelingnotations.gidl.InterfaceDescription;
import org.zefxis.dexms.modelingnotations.gidl.Operation;
import org.zefxis.dexms.modelingnotations.gidl.RoleTypes;

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
 * An implementation of the model object '<em><b>Interface Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.InterfaceDescriptionImpl#getRole <em>Role</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.InterfaceDescriptionImpl#getHasOperations <em>Has Operations</em>}</li>
 * </ul>
 *

 */
public class InterfaceDescriptionImpl extends MinimalEObjectImpl.Container implements InterfaceDescription {
	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final RoleTypes ROLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected RoleTypes role = ROLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHasOperations() <em>Has Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected EList<Operation> hasOperations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected InterfaceDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	protected EClass eStaticClass() {
		return GidlPackage.Literals.INTERFACE_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public RoleTypes getRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setRole(RoleTypes newRole) {
		RoleTypes oldRole = role;
		role = newRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.INTERFACE_DESCRIPTION__ROLE, oldRole, role));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public EList<Operation> getHasOperations() {
		if (hasOperations == null) {
			hasOperations = new EObjectContainmentEList<Operation>(Operation.class, this, GidlPackage.INTERFACE_DESCRIPTION__HAS_OPERATIONS);
		}
		return hasOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GidlPackage.INTERFACE_DESCRIPTION__HAS_OPERATIONS:
				return ((InternalEList<?>)getHasOperations()).basicRemove(otherEnd, msgs);
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
			case GidlPackage.INTERFACE_DESCRIPTION__ROLE:
				return getRole();
			case GidlPackage.INTERFACE_DESCRIPTION__HAS_OPERATIONS:
				return getHasOperations();
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
			case GidlPackage.INTERFACE_DESCRIPTION__ROLE:
				setRole((RoleTypes)newValue);
				return;
			case GidlPackage.INTERFACE_DESCRIPTION__HAS_OPERATIONS:
				getHasOperations().clear();
				getHasOperations().addAll((Collection<? extends Operation>)newValue);
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
			case GidlPackage.INTERFACE_DESCRIPTION__ROLE:
				setRole(ROLE_EDEFAULT);
				return;
			case GidlPackage.INTERFACE_DESCRIPTION__HAS_OPERATIONS:
				getHasOperations().clear();
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
			case GidlPackage.INTERFACE_DESCRIPTION__ROLE:
				return ROLE_EDEFAULT == null ? role != null : !ROLE_EDEFAULT.equals(role);
			case GidlPackage.INTERFACE_DESCRIPTION__HAS_OPERATIONS:
				return hasOperations != null && !hasOperations.isEmpty();
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
		result.append(" (role: ");
		result.append(role);
		result.append(')');
		return result.toString();
	}

} //InterfaceDescriptionImpl

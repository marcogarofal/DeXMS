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
import org.zefxis.dexms.modelingnotations.gidl.OccurrencesTypes;
import org.zefxis.dexms.modelingnotations.gidl.SimpleType;
import org.zefxis.dexms.modelingnotations.gidl.SimpleTypes;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl#getMinOccurs <em>Min Occurs</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl#getMaxOccurs <em>Max Occurs</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.SimpleTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * 
 */
public class SimpleTypeImpl extends MinimalEObjectImpl.Container implements SimpleType {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * 
	 * 
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinOccurs() <em>Min Occurs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * 
	 * 
	 */
	protected static final OccurrencesTypes MIN_OCCURS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinOccurs() <em>Min Occurs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * 
	 * 
	 */
	protected OccurrencesTypes minOccurs = MIN_OCCURS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxOccurs() <em>Max Occurs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	protected static final OccurrencesTypes MAX_OCCURS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxOccurs() <em>Max Occurs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected OccurrencesTypes maxOccurs = MAX_OCCURS_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final SimpleTypes TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected SimpleTypes type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected SimpleTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	protected EClass eStaticClass() {
		return GidlPackage.Literals.SIMPLE_TYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.SIMPLE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public OccurrencesTypes getMinOccurs() {
		return minOccurs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setMinOccurs(OccurrencesTypes newMinOccurs) {
		OccurrencesTypes oldMinOccurs = minOccurs;
		minOccurs = newMinOccurs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.SIMPLE_TYPE__MIN_OCCURS, oldMinOccurs, minOccurs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public OccurrencesTypes getMaxOccurs() {
		return maxOccurs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setMaxOccurs(OccurrencesTypes newMaxOccurs) {
		OccurrencesTypes oldMaxOccurs = maxOccurs;
		maxOccurs = newMaxOccurs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.SIMPLE_TYPE__MAX_OCCURS, oldMaxOccurs, maxOccurs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public SimpleTypes getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setType(SimpleTypes newType) {
		SimpleTypes oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.SIMPLE_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GidlPackage.SIMPLE_TYPE__NAME:
				return getName();
			case GidlPackage.SIMPLE_TYPE__MIN_OCCURS:
				return getMinOccurs();
			case GidlPackage.SIMPLE_TYPE__MAX_OCCURS:
				return getMaxOccurs();
			case GidlPackage.SIMPLE_TYPE__TYPE:
				return getType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GidlPackage.SIMPLE_TYPE__NAME:
				setName((String)newValue);
				return;
			case GidlPackage.SIMPLE_TYPE__MIN_OCCURS:
				setMinOccurs((OccurrencesTypes)newValue);
				return;
			case GidlPackage.SIMPLE_TYPE__MAX_OCCURS:
				setMaxOccurs((OccurrencesTypes)newValue);
				return;
			case GidlPackage.SIMPLE_TYPE__TYPE:
				setType((SimpleTypes)newValue);
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
			case GidlPackage.SIMPLE_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case GidlPackage.SIMPLE_TYPE__MIN_OCCURS:
				setMinOccurs(MIN_OCCURS_EDEFAULT);
				return;
			case GidlPackage.SIMPLE_TYPE__MAX_OCCURS:
				setMaxOccurs(MAX_OCCURS_EDEFAULT);
				return;
			case GidlPackage.SIMPLE_TYPE__TYPE:
				setType(TYPE_EDEFAULT);
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
			case GidlPackage.SIMPLE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case GidlPackage.SIMPLE_TYPE__MIN_OCCURS:
				return MIN_OCCURS_EDEFAULT == null ? minOccurs != null : !MIN_OCCURS_EDEFAULT.equals(minOccurs);
			case GidlPackage.SIMPLE_TYPE__MAX_OCCURS:
				return MAX_OCCURS_EDEFAULT == null ? maxOccurs != null : !MAX_OCCURS_EDEFAULT.equals(maxOccurs);
			case GidlPackage.SIMPLE_TYPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
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
		result.append(", minOccurs: ");
		result.append(minOccurs);
		result.append(", maxOccurs: ");
		result.append(maxOccurs);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //SimpleTypeImpl

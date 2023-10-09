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
import org.zefxis.dexms.modelingnotations.gidl.Scope;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.ScopeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.ScopeImpl#getVerb <em>Verb</em>}</li>
 *   <li>{@link eu.chorevolution.modelingnotations.gidl.impl.ScopeImpl#getUri <em>Uri</em>}</li>
 * </ul>
 *

 */
public class ScopeImpl extends MinimalEObjectImpl.Container implements Scope {
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

	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVerb() <em>Verb</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final String VERB_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVerb() <em>Verb</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected String verb = VERB_EDEFAULT;

	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected String uri = URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	protected ScopeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	protected EClass eStaticClass() {
		return GidlPackage.Literals.SCOPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.SCOPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String getVerb() {
		return verb;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setVerb(String newVerb) {
		String oldVerb = verb;
		verb = newVerb;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.SCOPE__VERB, oldVerb, verb));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public String getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GidlPackage.SCOPE__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->

	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GidlPackage.SCOPE__NAME:
				return getName();
			case GidlPackage.SCOPE__VERB:
				return getVerb();
			case GidlPackage.SCOPE__URI:
				return getUri();
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
			case GidlPackage.SCOPE__NAME:
				setName((String)newValue);
				return;
			case GidlPackage.SCOPE__VERB:
				setVerb((String)newValue);
				return;
			case GidlPackage.SCOPE__URI:
				setUri((String)newValue);
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
			case GidlPackage.SCOPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case GidlPackage.SCOPE__VERB:
				setVerb(VERB_EDEFAULT);
				return;
			case GidlPackage.SCOPE__URI:
				setUri(URI_EDEFAULT);
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
			case GidlPackage.SCOPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case GidlPackage.SCOPE__VERB:
				return VERB_EDEFAULT == null ? verb != null : !VERB_EDEFAULT.equals(verb);
			case GidlPackage.SCOPE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
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
		result.append(", verb: ");
		result.append(verb);
		result.append(", uri: ");
		result.append(uri);
		result.append(')');
		return result.toString();
	}

} //ScopeImpl

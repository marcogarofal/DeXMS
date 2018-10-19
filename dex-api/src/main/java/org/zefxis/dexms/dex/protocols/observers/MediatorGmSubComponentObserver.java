package org.zefxis.dexms.dex.protocols.observers;

import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;

// https://www.journaldev.com/1739/observer-design-pattern-in-java

public abstract class MediatorGmSubComponentObserver {
	
	
	/**
	 * Method use to publish notification event on BcGmSubcomponent 
	 * 
	 */
	
	public abstract void update(String action);
	
	/**
	 * Method use to set new BcGmSubcomponent object
	 * 
	 * @param bcGmSubcomponent
	 */
	public abstract void setGmSubComponent(MediatorGmSubcomponent bcGmSubcomponent);
	
}

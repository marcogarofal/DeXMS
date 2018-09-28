package eu.chorevolution.vsb.gm.protocols.observers;

import eu.chorevolution.vsb.gm.protocols.primitives.BcGmSubcomponent;

// https://www.journaldev.com/1739/observer-design-pattern-in-java

public abstract class BcGmSubComponentObserver {
	
	
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
	public abstract void setGmSubComponent(BcGmSubcomponent bcGmSubcomponent);
	
}

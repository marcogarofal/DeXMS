package org.zefxis.dexms.dex.protocols.observers;

import org.zefxis.dexms.dex.protocols.primitives.MediatorGmSubcomponent;
import org.zefxis.dexms.gmdl.utils.Constants;

import fr.inria.mimove.monitor.agent.Agent;

public class MediatorSubComponentObserver extends MediatorGmSubComponentObserver {

	private Agent agent = null;
	private MediatorGmSubcomponent bcGmSubcomponent = null;

	public MediatorSubComponentObserver(String name) {

		agent = new Agent(name, Constants.getObserverHostIp(), Constants.getObserverPort(name));
	}

	@Override
	public void update(String action) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setGmSubComponent(MediatorGmSubcomponent bcGmSubcomponent) {
		// TODO Auto-generated method stub
		this.bcGmSubcomponent = bcGmSubcomponent;
	}

}

package eu.chorevolution.vsb.gm.protocols.observers;

import eu.chorevolution.vsb.gm.protocols.primitives.BcGmSubcomponent;
import eu.chorevolution.vsb.gmdl.utils.Constants;
import fr.inria.mimove.monitor.agent.Agent;

public class BcSubComponentObserver extends BcGmSubComponentObserver {

	private Agent agent = null;
	private BcGmSubcomponent bcGmSubcomponent = null;

	public BcSubComponentObserver(String name) {

		agent = new Agent(name, Constants.getObserverHostIp(), Constants.getObserverPort(name));
	}

	@Override
	public void update(String action) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setGmSubComponent(BcGmSubcomponent bcGmSubcomponent) {
		// TODO Auto-generated method stub
		this.bcGmSubcomponent = bcGmSubcomponent;
	}

}

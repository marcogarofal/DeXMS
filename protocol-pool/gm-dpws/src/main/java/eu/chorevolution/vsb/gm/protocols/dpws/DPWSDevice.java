package eu.chorevolution.vsb.gm.protocols.dpws;

import org.ws4d.java.communication.DPWSCommunicationManager;
import org.ws4d.java.service.DefaultDevice;
import org.ws4d.java.types.LocalizedString;
import org.ws4d.java.types.QName;
import org.ws4d.java.types.QNameSet;

public class DPWSDevice extends DefaultDevice {

	public final static String	DOCU_NAMESPACE	= "http://ws4d.org/jmeds";

	/**
	 * Constructor of our device.
	 */
	public DPWSDevice() {
		super(DPWSCommunicationManager.COMMUNICATION_MANAGER_ID);

		/*
		 * The following lines add metadata information to the device to
		 * illustrate how it works. As default values are defined for all of the
		 * fields, you CAN set new values here but you do NOT have to.
		 */

		// set PortType
		System.err.println("mango 1");
		this.setPortTypes(new QNameSet(new QName("DPWSDevice", DOCU_NAMESPACE)));
		// add device name (name is language specific)
		this.addFriendlyName("en-US", "DPWSDevice");
		this.addFriendlyName(LocalizedString.LANGUAGE_DE, "DokuGeraet DPWSDevice");

		// add device manufacturer (manufacturer is language specific)
		this.addManufacturer(LocalizedString.LANGUAGE_EN, "Test Inc.");
		this.addManufacturer("de-DE", "Test GmbH");

		this.addModelName(LocalizedString.LANGUAGE_EN, "DPWSDevice Model");
		System.err.println("mango 2");
	}
}

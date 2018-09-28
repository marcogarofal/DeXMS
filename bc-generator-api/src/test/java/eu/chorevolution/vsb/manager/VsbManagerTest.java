package eu.chorevolution.vsb.manager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import eu.chorevolution.vsb.bc.manager.VsbOutput;
import eu.chorevolution.vsb.gmdl.utils.Constants;
import eu.chorevolution.vsb.gmdl.utils.enums.ProtocolType;
import eu.chorevolution.vsb.manager.api.VsbManager;

//import eu.chorevolution.vsb.bindingcomponent.generated.GeneratedFactory;

public class VsbManagerTest{
	
	
	@Test
	public void testGenerateWar(){
		
		String interfaceDescriptionPath = "src/test/resources/gidl/gidl_1.gidl";
		VsbManager vsbm = new VsbManager();
		VsbOutput vsbOutput = vsbm.generateWar(interfaceDescriptionPath,ProtocolType.SOAP, "gidl");
	    
		assertTrue(Integer.valueOf(vsbOutput.service_bc_port) >= Constants.service_bc_port_min_range && Integer.valueOf(vsbOutput.service_bc_port) <= Constants.service_bc_port_max_range);
		assertTrue(Integer.valueOf(vsbOutput.bc_manager_servlet_port) >= Constants.bc_manager_servlet_port_min_range && Integer.valueOf(vsbOutput.bc_manager_servlet_port) <= Constants.bc_manager_servlet_port_max_range);
		assertTrue(Integer.valueOf(vsbOutput.setinvaddr_service_port) >= Constants.setinvaddr_service_port_min_range && Integer.valueOf(vsbOutput.setinvaddr_service_port) <= Constants.setinvaddr_service_port_max_range);
		assertTrue(Integer.valueOf(vsbOutput.service_port) >= Constants.service_port_min_range && Integer.valueOf(vsbOutput.service_port) <= Constants.service_port_max_range);
		assertTrue(vsbOutput.war instanceof byte[]);
		assertTrue(vsbOutput.wsdl instanceof byte[]);
		
		
		interfaceDescriptionPath = "src/test/resources/gidl/gidl_2.gidl";
		vsbm = new VsbManager();
		vsbOutput = vsbm.generateWar(interfaceDescriptionPath,ProtocolType.REST, "gidl");
		assertTrue(vsbOutput.jar instanceof byte[]);
		assertFalse(ArrayUtils.isEmpty(vsbOutput.jar));
		
	}
	
}


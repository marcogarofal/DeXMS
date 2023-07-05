package org.zefxis.dexms.dex.protocols.rest;

import org.zefxis.dexms.dex.protocols.generators.MediatorSubcomponentGenerator;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;

public class MediatorRestGenerator extends MediatorSubcomponentGenerator {

  public MediatorRestGenerator(GmServiceRepresentation componentDescription, MediatorConfiguration bcConfiguration) {
    super(componentDescription, bcConfiguration);
  }

  @Override
  public void generateEndpoint() {
    // TODO Auto-generated method stub
  }

  @Override
  protected void generatePojo(Data<?> definition) {
    // TODO Auto-generated method stub 
  }

@Override
protected void generateRootClass() {
	// TODO Auto-generated method stub
	
}
}
package org.zefxis.dexms.dex.protocols.https;

import org.zefxis.dexms.dex.protocols.generators.MediatorSubcomponentGenerator;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;

public class MediatorHttpsGenerator extends MediatorSubcomponentGenerator {

  public MediatorHttpsGenerator(GmServiceRepresentation componentDescription, MediatorConfiguration bcConfiguration) {
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


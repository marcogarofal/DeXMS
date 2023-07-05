package org.zefxis.dexms.dex.protocols.generators;

import java.util.Collection;

import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.Operation;



public abstract class MediatorSubcomponentGenerator {
  protected final MediatorConfiguration bcConfiguration;
  protected final GmServiceRepresentation componentDescription;
  protected boolean debug = false;
  
  public MediatorSubcomponentGenerator(GmServiceRepresentation componentDescription, MediatorConfiguration bcConfiguration) {
    this.bcConfiguration = bcConfiguration;
    this.componentDescription = componentDescription;
  }
  
  public void generatePOJOAndEndpoint() {
	  
    this.generatePojos();
    this.generateEndpoint();
    
    Collection<Operation> operations = this.componentDescription.getOperations();
	for (Operation operation : operations) {
		
		if(operation.getPostDatas().size() > 1){
			
			this.generateRootClass();
		}
	}
  }
  
  protected void generatePojos() {
	  
	  
    Collection<Data<?>> definitions = this.componentDescription.getTypeDefinitions();
    
    for (Data<?> definition : definitions) {
      this.generatePojo(definition);
    }
  }
  
  public MediatorSubcomponentGenerator setDebug(boolean debug) {
    this.debug = debug;
    return this;
  }
  
  protected abstract void generatePojo(Data<?> definition);
  protected abstract void generateEndpoint();
  protected abstract void generateRootClass();
}
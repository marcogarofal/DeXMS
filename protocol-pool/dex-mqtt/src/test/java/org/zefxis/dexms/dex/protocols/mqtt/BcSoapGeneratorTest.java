package org.zefxis.dexms.dex.protocols.mqtt;

import org.junit.Before;
import org.junit.Test;
import org.zefxis.dexms.dex.protocols.mqtt.MediatorMQTTGenerator;
import org.zefxis.dexms.gmdl.utils.MediatorConfiguration;
import org.zefxis.dexms.gmdl.utils.GmServiceRepresentation;
import org.zefxis.dexms.gmdl.utils.Operation;
import org.zefxis.dexms.gmdl.utils.Scope;
import org.zefxis.dexms.dex.protocols.generators.MediatorSubcomponentGenerator;
import org.zefxis.dexms.gmdl.utils.Data;
import org.zefxis.dexms.gmdl.utils.Data.Context;
import org.zefxis.dexms.gmdl.utils.Data.MediaType;
import org.zefxis.dexms.gmdl.utils.enums.OperationType;
import org.zefxis.dexms.gmdl.utils.enums.ProtocolType;
import org.zefxis.dexms.gmdl.utils.enums.QosType;
import org.zefxis.dexms.gmdl.utils.enums.RoleType;
import org.zefxis.dexms.gmdl.utils.enums.Verb;


public class BcSoapGeneratorTest {
  
  private MediatorSubcomponentGenerator soapGenerator;
  
  @Before
  public void initGenerator() {
	  MediatorConfiguration compConfServer = new MediatorConfiguration();
    compConfServer.setSubcomponentRole(RoleType.SERVER);
    compConfServer.setServiceAddress("http://127.0.0.1:8282");
    compConfServer.setGeneratedCodePath("src/test/resources/generated/traffic");
    compConfServer.setTargetNamespace("");
    
    GmServiceRepresentation serviceDefinition = new GmServiceRepresentation();
    serviceDefinition.setProtocol(ProtocolType.REST);
    
    /*Types Definitions*/
    Data<?> light = new Data<>("light", "TrafficLight", false, MediaType.JSON, Context.BODY, true);
    Data<?> id = new Data<>("id", "Integer", true, MediaType.JSON, Context.PATH, true);
    Data<?> status = new Data<>("status", "String", true, MediaType.JSON, Context.PATH, true);
    Data<?> address = new Data<>("address", "String", true, MediaType.JSON, Context.PATH, true);
    light.addAttribute(id);
    light.addAttribute(status);
    light.addAttribute(address);
    serviceDefinition.addTypeDefinition(light);
    
    /*ONEWAY OP*/    
    Scope scope1 = new Scope();
    scope1.setName("postTrafficLight");
    scope1.setVerb(Verb.POST);
    scope1.setUri("/traffic-lights");
    Operation oneWayOperation = new Operation("operation_1", OperationType.ONE_WAY, QosType.RELIABLE, scope1);
    Data<?> getData1 = light;
    oneWayOperation.addGetData(getData1);
    serviceDefinition.addOperation(oneWayOperation);
    
    /*TWOWAY OP*/
    Scope scope2 = new Scope();
    scope2.setName("getTrafficLight");
    scope2.setVerb(Verb.GET);
    scope2.setUri("/traffic-lights/{id}");
    Operation twoWayOperation = new Operation("operation_2", OperationType.TWO_WAY_SYNC, QosType.RELIABLE, scope2);
    Data<?> getData2 = id;
    twoWayOperation.addGetData(getData2);
    Data<?> postData = light;
    twoWayOperation.setPostData(postData);
    serviceDefinition.addOperation(twoWayOperation);
    
    this.soapGenerator = new MediatorMQTTGenerator(serviceDefinition, compConfServer).setDebug(true);
  }
  
  @Test
  public void testEndpointGeneration() {
    this.soapGenerator.generatePOJOAndEndpoint();
    // DifferentiaAssert.assertSourcesEqual("src/test/resources/expected/BindingComponent.java", "src/test/resources/generated/traffic/BindingComponent.java");
  }
  
  @Test
  public void testPojoGeneration() {
    this.soapGenerator.generatePOJOAndEndpoint();
    // DifferentiaAssert.assertSourcesEqual("src/test/resources/expected/TrafficLight.java", "src/test/resources/generated/traffic/TrafficLight.java");
  }
}
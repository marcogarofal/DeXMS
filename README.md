**1.  DeXMS**

Data eXchange Mediator Synthesizer  (DeXMS) is a framework dedicated to complex distributed applications of the future Internet.

The goal of DeXMS is to allow developper to  perfectly interconnect services and things within choreography that use heterogeneous interaction protocols at the middleware layer, such as SOAP Web Services, REST Web Services, or things using CoAP, MQTT. So far the DeXMS supports following protocols : 

*  SOAP
*  REST
*  CoAP
*  MQTT
*  Websocket
*  DPWS


**2. Requirements**

DeXMS developped in Java 8 it is sucessfully tested on Linux based operating system.

**3.  Inteconnecting services and things**

To interconnect services and things, developer has to generate a middleware artifact that we call Mediator that ensures this interconnection :


*  Create a maven based project

*  Creating the  Data eXchange Interface Description Language (DeX-IDL) of the service or thing to interconnect 
 
Relying on the above figure, we have to create the DeX-IDL of the Temperature ressource. By using the [gidl eclipse plugin](https://gitlab.inria.fr/zefxis/DeX-IDL) or the [IoT Web Console](https://gitlab.inria.fr/pntumba/vsb-web-console/wikis/home).

*  Updating the pom.xml 

```
Add repository :

<repository>
  <id>snapshots</id>
  <name>http://maven.inria.fr-snapshots</name>
  <url>http://maven.inria.fr/artifactory/zefxis-public-snapshot</url>
</repository>

Add dependency :

<dependency>
   <groupId>org.zefxis.dexms</groupId>
	<artifactId>mediator-generator</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
	
*  Generate Mediator as follow :

```
MediatorGenerator mediator = new MediatorGenerator();
mediator.setServiceEndpoint("HOST-IP_TEMPERATURE_SENSOR", "HOST-PORT_TEMPERATURE_SENSOR");
mediator.setBusEndpoint("HOST-IP_CLIENT","HOST-PORT_CLIENT");
mediator.generateWar(PATH_TO_GIDL, ProtocolType.REST, "REST_to_COAP");
```
After executing this lines of codes, the mediator will be generated into the temporary folder of the system, and  has the following name pattern : GeneratorFolderXXXXXXXXXX
You can find that folder depending the operating system used : 
- for  windows based operating system check in C:\users\username\AppData\Local\Temp
- for linux based operating system check in /tmp/
- 




**4.  DeX-IDL examples**

<pre>

&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;gidl:GIDLModel xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:gidl="http://eu.chorevolution/modelingnotations/gidl" hostAddress="http://jinx.viktoria.chalmers.se:3000/" protocol="REST"&gt;
  &lt;hasInterfaces role="provider"&gt;
    &lt;hasOperations name="waypointWeatherInformation" type="two_way_sync" qos="reliable"&gt;
      &lt;hasScope name="waypointWeatherInformation" verb="POST" uri="waypointWeatherInformation"/&gt;
      &lt;inputData name="request" context="body" media="json"&gt;
        &lt;hasDataType xsi:type="gidl:ComplexType" name="segmentInformationRequest" minOccurs="one" maxOccurs="one"&gt;
			&lt;hasDataType xsi:type="gidl:ComplexType" name="waypoint0" minOccurs="one" maxOccurs="one"&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lat" minOccurs="one" maxOccurs="one" type="string"/&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lon" minOccurs="one" maxOccurs="one" type="string"/&gt;
			&lt;/hasDataType&gt;
		    &lt;hasDataType xsi:type="gidl:ComplexType" name="waypoint1" minOccurs="one" maxOccurs="one"&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lat" minOccurs="one" maxOccurs="one" type="string"/&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lon" minOccurs="one" maxOccurs="one" type="string"/&gt;
			&lt;/hasDataType&gt;
        &lt;/hasDataType&gt;
      &lt;/inputData&gt;
      &lt;outputData name="response" context="body" media="json"&gt;
		&lt;hasDataType xsi:type="gidl:ComplexType" name="segmentWeatherInformationResponse" minOccurs="one" maxOccurs="one"&gt;
			&lt;hasDataType xsi:type="gidl:ComplexType" name="waypoint0" minOccurs="one" maxOccurs="one"&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lat" minOccurs="one" maxOccurs="one" type="string"/&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lon" minOccurs="one" maxOccurs="one" type="string"/&gt;
			&lt;/hasDataType&gt;
			&lt;hasDataType xsi:type="gidl:ComplexType" name="waypoint1" minOccurs="one" maxOccurs="one"&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lat" minOccurs="one" maxOccurs="one" type="string"/&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="lon" minOccurs="one" maxOccurs="one" type="string"/&gt;
			&lt;/hasDataType&gt;
			&lt;hasDataType xsi:type="gidl:ComplexType" name="weatherInfo" minOccurs="one" maxOccurs="one"&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="roadTemperature" minOccurs="one" maxOccurs="one" type="string"/&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="airTemperature" minOccurs="one" maxOccurs="one" type="string"/&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="airRelativeHumidity" minOccurs="one" maxOccurs="one" type="string"/&gt;
				&lt;hasDataType xsi:type="gidl:SimpleType" name="windForce" minOccurs="one" maxOccurs="one" type="string"/&gt;
			&lt;/hasDataType&gt;
			&lt;hasDataType xsi:type="gidl:SimpleType" name="type" minOccurs="one" maxOccurs="one" type="string"/&gt;
		&lt;/hasDataType&gt;
      &lt;/outputData&gt;    
	&lt;/hasOperations&gt;
  &lt;/hasInterfaces&gt;
&lt;/gidl:GIDLModel&gt;

</pre>

**5.  Who do I talk to?**

Patient NTUMBA: patient.ntumba AT inria.fr 

Georgios Bouloukakis: Georgios.Bouloukakis AT inria.fr 

Nikolaos Georgantas: nikolaos.georgantas AT inria.fr 











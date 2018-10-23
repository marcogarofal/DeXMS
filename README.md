**1.  DeXMS**

Data eXchange Mediator Synthesizer  (DeXMS) is a development and execution environment dedicated to complex distributed applications of the future Internet.

These applications are open and dynamic choreographies of extremely heterogeneous objects and services, including lightweight integrated systems (such as sensors, actuators and networks of the latter), mobile systems (such as smartphone applications), and systems resource-rich computing (such as systems hosted on corporate servers and cloud infrastructures).

The goal of VSB is to allow developper to  perfectly interconnect services and things within choreography that use heterogeneous interaction protocols at the middleware layer, such as SOAP Web Services, REST Web Services, or things using CoAP, MQTT. So far the VSB supports following protocols : 

*  SOAP
*  REST
*  CoAP
*  MQTT
*  Websocket
*  DPWS

In addition,  the BCgenerator provides a monitoring API for this interconnected services and things.

**2. Requirements**

BCgenerator ensures interoperability of services and things developped in Java. 

**3.  Inteconnecting services and things**

To interconnect services and things, developer has to generate a middleware artifact that we call Mediator that ensures this interconnection :

![Mediator](https://gitlab.inria.fr/pntumba/vsb-web-console/raw/master/vsbwebconsole/WebContent/css/images/BC.png)

For instance based on the above figure, to generate the BC we need to follow this steps :

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
    <artifactId>MediatorGeneratorTest-api</artifactId>
    <version>2.1.2</version>
</dependency>
```
	
*  Generate Mediator as follow :

```
VsbManager vsbm = new VsbManager();
vsbm.setServiceEndpoint("HOST-IP_TEMPERATURE_SENSOR", "HOST-PORT_TEMPERATURE_SENSOR");
vsbm.setBusEndpoint("HOST-IP_CLIENT","HOST-PORT_CLIENT");
vsbm.generateWar(PATH_TO_GIDL, ProtocolType.REST, "REST_to_COAP");
```
After executing this lines of codes, the BC will be generated into the temporary folder of the system.




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

Patient NTUMBA: patient.ntumba AT inria.fr (developer/architect)

Georgios Bouloukakis: Georgios.Bouloukakis AT inria.fr (designer/architect)

Nikolaos Georgantas: nikolaos.georgantas AT inria.fr (designer)











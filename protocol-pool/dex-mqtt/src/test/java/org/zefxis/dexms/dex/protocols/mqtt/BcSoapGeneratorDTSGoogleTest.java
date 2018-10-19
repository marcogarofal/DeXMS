/**
 * 
 */
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


/**
 * @author Georgios Bouloukakis (boulouk@gmail.com)
 *
 * BcSoapGeneratorDTSGoogleTest.java Created: 5 févr. 2016
 * Description:
 */
public class BcSoapGeneratorDTSGoogleTest {
	
	private MediatorSubcomponentGenerator soapGenerator;
	
	@Before
	public void initGenerator() {
		MediatorConfiguration compConfServer = new MediatorConfiguration();
		compConfServer.setSubcomponentRole(RoleType.SERVER);
		compConfServer.setServiceAddress("https://maps.googleapis.com");
		compConfServer.setGeneratedCodePath("src/test/resources/generated/dtsgoogle");
		compConfServer.setTargetNamespace("");

		GmServiceRepresentation serviceDefinition = new GmServiceRepresentation();
		serviceDefinition.setProtocol(ProtocolType.REST);

		/* Types Definitions */
		Data<?> rootClass = new Data<>("rootClass", "RootClass", false, MediaType.JSON, Context.BODY, true);

		Data<?> geocoded_waypoints = new Data<>("geocoded_waypoints", "GeocodedWayPoints", false, MediaType.JSON, Context.PATH, true);
		Data<?> routes = new Data<>("routes", "Routes", false, MediaType.JSON, Context.PATH, true);
		Data<?> status = new Data<>("status", "String", true, MediaType.JSON, Context.PATH, true);

		rootClass.addAttribute(geocoded_waypoints);
		rootClass.addAttribute(routes);
		rootClass.addAttribute(status);
		
		Data<?> geocoder_status = new Data<>("geocoder_status", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> place_id = new Data<>("place_id", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> types = new Data<>("types", "String", true, MediaType.JSON, Context.PATH, true);
		
		geocoded_waypoints.addAttribute(geocoder_status);
		geocoded_waypoints.addAttribute(place_id);
		geocoded_waypoints.addAttribute(types);
		
		Data<?> bounds = new Data<>("bounds", "Bounds", false, MediaType.JSON, Context.PATH, true);
		Data<?> copyrights = new Data<>("copyrights", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> legs = new Data<>("legs", "Legs", false, MediaType.JSON, Context.PATH, true);
		Data<?> overview_polyline = new Data<>("overview_polyline", "OverviewPolyline", false, MediaType.JSON, Context.PATH, true);
		Data<?> summary = new Data<>("summary", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> warnings = new Data<>("warnings", "Warnings", false, MediaType.JSON, Context.PATH, true);
		Data<?> waypoint_order = new Data<>("waypoint_order", "WaypointOrder", false, MediaType.JSON, Context.PATH, true);
		
		routes.addAttribute(bounds);
		routes.addAttribute(copyrights);
		routes.addAttribute(legs);
		routes.addAttribute(overview_polyline);
		routes.addAttribute(summary);
		routes.addAttribute(warnings);
		routes.addAttribute(waypoint_order);
		
		Data<?> northeast = new Data<>("northeast", "Northeast", false, MediaType.JSON, Context.PATH, true);
		Data<?> southwest = new Data<>("southwest", "Southwest", false, MediaType.JSON, Context.PATH, true);
		
		bounds.addAttribute(northeast);
		bounds.addAttribute(southwest);
		
		Data<?> lat = new Data<>("lat", "Double", true, MediaType.JSON, Context.PATH, true);
		Data<?> lng = new Data<>("lng", "Double", true, MediaType.JSON, Context.PATH, true);
		
		northeast.addAttribute(lat);
		northeast.addAttribute(lng);
		
		southwest.addAttribute(lat);
		southwest.addAttribute(lng);
		
		Data<?> distance = new Data<>("distance", "Distance", false, MediaType.JSON, Context.PATH, true);
		Data<?> duration = new Data<>("duration", "Duration", false, MediaType.JSON, Context.PATH, true);
		Data<?> end_address = new Data<>("end_address", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> end_location = new Data<>("end_location", "EndLocation", false, MediaType.JSON, Context.PATH, true);
		Data<?> start_address = new Data<>("start_address", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> start_location = new Data<>("start_location", "StartLocation", false, MediaType.JSON, Context.PATH, true);
		Data<?> steps = new Data<>("steps", "Steps", false, MediaType.JSON, Context.PATH, true);
		Data<?> via_waypoint = new Data<>("via_waypoint", "ViaWaypoint", false, MediaType.JSON, Context.PATH, true);
		
		legs.addAttribute(distance);
		legs.addAttribute(duration);
		legs.addAttribute(end_address);
		legs.addAttribute(end_location);
		legs.addAttribute(start_address);
		legs.addAttribute(start_location);
		legs.addAttribute(steps);
		legs.addAttribute(via_waypoint);
		
		Data<?> text = new Data<>("text", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> value = new Data<>("value", "Double", true, MediaType.JSON, Context.PATH, true);
		
		distance.addAttribute(text);
		distance.addAttribute(value);
		
		duration.addAttribute(text);
		duration.addAttribute(value);
		
		end_location.addAttribute(lat);
		end_location.addAttribute(lng);
		
		start_location.addAttribute(lat);
		start_location.addAttribute(lng);
		
		Data<?> maneuver = new Data<>("maneuver", "String", true, MediaType.JSON, Context.PATH, false);
		Data<?> html_instructions = new Data<>("html_instructions", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> polyline = new Data<>("polyline", "Polyline", false, MediaType.JSON, Context.PATH, true);
		Data<?> travel_mode = new Data<>("travel_mode", "String", true, MediaType.JSON, Context.PATH, true);
		
		steps.addAttribute(distance);
		steps.addAttribute(duration);
		steps.addAttribute(end_location);
		steps.addAttribute(maneuver);
		steps.addAttribute(html_instructions);
		steps.addAttribute(polyline);
		steps.addAttribute(start_location);
		steps.addAttribute(travel_mode);
		
		Data<?> points = new Data<>("points", "String", true, MediaType.JSON, Context.PATH, true);
		polyline.addAttribute(points);
		
		overview_polyline.addAttribute(points);
		
		serviceDefinition.addTypeDefinition(rootClass);

		
		
		// Data for the requests
		Data<?> origin = new Data<>("origin", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> destination = new Data<>("destination", "String", true, MediaType.JSON, Context.PATH, true);
		Data<?> key = new Data<>("key", "String", true, MediaType.JSON, Context.PATH, true);

		/* -- TWOWAY OPERATION 1 -- */

		Scope scope1 = new Scope();
		scope1.setName("routeRequest");
		scope1.setVerb(Verb.GET);
//		scope1.setUri("/mes/get_metadata_in_area?collection=weather&{period}");
		scope1.setUri("/maps/api/directions/json?origin={origin}&destination={destination}&key={key}");

		Operation twoWayOperation1 = new Operation("operation_1", OperationType.TWO_WAY_SYNC, QosType.RELIABLE, scope1);
		twoWayOperation1.addGetData(origin);
		twoWayOperation1.addGetData(destination);
		twoWayOperation1.addGetData(key);
		twoWayOperation1.setPostData(rootClass); // Changed setPostData to addPostData

		serviceDefinition.addOperation(twoWayOperation1);

		/* ------------------------ */

		this.soapGenerator = new MediatorMQTTGenerator(serviceDefinition, compConfServer).setDebug(true);
	}

	@Test
	public void testEndpointGeneration() {
		this.soapGenerator.generatePOJOAndEndpoint();
		// DifferentiaAssert.assertSourcesEqual("src/test/resources/expected/BindingComponent.java",
		// "src/test/resources/generated/BindingComponent.java");
	}
	//
	// @Test
	// public void testPojoGeneration() {
	// this.soapGenerator.generateBc();
	// DifferentiaAssert.assertSourcesEqual("src/test/resources/expected/TrafficLight.java",
	// "src/test/resources/generated/TrafficLight.java");
	// }
	
	

}

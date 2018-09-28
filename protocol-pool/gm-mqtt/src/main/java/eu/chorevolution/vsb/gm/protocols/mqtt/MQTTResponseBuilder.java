package eu.chorevolution.vsb.gm.protocols.mqtt;

import com.fasterxml.jackson.databind.type.CollectionType;

import eu.chorevolution.vsb.gm.protocols.builders.ResponseBuilder;

public class MQTTResponseBuilder extends ResponseBuilder {

	@Override
	public <T> T unmarshalObject(String mediaType, String serializedObject, Class<T> responseClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unmarshalObject(String mediaType, String serializedObject, CollectionType collectionType) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

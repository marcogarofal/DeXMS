package org.zefxis.dexms.dex.protocols.mqtt;

import org.zefxis.dexms.dex.protocols.builders.ResponseBuilder;
import com.fasterxml.jackson.databind.type.CollectionType;



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
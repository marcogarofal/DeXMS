package org.zefxis.dexms.dex.protocols.dpws;

import org.zefxis.dexms.dex.protocols.builders.ResponseBuilder;
import com.fasterxml.jackson.databind.type.CollectionType;


public class DPWSResponseBuilder extends ResponseBuilder {

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

package eu.chorevolution.vsb.gm.protocols.soap;

import com.fasterxml.jackson.databind.type.CollectionType;

import eu.chorevolution.vsb.gm.protocols.builders.ResponseBuilder;

public class SoapResponseBuilder extends ResponseBuilder{

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

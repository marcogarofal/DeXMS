package org.zefxis.dexms.dex.protocols.builders;


import com.fasterxml.jackson.databind.type.CollectionType;

public abstract class ResponseBuilder {
	
	public abstract <T> T unmarshalObject(final String mediaType,
			final String serializedObject, final Class<T> responseClass) ;
	
	public abstract  <T> T unmarshalObject(final String mediaType,
			final String serializedObject, final CollectionType collectionType);
		
}

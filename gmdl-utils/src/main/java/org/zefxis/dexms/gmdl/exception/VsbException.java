package org.zefxis.dexms.gmdl.exception;

public class VsbException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7038217167826006544L;
	
	public VsbException(){}
	
	/** 
	* Create new instance of VsbException 
	* @param message The message to provide more details on the exception 
	*/ 
	public VsbException(String message){
		
		super(message);
		
	}
	
	/** 
	* Create new instance of VsbException 
	* @param message The message to more details on the exception 
	* @param cause The origin of the exception 
	*/ 
	public VsbException(String message, Throwable cause){
		
		super(message, cause);
	}
}

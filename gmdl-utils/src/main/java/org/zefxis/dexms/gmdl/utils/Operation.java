package org.zefxis.dexms.gmdl.utils;

import java.util.ArrayList;
import java.util.List;

import org.zefxis.dexms.gmdl.utils.enums.OperationType;
import org.zefxis.dexms.gmdl.utils.enums.QosType;

public class Operation {

	private final String operationID;
	private final OperationType operationType;
	private final QosType qosType;
	private final Scope scope;
	private List<Data<?>> getDatas = new ArrayList<Data<?>>();
	private List<Data<?>> postDatas = new ArrayList<Data<?>>();
	private Data<?> postData = null;

	public Operation (String operationID, OperationType operationType, QosType qosType, Scope scope) {
		this.operationID = operationID;
		this.operationType = operationType;
		this.qosType = qosType;
		this.scope = scope;
	}

	public String getOperationID() {
		return operationID;
	}

	public String getScopeName() {
		return this.scope.getName();
	}

	public Scope getScope() {
		return scope;
	}

	public QosType getQosType() {
		return qosType;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public List<Data<?>> getGetDatas() {
		return getDatas;
	}

	public void addGetData(Data<?> data) {
		this.getDatas.add(data);
	}

	
	
	public List<Data<?>> getPostDatas() {
		return postDatas;
	}

	public void addPostDatas(Data<?> data) {
		
		this.postDatas.add(data);
	}
	
	public Data<?> getPostData(){
		
		return this.postData;
	}
	
	public void setPostData(Data<?> data) {
		this.postData = data;
	}
	

}
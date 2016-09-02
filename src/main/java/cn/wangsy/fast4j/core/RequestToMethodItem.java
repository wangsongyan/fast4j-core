package cn.wangsy.fast4j.core;

/**
 * 说明：
 * 
 * @author wangsy
 * @date 创建时间：2016年8月5日 下午4:03:49
 */
public class RequestToMethodItem implements Comparable<RequestToMethodItem>{
	private String controllerName;
	private String methodName;
	private String requestType;
	private String requestUrl;
	private Class<?>[] methodParmaTypes;

	public RequestToMethodItem(String requestUrl, String requestType,
			String controllerName, String requestMethodName,
			Class<?>[] methodParmaTypes) {
		this.requestUrl = requestUrl;
		this.requestType = requestType;
		this.controllerName = controllerName;
		this.methodName = requestMethodName;
		this.methodParmaTypes = methodParmaTypes;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Class<?>[] getMethodParmaTypes() {
		return methodParmaTypes;
	}

	public void setMethodParmaTypes(Class<?>[] methodParmaTypes) {
		this.methodParmaTypes = methodParmaTypes;
	}

	public int compareTo(RequestToMethodItem o) {
		return this.getRequestUrl().compareTo(o.getRequestUrl());
	}
	
}

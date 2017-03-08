package cn.wangsy.fast4j.core.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class JsonResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private int code = 200;
	private boolean succeed = true;
	private T data;
	private HashMap<String, Object> additionalProperties = new HashMap<String, Object>();

	public JsonResult(){
		
	}
	
	public JsonResult(T data){
		this.data = data;
	}
	
	public JsonResult(boolean succeed,int code,String message){
		this.succeed = succeed;
		this.code = code;
		this.message = message;
	}
	
	public JsonResult(boolean succeed,T data,String message){
		this.succeed = succeed;
		this.data = data;
		this.message = message;
	}
	
	public JsonResult(boolean succeed,T data,int code,String message){
		this.succeed = succeed;
		this.data = data;
		this.code = code;
		this.message = message;
	}
	
	public JsonResult(boolean succeed,String message){
		this.succeed = succeed;
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonAnyGetter
	public Object getAdditionalProperties(String name) {
		return this.additionalProperties.get(name);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}

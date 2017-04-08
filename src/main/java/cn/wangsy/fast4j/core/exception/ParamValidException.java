/**
 * 
 */
package cn.wangsy.fast4j.core.exception;

import java.util.List;

import cn.wangsy.fast4j.core.entity.FieldError;

/**
 * @author wangsy
 * @date 2017年3月14日下午4:35:22
 */
public class ParamValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FieldError> fieldErrors;

	public ParamValidException(List<FieldError> errors) {
		this.fieldErrors = errors;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}

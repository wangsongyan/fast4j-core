/**
 * 
 */
package cn.wangsy.fast4j.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel字段注解类
 * @author wangsy
 * @date 2016年11月11日上午10:48:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelField {

	/***
	 * 列名
	 * @return
	 */
	public String columnName();
	
	/***
	 * 排序字段
	 * @return
	 */
	public int sort() default -1;
	
	/***
	 * 列宽
	 * @return
	 */
	public short width() default 150;

}

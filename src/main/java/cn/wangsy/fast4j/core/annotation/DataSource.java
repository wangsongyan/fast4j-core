/**
 * 
 */package cn.wangsy.fast4j.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年8月28日 下午3:44:36
 */
 @Retention(RetentionPolicy.RUNTIME)
 @Target(ElementType.METHOD)
 public @interface DataSource {
	 String value();
 }

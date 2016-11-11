/**
 * 
 */
package cn.wangsy.fast4j.util;

import java.lang.reflect.Field;

import cn.wangsy.fast4j.core.annotation.ExcelField;

/**
 * Excel工具类
 * @author wangsy
 * @date 2016年11月11日上午11:03:55
 */
public class ExcelUtil<T> {

	
	public static void getAnnotation(Class<?> clazz){
		
		for(;clazz != Object.class;clazz = clazz.getSuperclass()){
			
			Field[] declaredFields = clazz.getDeclaredFields();
			for(Field field : declaredFields){
				ExcelField annotation = field.getAnnotation(ExcelField.class);
				if(null != annotation){
					System.out.println("columnName:"+annotation.columnName()+",sort:"+annotation.sort()+",width:"+annotation.width());
				}
			}
			
		}
		
	}
	
}

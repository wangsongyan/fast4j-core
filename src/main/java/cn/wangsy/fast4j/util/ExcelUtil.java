/**
 * 
 */
package cn.wangsy.fast4j.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import cn.wangsy.fast4j.core.annotation.ExcelField;

/**
 * Excel工具类
 * @author wangsy
 * @date 2016年11月11日上午11:03:55
 */
public class ExcelUtil<T> {

	
	public static HashMap<String, Object> getAnnotation(Class<?> clazz){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for(;clazz != Object.class;clazz = clazz.getSuperclass()){
			
			Field[] declaredFields = clazz.getDeclaredFields();
			for(Field field : declaredFields){
				ExcelField annotation = field.getAnnotation(ExcelField.class);
				if(null != annotation){
					System.out.println("columnName:"+annotation.columnName()+",sort:"+annotation.sort()+",width:"+annotation.width());
					
					String fieldname = field.getName();
					String setMethodName = "set"
							+ fieldname.substring(0, 1).toUpperCase()
							+ fieldname.substring(1);
					// 构造调用的method
					Method setMethod = null;
					try {
						setMethod = clazz.getMethod(setMethodName,
								new Class[] { field.getType() });
						map.put(annotation.columnName(), setMethod);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		return map;
	}
	
}

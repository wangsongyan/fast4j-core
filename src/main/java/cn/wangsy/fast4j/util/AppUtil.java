package cn.wangsy.fast4j.util;

import java.util.UUID;

/** 
 * @author wangsy
 * @date 创建时间：2016年7月24日 上午12:06:29
 * @version 1.0
 */
public class AppUtil {

	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[]args){
		System.out.println(AppUtil.getUUID());
	}
	
}

package cn.wangsy.fast4j.util;

import java.util.Set;

/** 
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年8月5日 下午4:08:35
 */
public class SetUtils {

	public static <T> T first(Set<T> sets) {
		if(!sets.isEmpty()){
			return sets.iterator().next();
		}
		return null;
	}

}

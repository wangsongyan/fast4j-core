package cn.wangsy.fast4j.core.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import cn.wangsy.fast4j.core.annotation.Operation;

/** 
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年8月5日 上午10:15:44
 */
public interface LogPoint {

	void save(ProceedingJoinPoint joinPoint,Method method,Operation operation);
	
}

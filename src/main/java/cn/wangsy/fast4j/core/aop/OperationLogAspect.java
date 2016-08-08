package cn.wangsy.fast4j.core.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import cn.wangsy.fast4j.core.annotation.Operation;

/** 
 * @author wangsy
 * @date 创建时间：2016年7月24日 下午10:11:40
 * @version 1.0
 */
public class OperationLogAspect  {
     
	private LogPoint logPoint;
     
    public Object saveLog(ProceedingJoinPoint joinPoint)throws Throwable{
    	String methodName = joinPoint.getSignature().getName();
		Method method = currentMethod(joinPoint, methodName);
		Operation operation = method.getAnnotation(Operation.class);
    	
		if(null != logPoint){
			logPoint.save(joinPoint, method, operation);
		}
		
		return joinPoint.proceed();
    }
   
	/**
	 * 获取当前执行的方法
	 *
	 * @param joinPoint
	 *            连接点
	 * @param methodName
	 *            方法名称
	 * @return 方法
	 */
	private Method currentMethod(ProceedingJoinPoint joinPoint, String methodName) {
		/**
		 * 获取目标类的所有方法，找到当前要执行的方法
		 */
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		Method resultMethod = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				resultMethod = method;
				break;
			}
		}
		return resultMethod;
	}
    
    public LogPoint getLogPoint() {
		return logPoint;
	}

	public void setLogPoint(LogPoint logPoint) {
		this.logPoint = logPoint;
	}
	
}

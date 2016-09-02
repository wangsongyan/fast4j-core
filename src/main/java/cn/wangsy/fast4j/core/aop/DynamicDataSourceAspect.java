package cn.wangsy.fast4j.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.wangsy.fast4j.core.annotation.DataSource;
import cn.wangsy.fast4j.core.datasource.DataSourceContextHolder;

/** 
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年8月28日 下午3:55:41
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

	@Around("@annotation(dataSource)") //可以直接捕获下面这个方法中参数所设定的注解类型
	public Object doArround(ProceedingJoinPoint joinPoint, DataSource dataSource) throws Throwable{
		Object ret = null;
		boolean isSelectDataSource = false;
		try {
			if(null != dataSource){
				String key = dataSource.value();
				DataSourceContextHolder.setDataSourceType(key);
				isSelectDataSource = true;
			}
			ret = joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		} finally{
			if(isSelectDataSource){
				DataSourceContextHolder.clearDataSourceType();
			}
		}
		return ret;
	}
	
}

package cn.wangsy.fast4j.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 说明：在需要验证表单是否重复的方法上加上此注解
 * @author wangsy
 * @date 创建时间：2016年8月12日 下午12:22:02
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface TokenValid {}

package cn.wangsy.fast4j.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.wangsy.fast4j.core.aop.OperationType;

/** 
 * @Descrption 该注解描述方法的操作类型和方法的参数意义
 * @author wangsy
 * @date 创建时间：2016年7月24日 下午10:08:44
 * @version 1.0
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Operation {
    /**
     * @Description 描述操作类型,参见{@link OperationType} ,为必填项
     */
    OperationType type();
 
    /**
     * @Description 描述操作意义,比如申报通过或者不通过等
     */
    String desc() default ""; 
 
    /**
     * @Description 描述操作方法的参数意义,数组长度需与参数长度一致,否则无效
     */
    String[] arguDesc() default {};
}


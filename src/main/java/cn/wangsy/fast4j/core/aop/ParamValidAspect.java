/**
 * 
 */
package cn.wangsy.fast4j.core.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import cn.wangsy.fast4j.core.entity.FieldError;
import cn.wangsy.fast4j.core.exception.ParamValidException;

/**
 * @author wangsy
 * @date 2017年3月14日下午4:33:29
 */
public class ParamValidAspect{

    Logger log = LoggerFactory.getLogger(getClass());

    //@Pointcut("execution(* diamond.cms.server.mvc.controllers.*.*(..))")
    //public void controllerBefore(){};

    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    //@Before("controllerBefore()")
    public void before(JoinPoint point) throws NoSuchMethodException, SecurityException, ParamValidException{
        //  获得切入目标对象
        Object target = point.getThis();
        // 获得切入方法参数
        Object [] args = point.getArgs(); 
        // 获得切入的方法
        Method method = ((MethodSignature)point.getSignature()).getMethod(); 

        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

        if (!validResult.isEmpty()) {
//            String [] parameterNames = parameterNameDiscoverer.getParameterNames(method); // 获得方法的参数名称
//            List<FieldError> errors = validResult.stream().map(constraintViolation -> {
//                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
//                int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
//                String paramName = parameterNames[paramIndex];  // 获得校验的参数名称
//                FieldError error = new FieldError();  // 将需要的信息包装成简单的对象，方便后面处理
//                error.setName(paramName);  // 参数名称（校验错误的参数名称）
//                error.setMessage(constraintViolation.getMessage()); // 校验的错误信息
//                return error;
//            }).collect(Collectors.toList());
            
        	
        	List<FieldError> errors = new ArrayList<FieldError>();
            Iterator<ConstraintViolation<Object>> iterator = validResult.iterator();
            String [] parameterNames = parameterNameDiscoverer.getParameterNames(method); // 获得方法的参数名称
            while(iterator.hasNext()){
            	ConstraintViolation<Object> constraintViolation = iterator.next();
            	PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
                int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
                String paramName = parameterNames[paramIndex];  // 获得校验的参数名称
                FieldError error = new FieldError();  // 将需要的信息包装成简单的对象，方便后面处理
                error.setName(paramName);  // 参数名称（校验错误的参数名称）
                error.setMessage(constraintViolation.getMessage()); // 校验的错误信息
                errors.add(error);
            }
            throw new ParamValidException(errors);  // 我个人的处理方式，抛出异常，交给上层处理
            
        }
    }

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object [] params){
        return validator.validateParameters(obj, method, params);
    }
}

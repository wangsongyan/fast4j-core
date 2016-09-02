package cn.wangsy.fast4j.core.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.wangsy.fast4j.core.annotation.Token;
import cn.wangsy.fast4j.core.annotation.TokenValid;

/** 
 * 说明：
 * @author wangsy
 * @date 创建时间：2016年8月12日 下午12:28:47
 */
@Aspect
@Component
public class ReSubmitAspect {
	
	private static final String TOKEN = "token";
	private static final String TOKEN_FLAG = "TokenFlag_";
	
	@Around("@annotation(token)") //可以直接捕获下面这个方法中参数所设定的注解类型
	public Object addToken(ProceedingJoinPoint joinPoint, Token token) throws Throwable{
		Object[] args = joinPoint.getArgs();
		String className = joinPoint.getTarget().getClass().getName();
		Object result = null;
		for(Object a : args){
			if(a != null && a instanceof HttpServletRequest){
				HttpServletRequest request = (HttpServletRequest)a;
				HttpSession session = request.getSession(true);
				String flag = DigestUtils.md5Hex(className + new Date().getTime()); //创建一个token的flag
				session.setAttribute(TOKEN_FLAG + className, flag);
				request.setAttribute(TOKEN, flag);
				result = joinPoint.proceed();
			}
		}
		return result;
	}
	
	@Around("@annotation(tokenValid)")
	public Object checkToken(ProceedingJoinPoint joinPoint, TokenValid tokenValid) throws Throwable{
		Object[] args = joinPoint.getArgs();
		String className = joinPoint.getTarget().getClass().getName();
		Object result = null;
		for(Object a : args){
			if(a != null && a instanceof HttpServletRequest){
				HttpServletRequest request = (HttpServletRequest)a;
				HttpSession session = request.getSession(true);
				Object sessionFlag = session.getAttribute(TOKEN_FLAG + className);
				Object requestFlag = request.getParameter(TOKEN);
				if(sessionFlag != null &&sessionFlag.equals(requestFlag)){ //验证结果一致，既为第一次提交，删除会话中存储的token，并继续执行方法。否则不做任何处理。
					session.removeAttribute(TOKEN_FLAG + className);
					result = joinPoint.proceed();
				}
			}
		}
		return result;
	}
}

package cn.wangsy.fast4j.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 说明：
 * 
 * @author wangsy
 * @date 创建时间：2016年8月28日 下午10:33:35
 */
public class HttpHelper {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

}

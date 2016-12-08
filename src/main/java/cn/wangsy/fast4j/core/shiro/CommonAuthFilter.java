/**
 * 
 */
package cn.wangsy.fast4j.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @author wangsy
 * @date 2016年12月8日上午11:21:37
 */
public class CommonAuthFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object arg2) throws Exception {
		String requestURI = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
        Subject subject = this.getSubject(request, response);
        //超级管理员无阻
        if(subject.hasRole("adminrole")) return true;
        //通过subject判断用户有没有些url权限
        return subject.isPermitted(requestURI);
	}

}

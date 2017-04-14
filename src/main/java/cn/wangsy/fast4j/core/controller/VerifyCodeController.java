/**
 * 
 */
package cn.wangsy.fast4j.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.wangsy.fast4j.core.security.ImageCode;

/**
 * @author wangsy
 * @date 2017年4月14日下午2:38:01
 */
public abstract class VerifyCodeController {

	@RequestMapping("/verifyCode")
	public String code(HttpSession session, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		ImageCode vCode = new ImageCode(140, 30);
		session.setAttribute(getVerifyCodeKey(), vCode.getCode());
		vCode.write(response.getOutputStream());
		return null;
	} 
	
	protected abstract String getVerifyCodeKey();
	
}

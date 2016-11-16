/**
 * 
 */
package cn.wangsy.fast4j.core.template.impl;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import cn.wangsy.fast4j.core.template.TemplateProcessService;

/**
 * 需要注入 velocityEngine
 * @author wangsy
 * @date 2016年11月16日下午2:42:41
 */
public class VelocityTemplateProcessService implements TemplateProcessService{

	private VelocityEngine velocityEngine;
	
	public String process(String templateName, Map<String, Object> data) {
		Template template = velocityEngine.getTemplate(templateName);
		VelocityContext context = new VelocityContext(data);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer.toString();
	}

	public String processBySource(String templateSource, Map<String, Object> data) {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();
		VelocityContext context = new VelocityContext(data);
		StringWriter writer = new StringWriter();
		velocityEngine.evaluate(context, writer, "", templateSource); // 关键方法
		return writer.toString();
	}

	/*public static void main(String[]args){
		TemplateProcessService service = new VelocityTemplateProcessService();
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("user", "wangsy");
		String result = service.processBySource("hello,$user", rootMap);
		System.out.println(result);
	}*/
	
}

/**
 * 
 */
package cn.wangsy.fast4j.core.template.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.wangsy.fast4j.core.template.TemplateProcessService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * Freemarker模板引擎
 * 
 * @author wangsy
 * @date 2016年11月16日下午2:09:09
 */
public class FreemarkerTemplateProcessService implements TemplateProcessService{

	private static final String TEMPLATE_NAME = "template-name";
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	public String process(String templateName, Map<String, Object> data) {
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		try {
			Template template = configuration.getTemplate(templateName);
			Writer out = new StringWriter();
			template.process(data, out);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String processBySource(String templateSource, Map<String, Object> data) {
		Configuration configuration = new Configuration();
		StringTemplateLoader loader = new StringTemplateLoader();
		loader.putTemplate(TEMPLATE_NAME, templateSource);
		configuration.setTemplateLoader(loader);
		try {
			Template template = configuration.getTemplate(TEMPLATE_NAME);
			Writer out = new StringWriter();
			template.process(data, out);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void main(String[]args){
		TemplateProcessService service = new FreemarkerTemplateProcessService();
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("user", "wangsy");
		String result = service.processBySource("hello,${user}", rootMap);
		System.out.println(result);
	}*/
	
}

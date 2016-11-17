/**
 * 
 */
package cn.wangsy.fast4j.core.template.impl;

import java.io.IOException;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import cn.wangsy.fast4j.core.template.TemplateProcessService;

/**
 * @author wangsy
 * @date 2016年11月16日下午3:09:36
 */
public class BeetlTemplateProcessService implements TemplateProcessService{

	private GroupTemplate groupTemplate;
	
	public String process(String templateName, Map<String, Object> data) {
		Template template = groupTemplate.getTemplate(templateName);
		template.binding(data);
		return template.render();
	}

	public String processBySource(String templateSource, Map<String, Object> data) {
		try {
			StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();		
			Configuration cfg = Configuration.defaultConfiguration();
			GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
			Template template = gt.getTemplate(templateSource);
			template.binding(data);
			return template.render();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setGroupTemplate(GroupTemplate groupTemplate) {
		this.groupTemplate = groupTemplate;
	}
	
	/*public static void main(String[]args){
		TemplateProcessService service = new BeetlTemplateProcessService();
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("user", "wangsy");
		String result = service.processBySource("hello,${user}", rootMap);
		System.out.println(result);
	}*/
	
}

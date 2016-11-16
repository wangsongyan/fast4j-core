/**
 * 
 */
package cn.wangsy.fast4j.core.template;

import java.util.Map;


/**
 * 
 * 模板 + 数据 = 结果
 * 
 * @author wangsy
 * @date 2016年11月16日下午2:03:35
 */
public interface TemplateProcessService {

	/***
	 * 根据模板名字，数据获取渲染结果
	 * @param templateName 模板名字
	 * @param data 数据
	 * @return
	 */
	String process(String templateName, Map<String, Object> data);
	
	/***
	 * 根据模板内容，数据获取渲染结果
	 * @param templateSource 模板内容
	 * @param data 数据
	 * @return
	 */
	String processBySource(String templateSource, Map<String, Object> data);
	
}

package cn.wangsy.fast4j.core.orm.mybatis.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 说明：mybatis-generator逆向工程 mysql分页插件
 * 
 * @author wangsy
 * @date 创建时间：2016年9月8日 上午10:32:17
 */
public class MysqlPaginationPlugin extends BasePaginationPlugin {

	@Override
	protected void addXml(XmlElement element) {
		XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
		isNotNullElement.addAttribute(new Attribute("test", "pager != null")); //$NON-NLS-1$ //$NON-NLS-2$
		isNotNullElement.addElement(new TextElement(
				"limit #{pager.pageBegin} , #{pager.pageEnd}"));
		element.addElement(isNotNullElement);
	}
	
	/**
	 * This plugin is always valid - no properties are required
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}
	
}

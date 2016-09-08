package cn.wangsy.fast4j.core.orm.mybatis.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/** 
 * 说明：mybatis-generator逆向工程 oracle分页插件
 * @author wangsy
 * @date 创建时间：2016年9月8日 上午11:32:00
 */
public class OraclePaginationPlugin extends BasePaginationPlugin{

	@Override
	protected void addXml(XmlElement element) {
		XmlElement pageStart = new XmlElement("include"); //$NON-NLS-1$     
        pageStart.addAttribute(new Attribute("refid", "OracleDialectPrefix"));  
        element.getElements().add(0, pageStart);  
  
        XmlElement isNotNullElement = new XmlElement("include"); //$NON-NLS-1$     
        isNotNullElement.addAttribute(new Attribute("refid",  
                "OracleDialectSuffix"));  
        element.getElements().add(isNotNullElement);
	}
	
    @Override  
    public boolean sqlMapDocumentGenerated(Document document,  
            IntrospectedTable introspectedTable) {  
        XmlElement parentElement = document.getRootElement();  
  
        // 产生分页语句前半部分  
        XmlElement paginationPrefixElement = new XmlElement("sql");  
        paginationPrefixElement.addAttribute(new Attribute("id",  
                "OracleDialectPrefix"));  
        XmlElement pageStart = new XmlElement("if");  
        pageStart.addAttribute(new Attribute("test", "page != null"));  
        pageStart.addElement(new TextElement(  
                "select * from ( select row_.*, rownum rownum_ from ( "));  
        paginationPrefixElement.addElement(pageStart);  
        parentElement.addElement(paginationPrefixElement);  
  
        // 产生分页语句后半部分  
        XmlElement paginationSuffixElement = new XmlElement("sql");  
        paginationSuffixElement.addAttribute(new Attribute("id",  
                "OracleDialectSuffix"));  
        XmlElement pageEnd = new XmlElement("if");  
        pageEnd.addAttribute(new Attribute("test", "page != null"));  
        pageEnd.addElement(new TextElement(  
                "<![CDATA[ ) row_ ) where rownum_ > #{page.pageBegin} and rownum_ <= #{page.pageEnd} ]]>"));  
        paginationSuffixElement.addElement(pageEnd);  
        parentElement.addElement(paginationSuffixElement);  
  
        return super.sqlMapDocumentGenerated(document, introspectedTable);  
    } 
	
	public boolean validate(List<String> warnings) {
		return true;
	}

}

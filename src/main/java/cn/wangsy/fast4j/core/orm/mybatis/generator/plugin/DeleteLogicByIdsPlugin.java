package cn.wangsy.fast4j.core.orm.mybatis.generator.plugin;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

/** 
 * 说明：逻辑删除插件
 * @author wangsy
 * @date 创建时间：2016年9月8日 下午12:41:45
 */
public class DeleteLogicByIdsPlugin extends PluginAdapter{

	private String fieldName;//删除标记字段名称，例如deleteFlag,isDelete
	
	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {
		interfaze.addMethod(generateDeleteLogicByIds(method, introspectedTable));
		return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze,
				introspectedTable);
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addMethod(generateDeleteLogicByIds(method, introspectedTable));
		return super.clientSelectByPrimaryKeyMethodGenerated(method, topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名  
		XmlElement parentElement = document.getRootElement();
		// 产生分页语句前半部分
		XmlElement deleteLogicByIdsElement = new XmlElement("update");
		deleteLogicByIdsElement.addAttribute(new Attribute("id", "deleteLogicByIds"));
		deleteLogicByIdsElement.addElement(
				new TextElement(
				"update " + tableName + " set "+fieldName+" = #{deleteFlag,jdbcType=VARCHAR} where id in "
				+ " <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach> "
				));
		parentElement.addElement(deleteLogicByIdsElement);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	private Method generateDeleteLogicByIds(Method method, IntrospectedTable introspectedTable) {
		Method m = new Method("deleteLogicByIds");
		m.setVisibility(method.getVisibility());
		m.setReturnType(FullyQualifiedJavaType.getIntInstance());
		m.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "deleteFlag", "@Param(\"deleteFlag\")"));
		m.addParameter(new Parameter(FullyQualifiedJavaType.getNewListInstance(), "ids", "@Param(\"ids\")"));
		context.getCommentGenerator().addGeneralMethodComment(m,
				introspectedTable);
		return m;
	}
	
	public boolean validate(List<String> warnings) {
		fieldName = properties.getProperty("fieldName"); //$NON-NLS-1$
		if(!StringUtility.stringHasValue(fieldName)){
			warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                    "DeleteLogicByIdsPlugin", //$NON-NLS-1$
                    "fieldName")); //$NON-NLS-1$
			return false;
		}
		return true;
	}

}

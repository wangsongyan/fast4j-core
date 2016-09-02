package cn.wangsy.fast4j.core.orm.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

public class MyCommentGenerator extends DefaultCommentGenerator {

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		compilationUnit.addFileCommentLine("/*");
		compilationUnit.addFileCommentLine(" * " + compilationUnit.getType().getShortName() + ".java");
		compilationUnit.addFileCommentLine(" * Copyright(C) 2011-2016");
		compilationUnit.addFileCommentLine(" * All rights reserved.");
		compilationUnit.addFileCommentLine(" * --------------------------------");
		compilationUnit.addFileCommentLine(" */");
	}

	@Override
	public void addFieldComment(Field field,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {

		field.addJavaDocLine("/**");
		if (introspectedColumn.getRemarks() != null
				&& !introspectedColumn.getRemarks().equals("")) {
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		}
		StringBuilder sb = new StringBuilder();
		field.addJavaDocLine(" * "); //$NON-NLS-1$
		sb.append(" * This field corresponds to the database column "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		sb.append('.');
		sb.append(introspectedColumn.getActualColumnName());
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" */");
	}

}

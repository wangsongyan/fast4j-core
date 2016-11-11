package cn.wangsy.fast4j.core.test;

import cn.wangsy.fast4j.core.annotation.ExcelField;

public class Export {

	@ExcelField(columnName = "姓名")
	private String name;
	@ExcelField(columnName = "年龄")
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

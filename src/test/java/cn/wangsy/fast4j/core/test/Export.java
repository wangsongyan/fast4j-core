package cn.wangsy.fast4j.core.test;

import cn.wangsy.fast4j.core.annotation.ExcelField;

public class Export {

	@ExcelField(columnName = "姓名",sort = 1,width = 200)
	private String name;
	@ExcelField(columnName = "年龄",sort = 4)
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}

package cn.wangsy.fast4j.core.test;

import cn.wangsy.fast4j.core.annotation.ExcelField;

public class ExportEx extends Export{

	@ExcelField(columnName = "性别",sort = 2)
	private String gender;
	
	@ExcelField(columnName = "民族",sort = 3)
	private String nation;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
}

package cn.wangsy.fast4j.core.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.wangsy.fast4j.util.ExcelUtil;

public class ExcelUtilTest {

	
	public void test(){
			
		List<ExportEx> list = new ArrayList<ExportEx>();
		ExportEx ex = new ExportEx();
		ex.setAge(10);
		ex.setName("王");
		ex.setNation("汉族");
		ex.setGender("男");
		list.add(ex);
		
		ExcelUtil<ExportEx> util = new ExcelUtil<ExportEx>(ExportEx.class);
		Workbook workbook = util.write(list);
		try {
			workbook.write(new FileOutputStream("1.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testRead(){
		
		ExcelUtil<ExportEx> util = new ExcelUtil<ExportEx>(ExportEx.class);
		try {
			List<ExportEx> list = (List<ExportEx>) util.read(new FileInputStream("1.xls"), "xls");
			System.out.println(JSON.toJSONString(list));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

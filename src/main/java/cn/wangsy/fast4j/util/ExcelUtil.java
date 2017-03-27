/**
 * 
 */
package cn.wangsy.fast4j.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.wangsy.fast4j.core.annotation.ExcelField;

/**
 * Excel工具类
 * 
 * @author wangsy
 * @date 2016年11月11日上午11:03:55
 */
public class ExcelUtil<T> {

	/**
	 * xls文件类型
	 * */
	public static final String EXCEL_TYPE_XLS = "xls";

	/**
	 * xlsx文件类型
	 * */
	public static final String EXCEL_TYPE_XLSX = "xlsx";

	public static final String FIELD_TYPE_STREING = "class java.lang.String";
	public static final String FIELD_TYPE_DATE = "class java.util.Date";
	public static final String FIELD_TYPE_BOOLEAN = "class java.lang.Boolean";
	public static final String FIELD_TYPE_INTEGER = "class java.lang.Integer";
	public static final String FIELD_TYPE_LONG = "class java.lang.Long";

	public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");

	private Class<?> clazz;

	public ExcelUtil(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Workbook write(List<T> list) {

		Map<String, ExcelField> cache = new HashMap<String, ExcelField>();
		List<ExcelField> annotations = new ArrayList<ExcelField>();
		Map<ExcelField, Method> methodMap = new HashMap<ExcelField, Method>();

		for (Class<?> clazz1 = clazz; clazz1 != Object.class; clazz1 = clazz1
				.getSuperclass()) {
			Field fileds[] = clazz1.getDeclaredFields();
			for (Field field : fileds) {
				// 获取字段上的注解信息
				ExcelField annotation = field.getAnnotation(ExcelField.class);
				if (null != annotation) {
					if (null == cache.get(field.getName())) {
						cache.put(field.getName(), annotation);
						annotations.add(annotation);

						String fieldname = field.getName();
						String setMethodName = "get"
								+ fieldname.substring(0, 1).toUpperCase()
								+ fieldname.substring(1);
						try {
							// 构造调用的method
							Method setMethod = clazz.getMethod(setMethodName,
									new Class[] {});
							methodMap.put(annotation, setMethod);
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		Collections.sort(annotations, new Comparator<ExcelField>() {
			public int compare(ExcelField obj1, ExcelField obj2) {
				if (obj1.sort() > obj2.sort()) {
					return 1;
				} else if (obj1.sort() == obj2.sort()) {
					return 0;
				} else {
					return -1;
				}
			}
		});

		// 创建excel工作簿
		Workbook wb = new HSSFWorkbook();
		// 2007 版
		// Workbook wb = new XSSFWorkbook();
		// 创建第一个sheet（页），并命名
		Sheet sheet = wb.createSheet("sheet1");
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < annotations.size(); i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * annotations.get(i).width()));
		}

		// 创建第一行
		Row row = sheet.createRow((short) 0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// Font f3=wb.createFont();
		// f3.setFontHeightInPoints((short) 10);
		// f3.setColor(IndexedColors.RED.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置列名
		for (int i = 0; i < annotations.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(annotations.get(i).columnName());
			cell.setCellStyle(cs);
		}
		// 设置每行每列的值
		for (int i = 0; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			// System.out.println(i);
			Row row1 = sheet.createRow((i + 1));

			// 在row行上创建一个方格
			for (short j = 0; j < annotations.size(); j++) {
				Cell cell = row1.createCell(j);
				Object value = null;
				Method method = methodMap.get(annotations.get(j));
				try {
					value = method.invoke(list.get(i));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

				// 得到目标方法返回类型对应的Type对象
				Type ts = method.getGenericReturnType();
				String type = ts.toString();

				if (type.equals("class java.util.Date")) {
					CellStyle dateStyle = wb.createCellStyle();
					dateStyle.setFont(f2);
					dateStyle.setBorderLeft(CellStyle.BORDER_THIN);
					dateStyle.setBorderRight(CellStyle.BORDER_THIN);
					dateStyle.setBorderTop(CellStyle.BORDER_THIN);
					dateStyle.setBorderBottom(CellStyle.BORDER_THIN);
					dateStyle.setAlignment(CellStyle.ALIGN_CENTER);
					dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
					cell.setCellStyle(dateStyle);
					if (value == null) {
						cell.setCellValue(" ");
					} else {
						cell.setCellValue((Date) value);
					}
				} else {
					cell.setCellValue(value == null ? " " : value.toString());
					cell.setCellStyle(cs2);
				}
			}
		}
		return wb;

	}

	@SuppressWarnings("unchecked")
	public Collection<T> read(InputStream inputStream, String fileType) {
		Workbook wb = null;
		try {
			if (fileType.equals(EXCEL_TYPE_XLS)) {
				wb = new HSSFWorkbook(inputStream);
			} else if (fileType.equals(EXCEL_TYPE_XLSX)) {
				wb = new XSSFWorkbook(inputStream);
			} else {
				System.out.println("excel格式不正确");
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Sheet sheet = wb.getSheetAt(0);
		LinkedHashMap<Integer, String> titleMap = new LinkedHashMap<Integer, String>();
		Iterator<Row> rowIterator = sheet.rowIterator();
		// 获取标题行
		Row titleRow = rowIterator.next();
		int columns = titleRow.getLastCellNum();
		for (int i = 0; i < columns; i++) {
			Cell cell = titleRow.getCell(i);
			if (null == cell) {// 标题栏只要读到空单元格就认为结束
				break;
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String value = cell.getStringCellValue();
			titleMap.put(i, value);
		}

		Map<String, Method> methodMap = new HashMap<String, Method>();
		for (Class<?> clazz1 = clazz; clazz1 != Object.class; clazz1 = clazz1.getSuperclass()) {
			Field fileds[] = clazz1.getDeclaredFields();
			for (Field field : fileds) {
				// 获取字段上的注解信息
				ExcelField annotation = field.getAnnotation(ExcelField.class);
				if (null != annotation) {
					String fieldname = field.getName();
					String setMethodName = "set"
							+ fieldname.substring(0, 1).toUpperCase()
							+ fieldname.substring(1);

					try {
						// 构造调用的method
						Method setMethod = clazz.getMethod(setMethodName,
								new Class[] { field.getType() });
						methodMap.put(annotation.columnName(), setMethod);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				}
			}
		}

		Collection<T> dist = new ArrayList<T>();
		int rows = sheet.getLastRowNum();
		for (int i = 1; i <= rows; i++) {
			Row row = sheet.getRow(i);
			if (row != null && row.getCell(0) != null) {
				T tObject = null;
				try {
					tObject = (T) clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				int cols = titleMap.size();
				for (int j = 0; j < cols; j++) {
					Cell cell = row.getCell(j);
					String title = (String) titleMap.get(j);
					if (methodMap.containsKey(title)) {
						Method setMethod = (Method) methodMap.get(title);
						if (null != cell) {
							set(setMethod, cell, tObject);
						}
					}
				}
				dist.add(tObject);
			}
		}

		return dist;
	}

	/**
	 * 给Object赋值
	 * */
	public void set(Method setMethod, Cell cell, T tObject) {
		try {
			Type[] ts = setMethod.getGenericParameterTypes();
			String type = ts[0].toString();
			if (type.equals(FIELD_TYPE_STREING)) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				setMethod.invoke(tObject, cell.getStringCellValue());
			} else if (type.equals(FIELD_TYPE_DATE)) {
				try {
					double value = cell.getNumericCellValue();
					Date date = DateUtil.getJavaDate(value);
					setMethod.invoke(tObject, date);
				} catch (Exception e) {
					setMethod.invoke(tObject, string2Date(cell));
				}
			} else if (type.equals(FIELD_TYPE_BOOLEAN)) {
				Boolean boolname = true;
				if (cell.getStringCellValue().equals("否")) {
					boolname = false;
				}
				setMethod.invoke(tObject, boolname);
			} else if (type.equals(FIELD_TYPE_INTEGER)) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				setMethod.invoke(tObject,new Integer(cell.getStringCellValue()));
			} else if (type.equals(FIELD_TYPE_LONG)) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				setMethod.invoke(tObject, new Long(cell.getStringCellValue()));
			} else {
				//TODO Java基础类型处理
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Date string2Date(Cell cell) {
		SimpleDateFormat simpleDateFormat;
		try {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			return cell.getDateCellValue();
		} catch (Exception e0) {
			try {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String source = cell.getStringCellValue();
				simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
				return simpleDateFormat.parse(source);
			} catch (Exception e1) {
				try {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String source = cell.getStringCellValue();
					simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					return simpleDateFormat.parse(source);
				} catch (Exception e2) {
					try {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						String source = cell.getStringCellValue();
						simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
						return simpleDateFormat.parse(source);
					} catch (Exception e3) {
						return null;
					}
				}

			}
		}
	}

}

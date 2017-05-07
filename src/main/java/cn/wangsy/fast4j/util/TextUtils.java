/**
 * 
 */
package cn.wangsy.fast4j.util;

import java.util.regex.Pattern;

/**
 * 
 * 字符串操作，区别于StringUtils
 * 
 * @author wangsy
 * @date 2016年12月7日下午12:23:38
 */
public class TextUtils {

	private TextUtils() {

	}

	/**
	 * 
	 * 判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	 * 判断字符串是否是中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 获取字符串中的中文
	 * 
	 * @param str
	 * @return
	 */
	public static String getChinese(String str) {
		return str.replaceAll("[^\\u4e00-\\u9fa5]", "");
	}

}

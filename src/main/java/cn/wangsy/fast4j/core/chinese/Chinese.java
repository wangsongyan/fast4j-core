/**
 * 
 */
package cn.wangsy.fast4j.core.chinese;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangsy
 * @date 2017年5月4日下午1:23:07
 */
@SuppressWarnings("unchecked")
public class Chinese {

	// 字库文件
	private static final String DIC_NAME = "/BiShunKu.txt";
	private static Map<String, Word> words = new HashMap<String, Word>();
	private static Logger logger = LoggerFactory.getLogger(Chinese.class);

	static {
		try {
			List<String> lines = IOUtils.readLines(Chinese.class.getResourceAsStream(DIC_NAME)/* , "gbk" */);
			for (String str : lines) {
				String character = str.substring(0, 1);
				String sequence = str.substring(1).trim();
				words.put(character, new Word(character, sequence.length(),
						sequence));
			}
		} catch (IOException e) {
			logger.error("load dictionary failed.");
		}
	}
	
	private Chinese(){
		
	}
	
	// 获取汉字总笔画数
	public static int stroke(String str) throws UnsupportedWordException {
		int total = 0;
		for (int i = 0; i < str.length(); i++) {
			total += get(String.valueOf(str.charAt(i))).getTotal();
		}
		return total;
	}

	// 根据汉字获取Word
	public static Word get(String str) throws UnsupportedWordException {
		Word word = words.get(str);
		if (null == word) {
			throw new UnsupportedWordException("unsupportedword:" + str);
		}
		return word;
	}

}
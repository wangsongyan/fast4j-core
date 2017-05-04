package cn.wangsy.fast4j.core.chinese;

import java.util.Comparator;

/***
 * 中文汉字比较器
 * 
 * @author wangsy
 * @date 2017年5月4日下午8:53:44
 */
public class StrokeComparator implements Comparator<String> {

	private boolean sameLastname;

	public StrokeComparator() {
		this.sameLastname = false;
	}

	/***
	 * 是否开启"同姓,长度短排在前面"的规则
	 * 
	 * @param sameLastname
	 *            true开启，false关闭
	 */
	public StrokeComparator(boolean sameLastname) {
		this.sameLastname = sameLastname;
	}

	public int compare(String o1, String o2) {
		if (o1.length() == 1 && o1.length() != o2.length()) {
			return -1;
		} else if (o2.length() == 1 && o1.length() != o2.length()) {
			return 1;
		}

		for (int i = 0; i < o1.length() && i < o2.length(); i++) {
			int codePoint1 = o1.codePointAt(i);
			int codePoint2 = o2.codePointAt(i);
			if (codePoint1 == codePoint2) {
				continue;
			}

			// 同姓不同长度
			if (sameLastname && i == 1 && o1.length() != o2.length()) {
				return o1.length() - o2.length();
			}

			try {
				Word word1 = Chinese.getInstance().get(
						String.valueOf(Character.toChars(codePoint1)));
				Word word2 = Chinese.getInstance().get(
						String.valueOf(Character.toChars(codePoint2)));
				int stroke1 = word1.getTotal();
				int stroke2 = word2.getTotal();
				if (stroke1 < 0 || stroke2 < 0) {
					return codePoint1 - codePoint2;
				}
				if (stroke1 != stroke2) {
					return stroke1 - stroke2;
				} else { // 笔画数相等时，根据笔画顺序排序
					return word1.getSequence().compareTo(word2.getSequence());
				}
			} catch (UnsupportedWordException e) {
				e.printStackTrace();
			}
		}
		return o1.length() - o2.length();
	}
}
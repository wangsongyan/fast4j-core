package cn.wangsy.fast4j.core.chinese;

/**
 * @author wangsy
 * @date 2017年5月4日下午1:38:04
 */
public class Word {

	// 汉字
	private String text;
	// 总画数
	private int total;
	// 笔画顺序
	private String sequence;
	
	public Word(){}
	
	public Word(String text,int total,String sequence){
		this.text = text;
		this.total = total;
		this.sequence = sequence;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
}

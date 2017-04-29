/**
 * 
 */
package cn.wangsy.fast4j.core.wechat.message;

/**
 * 文本消息类
 * @author wangsy
 * @date 2017年4月29日下午3:21:01
 */
public class TextMessage extends BaseMessage {

	private String Content;// 文本消息内容

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}

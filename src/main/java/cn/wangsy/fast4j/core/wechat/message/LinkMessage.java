/**
 * 
 */
package cn.wangsy.fast4j.core.wechat.message;

/**
 * 链接消息
 * 
 * @author wangsy
 * @date 2017年4月29日下午3:34:13
 */
public class LinkMessage extends BaseMessage {

	private String Title;// 消息标题
	private String Description;// 消息描述
	private String Url;// 消息链接

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}

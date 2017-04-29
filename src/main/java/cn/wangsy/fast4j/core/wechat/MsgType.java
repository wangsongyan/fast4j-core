/**
 * 
 */
package cn.wangsy.fast4j.core.wechat;

/**
 * 微信消息类型枚举类
 * @author wangsy
 * @date 2017年4月29日下午2:53:23
 */
public enum MsgType {

	TEXT("text","文本消息"),
	IMAGE("image","图片消息"),
	VOICE("voice","语音消息"),
	VIDEO("video","视频消息"),
	SHORTVIDEO("shortvideo","小视频消息"),
	LOCATION("location","地理位置消息"),
	LINK("link","链接消息"),
	EVENT("event","事件消息");
	
	private String type;
	private String name;
	
	private MsgType(String type, String name){
		this.type = type;
		this.name = name;
	}
	
}

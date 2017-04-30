package cn.wangsy.fast4j.core.wechat;

import org.dom4j.DocumentException;

import cn.wangsy.fast4j.core.wechat.message.TextMessage;

import com.thoughtworks.xstream.XStream;

public class Converter {

	public static void main(String[] args) throws DocumentException {
		
		String xml = "<xml><ToUserName><![CDATA[fromUser]]></ToUserName><FromUserName><![CDATA[toUser]]></FromUserName><CreateTime>1493532355481</CreateTime><MsgType><![CDATA[text]]></MsgType></xml>";
		
		XStream xStream = new XStream();
		xStream.alias("xml", TextMessage.class);

		TextMessage textMessage = (TextMessage)xStream.fromXML(xml);
		System.out.println(textMessage);
	}


}

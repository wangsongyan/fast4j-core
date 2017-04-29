/**
 * 
 */
package cn.wangsy.fast4j.core.wechat;

/**
 * 微信开发者认证参数封装
 * @author wangsy
 * @date 2017年4月29日下午2:19:40
 */
public class Wechat {

	// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String signature;
	// 时间戳
	private String timestamp;
	// 随机数
	private String nonce;
	// 随机字符串
	private String echostr;
	
	// setter && getter
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
}

/**
 * 
 */
package cn.wangsy.fast4j.core.wechat.message;

/**
 * 语音消息
 * 
 * @author wangsy
 * @date 2017年4月29日下午3:27:22
 */
public class VoiceMessage extends BaseMessage {

	private String MediaId;// 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Format;// 语音格式，如amr，speex等

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

}

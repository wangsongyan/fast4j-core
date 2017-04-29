/**
 * 
 */
package cn.wangsy.fast4j.core.wechat.message;

/**
 * 视频消息
 * 
 * @author wangsy
 * @date 2017年4月29日下午3:29:32
 */
public class VideoMessage extends BaseMessage {

	private String MediaId;// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}

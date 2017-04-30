/**
 * 
 */
package cn.wangsy.fast4j.core.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wangsy
 * @date 2017年4月29日下午2:16:23
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {

	private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void valid(Wechat wechat, PrintWriter out){
		
		String signature = wechat.getSignature();
		String timestamp = wechat.getTimestamp();
		String nonce = wechat.getNonce();
		String echostr = wechat.getEchostr();
		
		if(SignatureUtil.checkSignature(signature,timestamp,nonce)){
			out.print(echostr);
		}else{
			logger.error("校验失败，signature={}&timestamp={}&nonce={}",signature,timestamp,nonce);
		}
		out.flush();
		out.close();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void dispose(HttpServletRequest request, HttpServletResponse response)  
            throws IOException {  
        /* 消息的接收、处理、响应 */  
  
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        InputStream inputStream = request.getInputStream();
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 测试输出
        Set<String> keySet = map.keySet();
        // 测试输出解析后用户发过来的信息
        //System.out.println(TAG + "：解析用户发送过来的信息开始");
        for (String key : keySet) {
            System.out.println(key + ":" + map.get(key));
        }
        //System.out.println(TAG + "：解析用户发送过来的信息结束");
        
        switch (MsgType.valueOf(map.get("MsgType"))) {
		case TEXT:
			
			break;

		default:
			break;
		}
        
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print("");  
        out.close();  
    }  
}

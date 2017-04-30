package cn.wangsy.fast4j.core.wechat;

import java.io.InputStream;  
import java.io.Writer;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  



import javax.servlet.http.HttpServletRequest;  
  



import org.dom4j.Document;  
import org.dom4j.Element;  
import org.dom4j.io.SAXReader;  

import cn.wangsy.fast4j.core.wechat.message.BaseMessage;
import cn.wangsy.fast4j.core.wechat.message.ImageMessage;
import cn.wangsy.fast4j.core.wechat.message.TextMessage;

import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.core.util.QuickWriter;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;  
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;  
import com.thoughtworks.xstream.io.xml.XppDriver;  
  
/**  
 * 消息工具类  
 */  
public class MessageUtil {  
  
    /**  
     * 返回消息类型：文本  
     */  
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
  
    /**  
     * 文本消息对象转换成xml  
     * alias 起别名,如果没有,根节点将面为com.pacage.textMessage的形式  
     * @param textMessage 文本消息对象  
     * @return xml  
     */  
    public static String textMessageToXml(BaseMessage textMessage) {  
    	//System.err.println(textMessage.getClass().getName());
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }  
  
    public static void main(String[] args) {  
        TextMessage textMessage = new TextMessage();  
        textMessage.setToUserName("fromUser");  
        textMessage.setFromUserName("toUser");  
        textMessage.setCreateTime(new Date().getTime());  
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
        //textMessage.setFuncFlag(0);  
        String xml = MessageUtil.textMessageToXml(textMessage);  
        System.out.println(xml);  
        
        
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setToUserName("gf_12312asd");
        imageMessage.setFromUserName("wangsy");
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setMsgType(MsgType.IMAGE.value());
        imageMessage.setMediaId("assccsdasd");
        imageMessage.setPicUrl("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=971096182,415320934&fm=58");
        System.out.println(MessageUtil.textMessageToXml(imageMessage));
        
        MsgType.valueOf("type");
    }  
      
    /**  
     * 扩展xstream，使其支持CDATA块  
     */  
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
                String createTime = "";  
                @SuppressWarnings("unchecked")  
                public void startNode(String name, Class clazz) {  
                    if(name!=null&&name.equals("CreateTime")){  
                        createTime = "CreateTime";  
                    }else{  
                        createTime=name;  
                    }  
                    super.startNode(name, clazz);  
                }  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata&&!createTime.equals("CreateTime")) {  
                        writer.write("<![CDATA["); 
                        writer.write(text); 
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });  
} 

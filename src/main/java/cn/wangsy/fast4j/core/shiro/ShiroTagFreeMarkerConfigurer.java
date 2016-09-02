package cn.wangsy.fast4j.core.shiro;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/** 
 * 自定义一个ShiroTagFreeMarkerConfigurer继承Spring本身提供的FreeMarkerConfigurer,目的是在FreeMarker的Configuration中添加shiro的配置
 * @author wangsy
 * @date 创建时间：2016年7月31日 下午10:19:57
 * @version 1.0
 */
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer{
	
	@Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
	
}

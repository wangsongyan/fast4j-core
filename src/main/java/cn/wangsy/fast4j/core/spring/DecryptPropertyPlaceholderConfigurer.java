/**
 * 
 */
package cn.wangsy.fast4j.core.spring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author wangsy
 * @date 2018年1月11日上午9:39:52
 */
public abstract class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static Pattern encryptedPattern = Pattern.compile("Encrypted:\\{((\\w|\\-)*)\\}");  //加密属性特征正则
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		Matcher matcher = encryptedPattern.matcher(propertyValue);
		if(matcher.matches()){
			String encryptedString = matcher.group(1);    //获得加密值
            String decryptedPropValue = decrypt(encryptedString);

            if (decryptedPropValue != null) {  //!=null说明正常
                propertyValue = decryptedPropValue; //设置解决后的值
            } else {//说明解密失败
                logger.error("Decrypt " + propertyName + "=" + propertyValue + " error!");
            }
		}
		return super.convertProperty(propertyName, propertyValue);
	}

	/***
	 * 
	 * @param encryptedStr 加密后的字符串
	 * @return
	 */
	public abstract String decrypt(String encryptedStr);
	
}

/**
 * 
 */
package cn.wangsy.fast4j.core.oauth2.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;

/**
 * @author wangsy
 * @date 2016年11月15日下午3:17:32
 */
public class SinaWeiboApi extends DefaultApi20{

    private static final String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize?client_id=%s&response_type=code&redirect_uri=%s";
    private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";
    private static final String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token?grant_type=authorization_code";

    public SinaWeiboApi(){
    	
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        if (config.hasScope()){
          return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), OAuthEncoder.encode(config.getScope()));
        }else{
          return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
        }
    }

    @Override
    public String getAccessTokenEndpoint() {
        return String.format(ACCESS_TOKEN_URL);
    }

}

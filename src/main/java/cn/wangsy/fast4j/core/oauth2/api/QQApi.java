/**
 * 
 */
package cn.wangsy.fast4j.core.oauth2.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;

/**
 * @author wangsy
 * @date 2016年11月15日下午3:11:14
 */
public class QQApi extends DefaultApi20 {
    
    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=%s";
    private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";
    private static final String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&state=%s";
    
    private final String qqState;
    
    public QQApi(String state){
        this.qqState = state;
    }
    
    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        if (config.hasScope()){
          return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), 
                  qqState, OAuthEncoder.encode(config.getScope()));
        }
        else{
          return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), qqState);
        }
    }

    @Override
    public String getAccessTokenEndpoint() {
        return String.format(ACCESS_TOKEN_URL, qqState);
    }

}

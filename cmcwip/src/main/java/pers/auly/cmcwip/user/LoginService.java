package pers.auly.cmcwip.user;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pers.auly.cmcwip.properties.WxApiProps;
import pers.auly.cmcwip.utils.JsonUtil;
import pers.auly.cmcwip.utils.RequestUtils;

@Slf4j
@Service
class LoginService {
    
    private final WxApiProps wxApiProps;
    
    @Autowired
    public LoginService(WxApiProps wxApiProps) {
        this.wxApiProps = wxApiProps;
    }
    
    boolean signCheck(String sign, String rawData, String key) {
        return sign.equals(DigestUtils.sha1Hex(rawData + key));
    }
    
    /**
     * Http GET from wechat server 'code2session' api. Result json performs like:
     * <blockquote><pre>
     * {
     *   "session_key":"F\/fKVI9+oj6fxK5nwhBKMg==",
     *   "openid":"o_PnH5RjJzfHmmZD5MwPhPfE4EsQ"
     * }
     * </pre></blockquote>
     *
     * @param code the specific code from mini app
     * @return result json message
     */
    WxLoginVo wechatLogin(String code) {
        String apiUrl = "sns/jscode2session";
        MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();
        payload.add("appid", wxApiProps.getAppId());
        payload.add("secret", wxApiProps.getSecret());
        payload.add("js_code", code);
        payload.add("grant_type", "authorization_code");
        
        String result = RequestUtils.openApiGet(apiUrl, payload);
        try {
            return JsonUtil.string2Pojo(result, WxLoginVo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}

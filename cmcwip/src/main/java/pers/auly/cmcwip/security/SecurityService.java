package pers.auly.cmcwip.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pers.auly.cmcwip.exceptions.CmcWebException;
import pers.auly.cmcwip.exceptions.UserNotFoundException;
import pers.auly.cmcwip.user.UserRepository;
import pers.auly.cmcwip.utils.RequestUtils;

@Slf4j
@Service
class SecurityService {
    
    private final ObjectMapper mapper;
    private final WxApiProps wxApiProps;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    
    public SecurityService(ObjectMapper mapper, WxApiProps wxApiProps,
        UserRepository userRepository, TokenRepository tokenRepository) {
        this.mapper = mapper;
        this.wxApiProps = wxApiProps;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
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
    private WxLoginVo wechatLogin(String code) {
        String apiUrl = "sns/jscode2session";
        MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();
        payload.add("appid", wxApiProps.getAppId());
        payload.add("secret", wxApiProps.getSecret());
        payload.add("js_code", code);
        payload.add("grant_type", "authorization_code");
        
        String result = RequestUtils.openApiGet(apiUrl, payload);
        try {
            return mapper.readValue(result, WxLoginVo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private boolean signCheck(String sign, String rawData, String key) {
        return sign.equals(DigestUtils.sha1Hex(rawData + key));
    }
    
    WmaLoginToken userLogin(String code, String sign, String rawData) {
        return tokenRepository.findById(code)
            .orElseGet(() -> {
                WxLoginVo wxLoginVo = wechatLogin(code);
                if (!signCheck(sign, rawData, wxLoginVo.getSessionKey())) {
                    throw new CmcWebException("Illegal sign ");
                }
                return userRepository.findByOpenId(wxLoginVo.getOpenId())
                    .map(user -> {
                        tokenRepository.deleteByUser(user);
                        return tokenRepository.save(new WmaLoginToken(code, user));
                    })
                    .orElseThrow(UserNotFoundException::new);
            });
        
    }
}

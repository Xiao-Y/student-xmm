package org.billow.utils.pay.wechat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 微信支付配置
 *
 * @author liuyongtao
 * @create 2017-12-26 11:23
 */
@Service
public class WeChatPayApiConfig {


    private static String weichatUrl;
    public static String appid;
    public static String appsecret;
    public static String mchId;
    public static String spbillCreateIp;
    public static String key;

    @Value("{weichat.key}")
    public void setKey(String key) {
        WeChatPayApiConfig.key = key;
    }

    @Value("{weichat.spbill_create_ip}")
    public void setSpbillCreateIp(String spbillCreateIp) {
        WeChatPayApiConfig.spbillCreateIp = spbillCreateIp;
    }

    @Value("{weichat.mch_id}")
    public void setMchId(String mchId) {
        WeChatPayApiConfig.mchId = mchId;
    }

    @Value("{weichat.url}")
    public void setWeichatUrl(String weichatUrl) {
        WeChatPayApiConfig.weichatUrl = weichatUrl;
    }

    @Value("${weichat.appid}")
    public void setAppid(String appid) {
        WeChatPayApiConfig.appid = appid;
    }

    @Value("${weichat.appsecret}")
    public void setAppsecret(String appsecret) {
        WeChatPayApiConfig.appsecret = appsecret;
    }

    public String getWeichatUrl() {
        return weichatUrl;
    }

    public String getAppid() {
        return appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getMchId() {
        return mchId;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public String getKey() {
        return key;
    }
}

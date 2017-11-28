package org.billow.utils.pay.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Javen
 * 2017年5月20日
 */
@Component
public class AliPayApiConfig {

    @Value("${alipay.privateKey}")
    private String privateKey;
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;
    @Value("${alipay.appId}")
    private String appId;
    @Value("${alipay.url}")
    private String serviceUrl;
    @Value("${alipay.charset}")
    private String charset;
    @Value("${alipay.signType}")
    private String signType;
    @Value("${alipay.format}")
    private String format;

    private AlipayClient alipayClient;

    public AliPayApiConfig build() {
        this.alipayClient = new DefaultAlipayClient(getServiceUrl(), getAppId(), getPrivateKey(), getFormat(), getCharset(), getAlipayPublicKey(), getSignType());
        return this;
    }

    public String getPrivateKey() {
        if (ToolsUtils.isEmpty(privateKey))
            throw new IllegalStateException("privateKey 未被赋值");
        return privateKey;
    }

    public AliPayApiConfig setPrivateKey(String privateKey) {
        if (ToolsUtils.isEmpty(privateKey))
            throw new IllegalArgumentException("privateKey 值不能为 null");
        this.privateKey = privateKey;
        return this;
    }

    public String getAlipayPublicKey() {
        if (ToolsUtils.isEmpty(alipayPublicKey))
            throw new IllegalStateException("alipayPublicKey 未被赋值");
        return alipayPublicKey;
    }

    public AliPayApiConfig setAlipayPublicKey(String alipayPublicKey) {
        if (ToolsUtils.isEmpty(alipayPublicKey))
            throw new IllegalArgumentException("alipayPublicKey 值不能为 null");
        this.alipayPublicKey = alipayPublicKey;
        return this;
    }

    public String getAppId() {
        if (ToolsUtils.isEmpty(appId))
            throw new IllegalStateException("appId 未被赋值");
        return appId;
    }

    public AliPayApiConfig setAppId(String appId) {
        if (ToolsUtils.isEmpty(appId))
            throw new IllegalArgumentException("appId 值不能为 null");
        this.appId = appId;
        return this;
    }

    public String getServiceUrl() {
        if (ToolsUtils.isEmpty(serviceUrl))
            throw new IllegalStateException("serviceUrl 未被赋值");
        return serviceUrl;
    }


    public AliPayApiConfig setServiceUrl(String serviceUrl) {
        if (ToolsUtils.isEmpty(serviceUrl))
            serviceUrl = "https://openapi.alipay.com/gateway.do";
        this.serviceUrl = serviceUrl;
        return this;
    }

    public String getCharset() {
        if (ToolsUtils.isEmpty(charset))
            charset = "UTF-8";
        return charset;
    }

    public AliPayApiConfig setCharset(String charset) {
        if (ToolsUtils.isEmpty(charset))
            charset = "UTF-8";
        this.charset = charset;
        return this;
    }

    public String getSignType() {
        if (ToolsUtils.isEmpty(signType))
            signType = "RSA2";
        return signType;
    }

    public AliPayApiConfig setSignType(String signType) {
        if (ToolsUtils.isEmpty(signType))
            signType = "RSA2";
        this.signType = signType;
        return this;
    }

    public String getFormat() {
        if (ToolsUtils.isEmpty(format))
            format = "json";
        return format;
    }

    public AlipayClient getAlipayClient() {
        if (alipayClient == null)
            throw new IllegalStateException("alipayClient 未被初始化");
        return alipayClient;
    }

}

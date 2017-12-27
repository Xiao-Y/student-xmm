package org.billow.utils.pay.wechat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.billow.utils.HttpRequest;
import org.billow.utils.generator.UUID;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信支付
 *
 * @author liuyongtao
 * @create 2017-12-26 10:31
 */
@Service
public class WeChatPayApi {

    private static WeChatPayApiConfig weChatPayApiConfig;

    @Autowired
    public void setWeChatPayApiConfig(WeChatPayApiConfig weChatPayApiConfig) {
        WeChatPayApi.weChatPayApiConfig = weChatPayApiConfig;
    }


    /**
     * 统一下单
     *
     * @param jsCode   用户登陆状态
     * @param orderId  订单号
     * @param totalFee 订单金额
     * @return
     * @throws Exception
     */
    public static Map<String, String> unifiedOrder(String jsCode, String orderId, BigDecimal totalFee) throws Exception {
        //获取用户的opendId和SessionKey
        Map<String, String> openIdAndSessionKey = getOpenIdAndSessionKey(jsCode);

        String body = null;
        body = new String(body.getBytes("UTF-8"), "ISO-8859-1");
        String appid = weChatPayApiConfig.getAppid();//小程序ID
        String mch_id = weChatPayApiConfig.getMchId();//商户号
        String nonce_str = UUID.generate();//随机字符串
        String out_trade_no = orderId;//商户订单号
        String spbill_create_ip = weChatPayApiConfig.getSpbillCreateIp();//终端IP
        String notify_url = "http://www.weixin.qq.com/mb/weChatPay/notifyResult";//通知地址
        String trade_type = "JSAPI";//交易类型
        String openid = openIdAndSessionKey.get("openid");//用户标识
        String url = weChatPayApiConfig.getWeichatUrl();//统一下单API接口链接
        String key = weChatPayApiConfig.getKey();// 商户支付密钥

        WeChatPayDto paymentPo = new WeChatPayDto();
        paymentPo.setAppid(appid);
        paymentPo.setMch_id(mch_id);
        paymentPo.setNonce_str(nonce_str);
        // 以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
        String newbody = new String(body.getBytes("ISO-8859-1"), "UTF-8");
        paymentPo.setBody(newbody);
        paymentPo.setOut_trade_no(out_trade_no);
        paymentPo.setTotal_fee(totalFee.toString());
        paymentPo.setSpbill_create_ip(spbill_create_ip);
        paymentPo.setNotify_url(notify_url);
        paymentPo.setTrade_type(trade_type);
        paymentPo.setOpenid(openid);
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<>();
        sParaTemp.put("appid", paymentPo.getAppid());
        sParaTemp.put("mch_id", paymentPo.getMch_id());
        sParaTemp.put("nonce_str", paymentPo.getNonce_str());
        sParaTemp.put("body", paymentPo.getBody());
        sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
        sParaTemp.put("total_fee", paymentPo.getTotal_fee());
        sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
        sParaTemp.put("notify_url", paymentPo.getNotify_url());
        sParaTemp.put("trade_type", paymentPo.getTrade_type());
        sParaTemp.put("openid", paymentPo.getOpenid());
        // 除去数组中的空值和签名参数
        Map sPara = PayUtil.paraFilter(sParaTemp);
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String prestr = PayUtil.createLinkString(sPara);
        //MD5运算生成签名
        String mysign = PayUtil.sign(prestr, "&key=" + key, "utf-8").toUpperCase();
        paymentPo.setSign(mysign);
        //打包要发送的xml
        String respXml = MessageUtil.messageToXML(paymentPo);
        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
        respXml = respXml.replace("__", "_");
        String param = respXml;
        String result = HttpRequest.sendPost(url, param);
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap();
        InputStream in = new ByteArrayInputStream(result.getBytes());
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        for (Element element : elementList) {
            map.put(element.getName(), element.getText());
        }
        // 返回信息
        String return_code = map.get("return_code");//返回状态码
        String return_msg = map.get("return_msg");//返回信息
        System.out.println("return_msg" + return_msg);
        Map<String, String> JsonObject = new HashMap<>();
        if ("SUCCESS".equals(return_code)) {
            // 业务结果
            String prepay_id = map.get("prepay_id");//返回的预付单信息
            String nonceStr = UUID.generate();
            JsonObject.put("nonceStr", nonceStr);
            JsonObject.put("package", "prepay_id=" + prepay_id);
            Long timeStamp = System.currentTimeMillis() / 1000;
            JsonObject.put("timeStamp", timeStamp + "");
            String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id
                    + "&signType=MD5&timeStamp=" + timeStamp;
            //再次签名
            String paySign = PayUtil.sign(stringSignTemp, "&key=" + key, "utf-8").toUpperCase();
            JsonObject.put("paySign", paySign);
        }
        return JsonObject;
    }

    /**
     * 获取用户的opendId和SessionKey
     *
     * @param code
     * @return
     */
    private static Map<String, String> getOpenIdAndSessionKey(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String param = "appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        param = param.replace("APPID", weChatPayApiConfig.getAppid());
        param = param.replace("SECRET", weChatPayApiConfig.getAppsecret());
        param = param.replace("JSCODE", code);
        //发请求到微信
        String response = HttpRequest.sendGet(url, param);
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(response, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }
}

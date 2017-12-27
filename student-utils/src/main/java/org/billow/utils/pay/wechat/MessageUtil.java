package org.billow.utils.pay.wechat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建微信支付的XML
 *
 * @author liuyongtao
 * @create 2017-12-26 15:10
 */
public class MessageUtil {

    public static Map<String, String> parseXML(HttpServletRequest request) throws Exception, IOException {
        Map<String, String> map = new HashMap<>();
        // 通过IO获得Document
        SAXReader reader = new SAXReader();
        Document doc = reader.read(request.getInputStream());
        //得到xml的根节点
        Element root = doc.getRootElement();
        recursiveParseXML(root, map);
        return map;
    }

    private static void recursiveParseXML(Element root, Map<String, String> map) {
        //得到根节点的子节点列表
        List<Element> elementList = root.elements();
        //判断有没有子元素列表
        if (elementList.size() == 0) {
            map.put(root.getName(), root.getTextTrim());
        } else {
            //遍历
            for (Element e : elementList) {
                recursiveParseXML(e, map);
            }
        }
    }

    /**
     * 对象转xml文件
     *
     * @param b
     * @return
     */
    public static Document getDocument(Object b) {
        Document document = DocumentHelper.createDocument();
        try {
            // 创建根节点元素
            Element root = document.addElement(b.getClass().getSimpleName());
            Field[] field = b.getClass().getDeclaredFields(); // 获取实体类b的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { // 遍历所有有属性
                String name = field[j].getName(); // 获取属属性的名字
                if (!name.equals("serialVersionUID")) {//去除串行化序列属性
                    name = name.substring(0, 1).toUpperCase()
                            + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    Method m = b.getClass().getMethod("get" + name);
                    String propertievalue = (String) m.invoke(b);// 获取属性值
                    Element propertie = root.addElement(name);
                    propertie.setText(propertievalue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 对象转xml格式的字符串
     *
     * @param b
     * @return
     */
    public static String messageToXML(Object b) {
        String asXML = getDocument(b).asXML();
        System.out.println(asXML);
        return asXML;
    }
}

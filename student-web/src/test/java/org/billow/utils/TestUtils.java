package org.billow.utils;

import org.billow.utils.bean.BeanUtils;
import org.billow.utils.exception.ActiveMQException;

import java.util.Date;

public class TestUtils {

    public static void main(String[] args) throws Exception {
        /*try {
            BeanUtils.getBean("111");
		} catch (Exception e) {
			throw new ActiveMQException();
		}
		System.out.println(22222);*/
//        long time1 = new Date().getTime();
//        System.out.println(time1);
//        Thread.sleep(2000);
//        long time2 = new Date().getTime();
//        System.out.println(time2);
//        System.out.println(time2 - time1);
        System.out.println(ToolsUtils.splitTextData("1000 * 60 * 24"));
    }
}

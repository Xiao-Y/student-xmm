package org.billow.queue.test1;


import java.util.concurrent.DelayQueue;

/**
 * 测试
 *
 * @author liuyongtao
 * @create 2017-12-01 10:46
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        TaskQueueDaemonThread instance = TaskQueueDaemonThread.getInstance();
        instance.init();

        boolean flag = false;
        for (int i = 0; i < 10; i++) {
            Thread.sleep(900);
            String orderFormId = "222" + i;
            new OrderFormTaskQueue("123213", 10000L, orderFormId);
            DelayQueue<Task> delayQueueTask = instance.getDelayQueueTask();
            System.out.println("be...." + delayQueueTask.size());
        }

        new OrderFormTaskQueue("123213", "2223");
        new OrderFormTaskQueue("123213", "2226");
        new OrderFormTaskQueue("123213", "2228");

        DelayQueue<Task> delayQueueTask = instance.getDelayQueueTask();
        System.out.println("af...." + delayQueueTask.size());
        Thread.sleep(2000000);
    }
}

package org.billow.queue.test3;


import java.util.concurrent.DelayQueue;

/**
 * 测试
 *
 * @author liuyongtao
 * @create 2017-12-01 10:46
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        String typeName = "123";
        TaskQueueDaemonThread instance = TaskQueueDaemonThread.getInstance();
        instance.init();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            String orderFormId = "222" + i;
            OrderFormTaskQueue orderFormTaskQueue = new OrderFormTaskQueue();
            orderFormTaskQueue.setOrderFormId(orderFormId);
            orderFormTaskQueue.setTypeName(typeName);
            orderFormTaskQueue.setOrderFormAutoTime(3000L);
            orderFormTaskQueue.putOrderFormTask();

            DelayQueue<Task> delayQueueTask = instance.getDelayQueueTask();
            System.out.println("be...." + delayQueueTask.size());
        }

        OrderFormTaskQueue orderFormTaskQueue = new OrderFormTaskQueue();
        orderFormTaskQueue.setOrderFormId("2223");
        orderFormTaskQueue.setTypeName(typeName);
        orderFormTaskQueue.endOrderFormTask();

        System.out.println("=======================================================");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            String orderFormId = "333" + i;
            new OrderFormTaskQueue(typeName, 4000L, orderFormId).putOrderFormTask();

            DelayQueue<Task> delayQueueTask = instance.getDelayQueueTask();
            System.out.println("be...." + delayQueueTask.size());
        }

        new OrderFormTaskQueue(typeName, "3335").endOrderFormTask();

        DelayQueue<Task> delayQueueTask = instance.getDelayQueueTask();
        System.out.println("af...." + delayQueueTask.size());
        Thread.sleep(200000);
    }
}

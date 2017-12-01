package org.billow.common.queues;

import org.billow.common.queues.Task.TaskQueueDaemonThread;

/**
 * 测试
 *
 * @author liuyongtao
 * @create 2017-12-01 10:46
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        TaskQueueDaemonThread.getInstance().init();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            String orderFormId = "222" + i;
            new Thread(new OrderFormTaskQueue(orderFormId)).start();
        }
        Thread.sleep(2000000);
    }
}

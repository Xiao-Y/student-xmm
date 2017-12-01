package org.billow.common.queues.Task;

import org.apache.log4j.Logger;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * [任务调度系统]
 * <br>
 * [后台守护线程不断的执行检测工作]
 * </p>
 *
 * @author liuyongtao
 * @create 2017-12-01 9:02
 */
public class TaskQueueDaemonThread {

    private static final Logger logger = Logger.getLogger(TaskQueueDaemonThread.class);

    private TaskQueueDaemonThread() {
    }

    private static class LazyHolder {
        private static TaskQueueDaemonThread taskQueueDaemonThread = new TaskQueueDaemonThread();
    }

    public static TaskQueueDaemonThread getInstance() {
        return LazyHolder.taskQueueDaemonThread;
    }

    Executor executor = Executors.newFixedThreadPool(20);
    /**
     * 守护线程
     */
    private Thread daemonThread;


    /**
     * 创建一个最初为空的新 DelayQueue<br/>
     * DelayQueue:是一个无界阻塞队列，只有在延迟期满时才能从中提取元素。该队列的头部是延迟期满后保存时间最长的Delayed 元素
     */
    private DelayQueue<Task> delayQueueTask = new DelayQueue<>();

    /**
     * 初始化守护线程
     */
    public void init() {
        //daemonThread = new Thread(() -> execute());
        daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                execute();
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.setName("Task Queue Daemon Thread");
        daemonThread.start();
    }

    private void execute() {
        logger.info("start:" + System.currentTimeMillis());
        while (true) {
            try {
                //从延迟队列中取值,如果没有对象过期则队列一直等待，
                Task t1 = delayQueueTask.take();
                if (t1 != null) {
                    //修改问题的状态
                    Runnable task = t1.getTask();
                    if (task == null) {
                        continue;
                    }
                    executor.execute(task);
                    logger.info(">>>>>>>> 自动处理任务:" + task);
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 添加任务，
     * time 延迟时间
     * task 任务
     * 用户为问题设置延迟时间
     */
    public void putTask(long time, Runnable task) {
        //转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS);
        //创建一个任务
        Task k = new Task(nanoTime, task);
        //将任务放在延迟的队列中
        delayQueueTask.put(k);
    }

    /**
     * 结束过期的对象
     *
     * @param task
     */
    public boolean endTask(Task<Runnable> task) {
        return delayQueueTask.remove(task);
    }

    /**
     * 获取此队列的头部(过期的对象)
     *
     * @return
     */
    public Task getTake() throws InterruptedException {
        return delayQueueTask.take();
    }
}

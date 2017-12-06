package org.billow.queue.test3;

import org.apache.log4j.Logger;


/**
 * 订单自动确认队列<br/>
 * PostConstruct-->afterPropertiesSet
 *
 * @author liuyongtao
 * @create 2017-12-01 9:20
 */
public class OrderFormTaskQueue implements Runnable {

    private static final Logger logger = Logger.getLogger(OrderFormTaskQueue.class);

    // 订单Id
    private String orderFormId;
    // 操作类型
    private String typeName;
    private Long orderFormAutoTime;
    private static final Long ORDER_FORM_AUTO_TIME = 3000L;

    public OrderFormTaskQueue() {
    }

    public OrderFormTaskQueue(String typeName, Long orderFormAutoTime, String orderFormId) {
        this.typeName = typeName;
        this.orderFormAutoTime = orderFormAutoTime;
        this.orderFormId = orderFormId;
    }

    public OrderFormTaskQueue(String typeName, String orderFormId) {
        this.typeName = typeName;
        this.orderFormId = orderFormId;
    }


    /**
     * 销毁任务
     */
    public void endOrderFormTask() {
        TaskQueueDaemonThread.getInstance().endTask(new Task(this));
        logger.info(">>>>>>>> 销毁任务:" + orderFormId);
    }

    /**
     * 添加新任务
     */
    public void putOrderFormTask() {
        this.orderFormAutoTime = this.getOrderFormAutoTime();
        if (this.orderFormAutoTime == null) {
            this.orderFormAutoTime = ORDER_FORM_AUTO_TIME;
        }
        TaskQueueDaemonThread.getInstance().putTask(orderFormAutoTime, this);
        logger.info("<<<<<<<< 添加新任务,业务号:" + orderFormId);
    }

    @Override
    public void run() {
        try {
            logger.info(">>>>>>>> 自动处理任务:" + this);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("订单自动确认失败：" + orderFormId);
        }
    }

    /**
     * 订单Id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public String getOrderFormId() {
        return this.orderFormId;
    }

    /**
     * 订单Id
     *
     * @param orderFormId
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setOrderFormId(String orderFormId) {
        this.orderFormId = orderFormId;
    }

    /**
     * 操作类型
     *
     * @return
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 操作类型
     *
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getOrderFormAutoTime() {
        return orderFormAutoTime;
    }

    public void setOrderFormAutoTime(Long orderFormAutoTime) {
        this.orderFormAutoTime = orderFormAutoTime;
    }

    @Override
    public String toString() {
        return "OrderFormTaskQueue{" +
                "orderFormId='" + orderFormId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", orderFormAutoTime=" + orderFormAutoTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderFormTaskQueue that = (OrderFormTaskQueue) o;

        if (orderFormId != null ? !orderFormId.equals(that.orderFormId) : that.orderFormId != null) return false;
        return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;
    }

    @Override
    public int hashCode() {
        int result = orderFormId != null ? orderFormId.hashCode() : 0;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }
}

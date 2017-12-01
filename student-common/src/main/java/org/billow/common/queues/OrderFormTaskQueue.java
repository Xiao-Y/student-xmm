package org.billow.common.queues;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.queues.Task.Task;
import org.billow.common.queues.Task.TaskQueueDaemonThread;
import org.billow.model.expand.OrderFormDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.enumType.PayEunm;
import org.billow.utils.property.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单自动确认队列<br/>
 * PostConstruct-->afterPropertiesSet
 *
 * @author liuyongtao
 * @create 2017-12-01 9:20
 */
public class OrderFormTaskQueue implements Runnable {

    private static final Logger logger = Logger.getLogger(OrderFormTaskQueue.class);

    @Autowired
    private OrderFormService orderFormService;

    // 订单Id
    private String orderFormId;

    public OrderFormTaskQueue(String orderFormId, long orderFormAutoTime) {
        boolean orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
        if (!orderFormAutoFlag) return;

        this.orderFormId = orderFormId;
        TaskQueueDaemonThread.getInstance().putTask(orderFormAutoTime, this);
        logger.info("<<<<<<<< 添加新任务,业务号:" + orderFormId);
    }

    public OrderFormTaskQueue(String orderFormId) {
        boolean orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
        if (!orderFormAutoFlag) return;
        String orderFormAutoTime = PropertyUtil.getProperty("orderForm.auto.time");

        this.orderFormId = orderFormId;
        long autoTime = ToolsUtils.splitTextData(orderFormAutoTime);
        TaskQueueDaemonThread.getInstance().putTask(autoTime, this);
        logger.info("<<<<<<<< 添加新任务,业务号:" + orderFormId);
    }

    @Override
    public void run() {
        try {
            boolean orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
            if (!orderFormAutoFlag) return;

            Task take = TaskQueueDaemonThread.getInstance().getTake();
            OrderFormTaskQueue orderFormTaskQueue = (OrderFormTaskQueue) take.getTask();
            TaskQueueDaemonThread.getInstance().endTask(take);

            OrderFormDto dto = new OrderFormDto();
            dto.setId(orderFormTaskQueue.getOrderFormId());
            dto.setStatus(PayEunm.BUSINESS_CONFIRMATION.getStatus());
            OrderFormService orderFormService = BeanUtils.getBean("orderFormServiceImpl");
            orderFormService.updateByPrimaryKeySelective(dto);

            logger.info(">>>>>>>> 自动处理任务:" + orderFormTaskQueue);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("订单自动确认失败：" + orderFormId);
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public String toString() {
        return "OrderFormTaskQueue{" +
                "orderFormId='" + orderFormId + '\'' +
                '}';
    }
}

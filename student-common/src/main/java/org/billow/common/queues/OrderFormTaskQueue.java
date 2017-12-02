package org.billow.common.queues;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.queues.Task.TaskQueueDaemonThread;
import org.billow.model.expand.OrderFormDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.enumType.PayEunm;
import org.billow.utils.property.PropertyUtil;

/**
 * 订单自动确认队列<br/>
 *
 * @author liuyongtao
 * @create 2017-12-01 9:20
 */
public class OrderFormTaskQueue implements Runnable {

    private static final Logger logger = Logger.getLogger(OrderFormTaskQueue.class);

    // 订单Id
    private String orderFormId;

    /**
     * 用于指定延时时间的构造方法
     *
     * @param orderFormId       订单号
     * @param orderFormAutoTime 延时时间
     */
    public OrderFormTaskQueue(String orderFormId, long orderFormAutoTime) {
        //是否开启自动确认
        boolean orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
        if (!orderFormAutoFlag) return;

        this.orderFormId = orderFormId;
        TaskQueueDaemonThread.getInstance().putTask(orderFormAutoTime, this);
        logger.debug("<<<<<<<< 添加新订单自动确认任务,业务号:" + orderFormId + "，延时：" + orderFormAutoTime);
    }

    /**
     * 使用默认延时时间的构造方法
     *
     * @param orderFormId 订单号
     */
    public OrderFormTaskQueue(String orderFormId) {
        //是否开启自动确认
        boolean orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
        if (!orderFormAutoFlag) return;
        //订单自动确认时间
        String orderFormAutoTime = PropertyUtil.getProperty("orderForm.auto.time");

        this.orderFormId = orderFormId;
        long autoTime = ToolsUtils.splitTextData(orderFormAutoTime);
        TaskQueueDaemonThread.getInstance().putTask(autoTime, this);
        logger.debug("<<<<<<<< 添加新订单自动确认任务,业务号:" + orderFormId + "，延时：" + orderFormAutoTime);
    }

    @Override
    public void run() {
        //是否开启自动确认
        boolean orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
        if (!orderFormAutoFlag) return;
        try {
            //处理业务逻辑
            OrderFormService orderFormService = BeanUtils.getBean("orderFormServiceImpl");
            OrderFormDto dto = new OrderFormDto();
            dto.setId(orderFormId);
            OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(dto);
            if (orderFormDto != null) {
                if (PayEunm.TRADE_SUCCESS.getStatus().equals(orderFormDto.getStatus())) {
                    dto.setStatus(PayEunm.BUSINESS_CONFIRMATION.getStatus());
                    orderFormService.updateByPrimaryKeySelective(dto);
                    logger.debug(">>>>>>>> 处理订单自动确认任务:" + orderFormId);
                } else {
                    logger.debug(">>>>>>>> 处理订单自动确认任务:" + orderFormId + "，[" + PayEunm.getName(orderFormDto.getStatus()) + "]订单状态不对...");
                }
            } else {
                logger.debug(">>>>>>>> 处理订单自动确认任务:" + orderFormId + "，订单不存在...");
            }
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

    @Override
    public String toString() {
        return "OrderFormTaskQueue{orderFormId='" + orderFormId + "'}";
    }
}

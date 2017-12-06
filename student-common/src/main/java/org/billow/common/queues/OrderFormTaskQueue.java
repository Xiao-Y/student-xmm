package org.billow.common.queues;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.queues.Task.Task;
import org.billow.common.queues.Task.TaskQueueDaemonThread;
import org.billow.model.expand.OrderFormDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.enumType.OrderFormTaskQueueEunm;
import org.billow.utils.enumType.PayStatusEunm;
import org.billow.utils.property.PropertyUtil;

/**
 * 订单操作队列<br/>
 *
 * @author liuyongtao
 * @create 2017-12-01 9:20
 */
public class OrderFormTaskQueue implements Runnable {

    private static final Logger logger = Logger.getLogger(OrderFormTaskQueue.class);

    // 订单Id
    private String orderFormId;
    // 操作类型
    private String typeCode;
    // 延时时间
    private Long orderFormAutoTime;
    //是否开启自动确认
    private boolean orderFormAutoFlag;

    public OrderFormTaskQueue() {
        //是否开启自动确认
        this.orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
    }

    /**
     * 用于指定延时时间的构造方法（使用默认延时时间）
     *
     * @param typeCode    操作类型
     * @param orderFormId 订单号
     */
    public OrderFormTaskQueue(String typeCode, String orderFormId) {
        this.typeCode = typeCode;
        this.orderFormId = orderFormId;
        //是否开启自动确认
        this.orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
    }

    /**
     * 用于指定延时时间的构造方法
     *
     * @param typeCode          操作类型
     * @param orderFormId       订单号
     * @param orderFormAutoTime 延时时间
     */
    public OrderFormTaskQueue(String typeCode, Long orderFormAutoTime, String orderFormId) {
        this.typeCode = typeCode;
        this.orderFormAutoTime = orderFormAutoTime;
        this.orderFormId = orderFormId;
        //是否开启自动确认
        this.orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.flag");
    }

    /**
     * 销毁任务
     */
    public void endOrderFormTask() {
        //是否开启自动确认
        if (!orderFormAutoFlag) return;
        TaskQueueDaemonThread.getInstance().endTask(new Task(this));
        logger.info(">>>>>>>> 销毁[" + OrderFormTaskQueueEunm.getTypeName(this.typeCode) + "]任务,业务号:" + orderFormId);
    }

    /**
     * 添加新任务
     */
    public void putOrderFormTask() {
        //是否开启自动确认
        if (!orderFormAutoFlag) return;
        this.orderFormAutoTime = this.getOrderFormAutoTime();
        if (this.orderFormAutoTime == null) {
            //订单自动确认时间
            if (OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode().equals(this.typeCode)) {
                this.orderFormAutoTime = ToolsUtils.splitTextData(PropertyUtil.getProperty("orderForm.auto.time"));
            }
        }
        if (this.orderFormAutoTime == null) {
            throw new RuntimeException("延时消息队列中延时时间不能为空！");
        }
        TaskQueueDaemonThread.getInstance().putTask(orderFormAutoTime, this);
        logger.info("<<<<<<<< 添加[" + OrderFormTaskQueueEunm.getTypeName(this.typeCode) + "]任务,业务号:" + orderFormId + "，延时：" + orderFormAutoTime);
    }

    @Override
    public void run() {
        //是否开启自动确认
        if (!orderFormAutoFlag) return;
        //处理业务逻辑
        try {
            if (OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode().equals(this.typeCode)) {
                this.autoOrderFormConfirmation();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[" + OrderFormTaskQueueEunm.getTypeName(this.typeCode) + "]失败：" + orderFormId);
        }
    }

    /**
     * 订单自动确认
     */
    private void autoOrderFormConfirmation() throws Exception {
        OrderFormService orderFormService = BeanUtils.getBean("orderFormServiceImpl");
        OrderFormDto dto = new OrderFormDto();
        dto.setId(orderFormId);
        OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(dto);
        if (orderFormDto != null) {
            if (PayStatusEunm.TRADE_SUCCESS.getStatus().equals(orderFormDto.getStatus())) {
                dto.setStatus(PayStatusEunm.BUSINESS_CONFIRMATION.getStatus());
                orderFormService.updateByPrimaryKeySelective(dto);
                logger.debug(">>>>>>>> 处理订单自动确认任务:" + orderFormId);
            } else {
                logger.debug(">>>>>>>> 处理订单自动确认任务:" + orderFormId + "，[" + PayStatusEunm.getNameByStatus(orderFormDto.getStatus()) + "]订单状态不对...");
            }
        } else {
            logger.debug(">>>>>>>> 处理订单自动确认任务:" + orderFormId + "，订单不存在...");
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
     * @see OrderFormTaskQueueEunm
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 操作类型
     *
     * @param typeCode
     * @see OrderFormTaskQueueEunm
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 延时时间
     *
     * @return
     */
    public Long getOrderFormAutoTime() {
        return orderFormAutoTime;
    }

    /**
     * 延时时间
     *
     * @param orderFormAutoTime
     */
    public void setOrderFormAutoTime(Long orderFormAutoTime) {
        this.orderFormAutoTime = orderFormAutoTime;
    }

    @Override
    public String toString() {
        return "OrderFormTaskQueue{" +
                "orderFormId='" + orderFormId + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", orderFormAutoTime=" + orderFormAutoTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderFormTaskQueue that = (OrderFormTaskQueue) o;

        if (orderFormId != null ? !orderFormId.equals(that.orderFormId) : that.orderFormId != null) return false;
        return typeCode != null ? typeCode.equals(that.typeCode) : that.typeCode == null;
    }

    @Override
    public int hashCode() {
        int result = orderFormId != null ? orderFormId.hashCode() : 0;
        result = 31 * result + (typeCode != null ? typeCode.hashCode() : 0);
        return result;
    }
}

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

import java.util.Date;

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
    private boolean orderFormAutoFlag;

    public OrderFormTaskQueue(String typeCode) {
        this.typeCode = typeCode;
        this.init();
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
        this.init();
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
        this.init();
    }

    public void init() {
        //订单自动确认
        if (OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode().equals(this.typeCode)) {
            //是否开启自动确认
            this.orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.confirm.flag");
            //订单自动确认时间
            String autoTime = PropertyUtil.getProperty("orderForm.auto.confirm.time");
            if (ToolsUtils.isNotEmpty(autoTime) && this.orderFormAutoTime == null) {
                this.orderFormAutoTime = ToolsUtils.splitTextData(autoTime);
            }
        } else if (OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONSIGNMENT.getTypeCode().equals(this.typeCode)) {
            //是否开启自动确认
            this.orderFormAutoFlag = PropertyUtil.getPropertyBoolean("orderForm.auto.consignment.flag");
            //订单自动确认时间
            String autoTime = PropertyUtil.getProperty("orderForm.auto.consignment.time");
            if (ToolsUtils.isNotEmpty(autoTime) && this.orderFormAutoTime == null) {
                this.orderFormAutoTime = ToolsUtils.splitTextData(autoTime);
            }
        }
    }

    /**
     * 添加新任务
     */
    public void putOrderFormTask() {
        //是否开启自动确认
        if (!orderFormAutoFlag) return;
        //订单自动确认时间
        if (this.orderFormAutoTime == null) {
            this.orderFormAutoTime = this.getOrderFormAutoTime();
        }
        if (this.orderFormAutoTime == null) {
            throw new RuntimeException("延时消息队列中延时时间不能为空！");
        }
        TaskQueueDaemonThread.getInstance().putTask(orderFormAutoTime, this);
        logger.info("<<<<<<<< 添加[" + OrderFormTaskQueueEunm.getTypeName(this.typeCode) + "]任务,业务号:" + orderFormId + "，延时：" + orderFormAutoTime);
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

    @Override
    public void run() {
        //是否开启自动确认
        if (!orderFormAutoFlag) return;
        //处理业务逻辑
        try {
            if (OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode().equals(this.typeCode)) {
                this.autoOrderFormConfirmation();
            } else if (OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONSIGNMENT.getTypeCode().equals(this.typeCode)) {
                this.autoOrderFormConsignment();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("处理[" + OrderFormTaskQueueEunm.getTypeName(this.typeCode) + "]失败：" + orderFormId);
        }
    }

    /**
     * 订单自动发货中
     */
    private void autoOrderFormConsignment() throws Exception {
        OrderFormService orderFormService = BeanUtils.getBean("orderFormServiceImpl");
        OrderFormDto dto = new OrderFormDto();
        dto.setId(orderFormId);
        OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(dto);
        if (orderFormDto != null) {
            if (PayStatusEunm.BUSINESS_CONFIRMATION.getStatus().equals(orderFormDto.getStatus())) {
                dto.setStatus(PayStatusEunm.CONSIGNMENT.getStatus());
                dto.setUpdateDate(new Date());
                orderFormService.updateByPrimaryKeySelective(dto);
                logger.info(">>>>>>>> 处理[订单自动发货中]任务:" + orderFormId);
            } else {
                logger.info(">>>>>>>> 处理[订单自动发货中]任务:" + orderFormId + "，[" + PayStatusEunm.getNameByStatus(orderFormDto.getStatus()) + "]订单状态不对...");
            }
        } else {
            logger.info(">>>>>>>> 处理[订单自动发货中]任务:" + orderFormId + "，订单不存在...");
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
                dto.setUpdateDate(new Date());
                orderFormService.updateByPrimaryKeySelective(dto);
                logger.info(">>>>>>>> 处理[订单自动确认]任务:" + orderFormId);
                //订单自动发货中
                try {
                    new OrderFormTaskQueue(OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONSIGNMENT.getTypeCode(), orderFormId).putOrderFormTask();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e);
                }
            } else {
                logger.info(">>>>>>>> 处理[订单自动确认]任务:" + orderFormId + "，[" + PayStatusEunm.getNameByStatus(orderFormDto.getStatus()) + "]订单状态不对...");
            }
        } else {
            logger.info(">>>>>>>> 处理[订单自动确认]任务:" + orderFormId + "，订单不存在...");
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

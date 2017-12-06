package org.billow.common.queues;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.queues.Task.TaskQueueDaemonThread;
import org.billow.model.expand.OrderFormDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.enumType.OrderFormTaskQueueEunm;
import org.billow.utils.enumType.PayStatusEunm;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 系统启动时初始化订单队列
 * PostConstruct-->afterPropertiesSet
 *
 * @author liuyongtao
 * @create 2017-12-01 9:38
 */
@Service
public class OrderFormTaskQueueInit implements InitializingBean {

    private static final Logger logger = Logger.getLogger(OrderFormTaskQueueInit.class);
    @Autowired
    private OrderFormService orderFormService;

    //自动确认订单---------start
    @Value("${orderForm.auto.confirm.time}")
    private String orderFormAutoConfirmTime;
    @Value("${orderForm.auto.confirm.minTime}")
    private String orderFormAutoMinConfirmTime;
    @Value("${orderForm.queue.interval.confirm.time}")
    private String orderFormQueueIntervalConfirmTime;
    @Value("${orderForm.auto.confirm.flag}")
    private boolean orderFormAutoConfirmFlag;
    //自动确认订单---------end

    //自动发货中---------start
    @Value("${orderForm.auto.consignment.time}")
    private String orderFormAutoConsignmentTime;
    @Value("${orderForm.auto.consignment.minTime}")
    private String orderFormAutoMinConsignmentTime;
    @Value("${orderForm.queue.interval.consignment.time}")
    private String orderFormQueueIntervalConsignmentTime;
    @Value("${orderForm.auto.consignment.flag}")
    private boolean orderFormAutoConsignmentFlag;
    //自动发货中---------end


    @PostConstruct
    public void postConstruct() {
        if (orderFormAutoConfirmFlag || orderFormAutoConsignmentFlag) {
            //初始化守护线程
            TaskQueueDaemonThread.getInstance().init();
        }
    }

    /**
     * 系统重启后，查询数据库数据，放入到队列中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //自动确认订单
        this.initOrderFormAutoConfirm();
        //自动发货中
        this.initOrderFormAutoConsignment();
    }

    /**
     * 初始化自动发货中队列
     */
    private void initOrderFormAutoConsignment() {
        if (!orderFormAutoConsignmentFlag) return;
        logger.info("==============================初始化自动发货中队列开始===========================");
        try {
            long autoTime = ToolsUtils.splitTextData(orderFormAutoConsignmentTime);
            long autoMinTime = ToolsUtils.splitTextData(orderFormAutoMinConsignmentTime);
            long queueIntervalTime = ToolsUtils.splitTextData(orderFormQueueIntervalConsignmentTime);
            //查询出支付成功的
            OrderFormDto dto = new OrderFormDto();
            dto.setStatus(PayStatusEunm.BUSINESS_CONFIRMATION.getStatus());
            List<OrderFormDto> orderFormDtos = orderFormService.selectAll(dto);
            if (ToolsUtils.isNotEmpty(orderFormDtos)) {
                Collections.reverse(orderFormDtos);
                for (int i = 0; i < orderFormDtos.size(); i++) {
                    OrderFormDto orderFormDto = orderFormDtos.get(i);
                    long time = autoTime - (new Date().getTime() - orderFormDto.getUpdateDate().getTime());
                    //避免系统启动时执行业务逻辑
                    if (time <= autoMinTime) {
                        //队列任务间隔时间
                        time = autoMinTime + (i * queueIntervalTime);
                    }
                    String typeCode = OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONSIGNMENT.getTypeCode();
                    String orderFormId = orderFormDto.getId();
                    new OrderFormTaskQueue(typeCode, time, orderFormId).putOrderFormTask();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化自动发货中队列错误" + e);
        }
        logger.info("==============================初始化自动发货中队列结束===========================");
    }

    /**
     * 初始化自动确认订单队列
     */
    private void initOrderFormAutoConfirm() {
        if (!orderFormAutoConfirmFlag) return;
        logger.info("==============================初始化自动订确认单队列开始===========================");
        try {
            long autoTime = ToolsUtils.splitTextData(orderFormAutoConfirmTime);
            long autoMinTime = ToolsUtils.splitTextData(orderFormAutoMinConfirmTime);
            long queueIntervalTime = ToolsUtils.splitTextData(orderFormQueueIntervalConfirmTime);
            //查询出支付成功的
            OrderFormDto dto = new OrderFormDto();
            dto.setStatus(PayStatusEunm.TRADE_SUCCESS.getStatus());
            List<OrderFormDto> orderFormDtos = orderFormService.selectAll(dto);
            if (ToolsUtils.isNotEmpty(orderFormDtos)) {
                Collections.reverse(orderFormDtos);
                for (int i = 0; i < orderFormDtos.size(); i++) {
                    OrderFormDto orderFormDto = orderFormDtos.get(i);
                    long time = autoTime - (new Date().getTime() - orderFormDto.getUpdateDate().getTime());
                    //避免系统启动时执行业务逻辑
                    if (time <= autoMinTime) {
                        //队列任务间隔时间
                        time = autoMinTime + (i * queueIntervalTime);
                    }
                    String typeCode = OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode();
                    String orderFormId = orderFormDto.getId();
                    new OrderFormTaskQueue(typeCode, time, orderFormId).putOrderFormTask();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化自动确认订单队列错误" + e);
        }
        logger.info("==============================初始化自动确认订单队列结束===========================");
    }
}

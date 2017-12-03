package org.billow.common.queues;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.queues.Task.TaskQueueDaemonThread;
import org.billow.model.expand.OrderFormDto;
import org.billow.utils.ToolsUtils;
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

    @Value("${orderForm.auto.time}")
    private String orderFormAutoTime;
    @Value("${orderForm.auto.minTime}")
    private String orderFormAutoMinTime;
    @Value("${orderForm.queue.interval.time}")
    private String orderFormQueueIntervalTime;
    @Value("${orderForm.auto.flag}")
    private boolean orderFormAutoFlag;
    @Autowired
    private OrderFormService orderFormService;

    @PostConstruct
    public void postConstruct() {
        if (!orderFormAutoFlag) return;
        //初始化守护线程
        TaskQueueDaemonThread.getInstance().init();
    }

    /**
     * 系统重启后，查询数据库数据，放入到队列中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (!orderFormAutoFlag) return;
        logger.debug("==============================初始化自动订单队列开始===========================");
        try {
            long autoTime = ToolsUtils.splitTextData(orderFormAutoTime);
            long autoMinTime = ToolsUtils.splitTextData(orderFormAutoMinTime);
            long queueIntervalTime = ToolsUtils.splitTextData(orderFormQueueIntervalTime);
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
                    new OrderFormTaskQueue(orderFormDto.getId(), time);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化自动订单队列错误" + e);
        }
        logger.debug("==============================初始化自动订单队列结束===========================");
    }
}

package org.billow.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.billow.model.base.BaseModel;

/**
 * 订单信息数据库模型<br>
 * <p>
 * 对应的表名：t_order_form
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:46
 */
public class OrderFormBase extends BaseModel implements Serializable {

    public OrderFormBase() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id 订单Id
     */
    public OrderFormBase(String id) {
        super();
        this.id = id;
    }

    // 收货人
    private String consignee;
    // 下单人id
    private Integer userId;
    // 订单金额
    private BigDecimal orderformAmount;
    // 订单Id
    private String id;
    // 下单时间
    private Date createDate;
    // 收货人电话
    private String consigneePhone;
    // 修改时间
    private Date updateDate;
    // 订单状态：0-删除，1-提交，2-确认，3-取消，4-完成
    private String status;

    /**
     * 收货人
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public String getConsignee() {
        return this.consignee;
    }

    /**
     * 收货人
     *
     * @param consignee
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 下单人id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 下单人id
     *
     * @param userId
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 订单金额
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public BigDecimal getOrderformAmount() {
        return this.orderformAmount;
    }

    /**
     * 订单金额
     *
     * @param orderformAmount
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setOrderformAmount(BigDecimal orderformAmount) {
        this.orderformAmount = orderformAmount;
    }

    /**
     * 订单Id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public String getId() {
        return this.id;
    }

    /**
     * 订单Id
     *
     * @param id
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 下单时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 下单时间
     *
     * @param createDate
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 收货人电话
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public String getConsigneePhone() {
        return this.consigneePhone;
    }

    /**
     * 收货人电话
     *
     * @param consigneePhone
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    /**
     * 修改时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 修改时间
     *
     * @param updateDate
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 订单状态：0-删除，1-提交，2-确认，3-取消，4-完成
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 订单状态：0-删除，1-提交，2-确认，3-取消，4-完成
     *
     * @param status
     * @author billow<br>
     * @date: 2017-10-24 17:19:46
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return "PK[id = " + id + "]";
    }
}  
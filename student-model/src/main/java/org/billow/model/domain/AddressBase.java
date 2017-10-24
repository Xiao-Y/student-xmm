package org.billow.model.domain;

import java.io.Serializable;
import java.util.Date;

import org.billow.model.base.BaseModel;

/**
 * 收货地址数据库模型<br>
 * <p>
 * 对应的表名：t_address
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:32:05
 */
public class AddressBase extends BaseModel implements Serializable {

    public AddressBase() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id 收货地址id
     */
    public AddressBase(String id) {
        super();
        this.id = id;
    }

    // 收货人地址
    private String consigneeAddress;
    // 更新时间
    private Date updateTime;
    // 收货人
    private String consignee;
    // 创建时间
    private Date createTime;
    // 用户id
    private Integer userId;
    // 收货地址id
    private String id;
    // 收货人电话
    private String consigneePhone;
    // 是否默认收货地址，0-否，1-是
    private String status;

    /**
     * 收货人地址
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public String getConsigneeAddress() {
        return this.consigneeAddress;
    }

    /**
     * 收货人地址
     *
     * @param consigneeAddress
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    /**
     * 更新时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 更新时间
     *
     * @param updateTime
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 收货人
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public String getConsignee() {
        return this.consignee;
    }

    /**
     * 收货人
     *
     * @param consignee
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 创建时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 用户id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     *
     * @param userId
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 收货地址id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public String getId() {
        return this.id;
    }

    /**
     * 收货地址id
     *
     * @param id
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 收货人电话
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public String getConsigneePhone() {
        return this.consigneePhone;
    }

    /**
     * 收货人电话
     *
     * @param consigneePhone
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    /**
     * 是否默认收货地址，0-否，1-是
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 是否默认收货地址，0-否，1-是
     *
     * @param status
     * @author billow<br>
     * @date: 2017-10-24 17:32:05
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
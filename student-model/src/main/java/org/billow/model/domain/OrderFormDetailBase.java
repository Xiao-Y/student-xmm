package org.billow.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.billow.model.base.BaseModel;

/**
 * 订单信息详细数据库模型<br>
 * <p>
 * 对应的表名：t_order_form_detail
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:21:52
 */
public class OrderFormDetailBase extends BaseModel implements Serializable {

    public OrderFormDetailBase() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id 订单id
     */
    public OrderFormDetailBase(String id) {
        super();
        this.id = id;
    }

    // 商品信息
    private String commodityInfo;
    //订单id
    private String orderFormId;
    // 订单id
    private String id;
    // 单价
    private BigDecimal unitPrice;
    // 包装
    private String packing;
    // 规格
    private String spec;
    // 商品名称
    private String commodityName;
    //商品数量
    private Integer commodityNum;
    //商品id
    private String commodityId;

    /**
     * 商品id
     *
     * @return
     */
    public String getCommodityId() {
        return commodityId;
    }

    /**
     * 商品id
     *
     * @param commodityId
     */
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * 商品数量
     *
     * @return
     */
    public Integer getCommodityNum() {
        return commodityNum;
    }

    /**
     * 商品数量
     *
     * @param commodityNum
     */
    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    /**
     * 商品信息
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public String getCommodityInfo() {
        return this.commodityInfo;
    }

    /**
     * 商品信息
     *
     * @param commodityInfo
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public void setCommodityInfo(String commodityInfo) {
        this.commodityInfo = commodityInfo;
    }

    /**
     * 订单id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public String getOrderFormId() {
        return this.orderFormId;
    }

    /**
     * 订单id
     *
     * @param orderFormId
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public void setOrderFormId(String orderFormId) {
        this.orderFormId = orderFormId;
    }

    /**
     * 订单详细id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public String getId() {
        return this.id;
    }

    /**
     * 订单详细id
     *
     * @param id
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 单价
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * 单价
     *
     * @param unitPrice
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 包装
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public String getPacking() {
        return this.packing;
    }

    /**
     * 包装
     *
     * @param packing
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public void setPacking(String packing) {
        this.packing = packing;
    }

    /**
     * 规格
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public String getSpec() {
        return this.spec;
    }

    /**
     * 规格
     *
     * @param spec
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * 商品名称
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public String getCommodityName() {
        return this.commodityName;
    }

    /**
     * 商品名称
     *
     * @param commodityName
     * @author billow<br>
     * @date: 2017-10-24 17:21:52
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }


    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return "PK[id = " + id + "]";
    }
}  
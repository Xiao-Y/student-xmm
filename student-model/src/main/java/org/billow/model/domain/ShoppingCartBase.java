package org.billow.model.domain;

import java.io.Serializable;
import java.util.Date;

import org.billow.model.base.BaseModel;

/**
 * 购物车数据库模型<br>
 * <p>
 * 对应的表名：t_shopping_cart
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-20 15:59:27
 */
public class ShoppingCartBase extends BaseModel implements Serializable {

    public ShoppingCartBase() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param commodityId 商品id
     * @param id          id相同
     */
    public ShoppingCartBase(String commodityId, String id) {
        super();
        this.commodityId = commodityId;
        this.id = id;
    }

    // 商品id
    private String commodityId;
    // id相同
    private String id;
    // 商品数量
    private Integer commodityNum;
    //最后更新时间
    private Date updateTime;

    /**
     * 最后更新时间
     *
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 最后更新时间
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 商品id
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-20 15:59:27
     */
    public String getCommodityId() {
        return this.commodityId;
    }

    /**
     * 商品id
     *
     * @param commodityId
     * @author billow<br>
     * @date: 2017-10-20 15:59:27
     */
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * id相同
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-20 15:59:27
     */
    public String getId() {
        return this.id;
    }

    /**
     * id相同
     *
     * @param id
     * @author billow<br>
     * @date: 2017-10-20 15:59:27
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 商品数量
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-20 15:59:27
     */
    public Integer getCommodityNum() {
        return this.commodityNum;
    }

    /**
     * 商品数量
     *
     * @param commodityNum
     * @author billow<br>
     * @date: 2017-10-20 15:59:27
     */
    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }


    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return "PK[commodityId = " + commodityId + ",id = " + id + "]";
    }
}  
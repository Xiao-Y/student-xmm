package org.billow.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.billow.model.base.BaseModel;

/**
 * 商品修改数据库模型<br>
 * <p>
 * 对应的表名：t_commodity
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-19 15:45:37
 */
public class CommodityBase extends BaseModel implements Serializable {

    public CommodityBase() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id 商品名称
     */
    public CommodityBase(String id) {
        super();
        this.id = id;
    }

    // 删除标志，0-删除，1-正常
    private String deleFlag;
    // 商品信息
    private String commodityInfo;
    // 0-无效，1-有效
    private String valid;
    // 等级
    private String grade;
    // 商品名称
    private String id;
    // 单价
    private BigDecimal unitPrice;
    // 产地
    private String localityGrowth;
    // 包装
    private String packing;
    // 规格
    private String spec;
    // 商品名称
    private String commodityName;
    // 状态：0-无货，1-有货
    private String status;
    private Date updateTime;
    //销售数量
    private int quantity;
    //商品图片名称
    private String img;

    /**
     * 商品图片名称
     * @return
     */
    public String getImg() {
        return img;
    }

    /**
     * 商品图片名称
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 销售数量
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 销售数量
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 删除标志，0-删除，1-正常
     *
     * @return
     */
    public String getDeleFlag() {
        return deleFlag;
    }

    /**
     * 删除标志，0-删除，1-正常
     *
     * @param deleFlag
     */
    public void setDeleFlag(String deleFlag) {
        this.deleFlag = deleFlag;
    }

    /**
     * 商品信息
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getCommodityInfo() {
        return this.commodityInfo;
    }

    /**
     * 商品信息
     *
     * @param commodityInfo
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setCommodityInfo(String commodityInfo) {
        this.commodityInfo = commodityInfo;
    }

    /**
     * 0-无效，1-有效
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getValid() {
        return this.valid;
    }

    /**
     * 0-无效，1-有效
     *
     * @param valid
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

    /**
     * 等级
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getGrade() {
        return this.grade;
    }

    /**
     * 等级
     *
     * @param grade
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 商品名称
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getId() {
        return this.id;
    }

    /**
     * 商品名称
     *
     * @param id
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 单价
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * 单价
     *
     * @param unitPrice
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 产地
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getLocalityGrowth() {
        return this.localityGrowth;
    }

    /**
     * 产地
     *
     * @param localityGrowth
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setLocalityGrowth(String localityGrowth) {
        this.localityGrowth = localityGrowth;
    }

    /**
     * 包装
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getPacking() {
        return this.packing;
    }

    /**
     * 包装
     *
     * @param packing
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setPacking(String packing) {
        this.packing = packing;
    }

    /**
     * 规格
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getSpec() {
        return this.spec;
    }

    /**
     * 规格
     *
     * @param spec
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * 商品名称
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getCommodityName() {
        return this.commodityName;
    }

    /**
     * 商品名称
     *
     * @param commodityName
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    /**
     * 状态：0-无货，1-有货
     *
     * @return
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 状态：0-无货，1-有货
     *
     * @param status
     * @author billow<br>
     * @date: 2017-10-19 15:45:37
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
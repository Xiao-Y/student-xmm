package org.billow.model.expand;

import org.billow.model.domain.CommodityBase;

/**
 * 商品修改model模型<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-19 15:45:38
 */
public class CommodityDto extends CommodityBase {

    //用于页面商品样式
    private String commodityDisplay;

    /**
     * 用于页面商品样式
     *
     * @return
     */
    public String getCommodityDisplay() {
        return commodityDisplay;
    }

    /**
     * 用于页面商品样式
     *
     * @param commodityDisplay
     */
    public void setCommodityDisplay(String commodityDisplay) {
        this.commodityDisplay = commodityDisplay;
    }

    public CommodityDto() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id 商品名称
     */
    public CommodityDto(String id) {
        super(id);
    }

    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return super.toString();
    }
}  
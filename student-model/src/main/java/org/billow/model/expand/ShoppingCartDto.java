package org.billow.model.expand;

import org.billow.model.domain.ShoppingCartBase;

/**
 * 购物车model模型<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-20 15:59:28
 */
public class ShoppingCartDto extends ShoppingCartBase {

    public ShoppingCartDto() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param commodityId 商品id
     * @param id          id相同
     */
    public ShoppingCartDto(String commodityId, String id) {
        super(commodityId, id);
    }

    //商品对象
    private CommodityDto commodityDto;

    /**
     * 商品对象
     *
     * @return
     */
    public CommodityDto getCommodityDto() {
        return commodityDto;
    }

    /**
     * 商品对象
     *
     * @param commodityDto
     */
    public void setCommodityDto(CommodityDto commodityDto) {
        this.commodityDto = commodityDto;
    }

    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return super.toString();
    }
}  
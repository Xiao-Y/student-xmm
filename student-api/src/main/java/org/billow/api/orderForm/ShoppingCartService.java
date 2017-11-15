package org.billow.api.orderForm;

import org.billow.api.base.BaseService;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;

import java.util.List;

/**
 * 购物车接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-20 15:59:28
 */
public interface ShoppingCartService extends BaseService<ShoppingCartDto> {

    /**
     * 商品添加到购物车,如果存在则数量加1,否则插入新数据
     *
     * @param id           商品id
     * @param commodityNum 商品数量
     * @param userDto      用户信息
     * @return 商品总数量
     */
    int addShoppingCart(ShoppingCartDto shoppingCartDto, UserDto userDto);

    /**
     * 查询我的购物车
     *
     * @param userDto 当前登陆用户
     * @return
     */
    List<ShoppingCartDto> myShoppingCart(UserDto userDto);
}
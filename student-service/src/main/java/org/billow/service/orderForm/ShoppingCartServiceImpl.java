package org.billow.service.orderForm;

import org.billow.api.orderForm.ShoppingCartService;
import org.billow.dao.CommodityDao;
import org.billow.dao.ShoppingCartDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.billow.utils.date.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 购物车实现类<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-20 15:59:28
 */
@Service
public class ShoppingCartServiceImpl extends BaseServiceImpl<ShoppingCartDto> implements ShoppingCartService {

    @Resource
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private CommodityDao commodityDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<ShoppingCartDto> baseDao) {
        super.baseDao = this.shoppingCartDao;
    }

    @Override
    public void addShoppingCart(String id, UserDto userDto) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(userDto.getUserId().toString());
        dto.setCommodityId(id);
        ShoppingCartDto shoppingCartDto = shoppingCartDao.selectByPrimaryKey(dto);
        if (shoppingCartDto == null) {
            dto.setCommodityNum(1);//默认为1
            dto.setUpdateTime(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
            shoppingCartDao.insert(dto);
        } else {
            shoppingCartDto.setCommodityNum(shoppingCartDto.getCommodityNum() + 1);
            shoppingCartDto.setUpdateTime(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
            shoppingCartDao.updateByPrimaryKeySelective(shoppingCartDto);
        }
    }

    @Override
    public List<ShoppingCartDto> myShoppingCart(UserDto userDto) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(userDto.getUserId().toString());
        List<ShoppingCartDto> list = shoppingCartDao.selectAll(dto);
        //查询商品信息
        if (ToolsUtils.isNotEmpty(list)) {
            for (ShoppingCartDto cartDto : list) {
                String commodityId = cartDto.getCommodityId();
                CommodityDto commodityDto = new CommodityDto();
                commodityDto.setId(commodityId);
                commodityDao.selectByPrimaryKey(commodityDto);
                cartDto.setCommodityDto(commodityDto);
            }
        }
        return list;
    }
}
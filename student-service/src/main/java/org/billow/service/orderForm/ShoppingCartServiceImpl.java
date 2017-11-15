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
    public int addShoppingCart(ShoppingCartDto shoppingCartDto, UserDto userDto) {
        String commodityId = shoppingCartDto.getCommodityId();
        Integer commodityNum = shoppingCartDto.getCommodityNum() == null ? 1 : shoppingCartDto.getCommodityNum();

        //商品数量
        int shoppingCount = 0;
        //是否存在这个商品在购物车
        boolean flag = false;
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(userDto.getUserId().toString());
        List<ShoppingCartDto> shoppingCartDtos = shoppingCartDao.selectAll(dto);
        if (ToolsUtils.isNotEmpty(shoppingCartDtos)) {
            for (ShoppingCartDto cartDto : shoppingCartDtos) {
                if (cartDto.getCommodityId().equals(commodityId)) {//如果商品存在更新商品数量
                    cartDto.setCommodityNum(cartDto.getCommodityNum() + commodityNum);
                    cartDto.setUpdateTime(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
                    shoppingCartDao.updateByPrimaryKeySelective(cartDto);
                    //商品存在
                    flag = true;
                }
                shoppingCount += cartDto.getCommodityNum();
            }
        }

        if (!flag) {
            dto.setCommodityId(commodityId);
            dto.setCommodityNum(commodityNum);//默认为1
            dto.setUpdateTime(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
            shoppingCartDao.insert(dto);
            shoppingCount += commodityNum;
        }
        return shoppingCount;
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
                CommodityDto commodity = commodityDao.selectByPrimaryKey(commodityDto);
                cartDto.setCommodityDto(commodity);
            }
        }
        return list;
    }
}
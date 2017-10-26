package org.billow.service.orderForm;

import javax.annotation.Resource;

import org.billow.api.orderForm.AddressService;
import org.billow.dao.AddressDao;
import org.billow.model.expand.AddressDto;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.billow.utils.generator.UUID;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 收货地址实现类<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:32:06
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressDto> implements AddressService {

    @Resource
    private AddressDao addressDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<AddressDto> baseDao) {
        super.baseDao = this.addressDao;
    }

    @Override
    public void saveOrUpdateMyAddress(UserDto loginUser, AddressDto addressDto) throws Exception {
        if ("1".equals(addressDto.getStatus())) {//设置了默认地址
            AddressDto dto = new AddressDto();
            dto.setUserId(loginUser.getUserId());
            List<AddressDto> addressDtos = addressDao.selectAll(dto);
            if (ToolsUtils.isNotEmpty(addressDtos)) {
                for (AddressDto temp : addressDtos) {//更新所有的默认地址为空
                    temp.setStatus(null);
                    addressDao.updateByPrimaryKey(temp);
                }
            }
        }
        if (ToolsUtils.isNotEmpty(addressDto.getId())) {//更新
            addressDto.setUpdateTime(new Date());
            addressDao.updateByPrimaryKeySelective(addressDto);
        } else {//添加
            addressDto.setId(UUID.generate());
            addressDto.setUpdateTime(new Date());
            addressDto.setCreateTime(new Date());
            addressDto.setUserId(loginUser.getUserId());
            addressDao.insert(addressDto);
        }
    }
}
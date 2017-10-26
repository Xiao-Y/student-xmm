package org.billow.api.orderForm;

import org.billow.api.base.BaseService;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.UserDto;

/**
 * 收货地址接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:32:06
 */
public interface AddressService extends BaseService<AddressDto> {


    /**
     * 添加or更新收货地址,更新默认收货地址
     *
     * @param loginUser
     * @param addressDto
     */
    void saveOrUpdateMyAddress(UserDto loginUser, AddressDto addressDto) throws Exception;
}
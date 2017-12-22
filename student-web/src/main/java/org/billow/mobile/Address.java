package org.billow.mobile;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.AddressService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 收货地址
 *
 * @author liuyongtao
 * @create 2017-12-21 8:30
 */
@Controller
@RequestMapping("/mb/address")
public class Address {

    private static final Logger logger = Logger.getLogger(Address.class);

    @Autowired
    private AddressService addressService;

    /**
     * 收货地址列表
     *
     * @param request
     * @param addressDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/address")
    public List<AddressDto> address(HttpServletRequest request, AddressDto addressDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        addressDto.setUserId(loginUser.getUserId());
        List<AddressDto> list = addressService.selectAll(addressDto);
        return list;
    }

    /**
     * 进入收货地址编辑/添加页面
     *
     * @param request
     * @param addressDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/editAddress")
    public AddressDto editAddress(HttpServletRequest request, AddressDto addressDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        addressDto.setUserId(loginUser.getUserId());
        AddressDto dto = addressService.selectByPrimaryKey(addressDto);
        return dto;
    }

    /**
     * 保存/更新收货地址
     *
     * @param request
     * @param addressDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveAddress")
    public JsonResult saveAddress(HttpServletRequest request, AddressDto addressDto) {
        String type = addressDto.getType();
        JsonResult json = new JsonResult();
        boolean success = true;
        String message = "";
        String id = addressDto.getId();
        try {
            UserDto loginUser = LoginHelper.getLoginUser(request);
            addressDto.setUserId(loginUser.getUserId());
            if ("add".equals(type)) {
                id = UUID.generate();
                addressDto.setId(id);
                addressDto.setUpdateTime(new Date());
                addressDto.setCreateTime(new Date());
                addressService.insert(addressDto);
                message = MessageTipsCst.SAVE_SUCCESS;
            } else {
                addressDto.setUpdateTime(new Date());
                addressService.updateByPrimaryKeySelective(addressDto);
                message = MessageTipsCst.UPDATE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if ("add".equals(type)) {
                message = MessageTipsCst.SAVE_FAILURE;
            } else {
                message = MessageTipsCst.UPDATE_FAILURE;
            }
            success = false;
        }
        json.setData(id);
        json.setMessage(message);
        json.setSuccess(success);
        return json;
    }

    /**
     * 删除收货地址
     *
     * @param request
     * @param addressDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteAddress")
    public JsonResult deleteAddress(HttpServletRequest request, AddressDto addressDto) {
        JsonResult json = new JsonResult();
        String message = "";
        boolean success = true;
        try {
            addressService.deleteByPrimaryKey(addressDto);
            message = MessageTipsCst.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.DELETE_FAILURE;
            success = false;
        }
        json.setMessage(message);
        json.setSuccess(success);
        return json;
    }
}

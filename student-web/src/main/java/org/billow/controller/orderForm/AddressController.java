package org.billow.controller.orderForm;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.AddressService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 收货地址控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:32:06
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    private static final Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    /**
     * 查看个人的收货地址
     *
     * @param request
     * @return
     */
    @RequestMapping("/myAddressView")
    public ModelAndView myAddressView(HttpServletRequest request) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        AddressDto addressDto = new AddressDto();
        addressDto.setUserId(loginUser.getUserId());
        List<AddressDto> list = addressService.selectAll(addressDto);
        ModelAndView av = new ModelAndView();
        av.addObject("addressDtos", list);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "myAddressView");
        return av;
    }


    /**
     * 个人收货地址管理
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     * @date 2017年4月24日 上午9:52:46
     */
    @RequestMapping("/myAddressList")
    public ModelAndView myAddressList(HttpServletRequest request) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        AddressDto addressDto = new AddressDto();
        addressDto.setUserId(loginUser.getUserId());
        List<AddressDto> list = addressService.selectAll(addressDto);
        ModelAndView av = new ModelAndView();
        av.addObject("addressDtos", list);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "myAddressList");
        return av;
    }

    /**
     * 显示收货地址编辑页面
     *
     * @param addressDto
     * @return
     */
    @RequestMapping("/myAddressEdit")
    public ModelAndView myAddressEdit(AddressDto addressDto) {
        ModelAndView av = new ModelAndView();
        if (addressDto != null) {
            AddressDto dto = new AddressDto();
            dto.setId(addressDto.getId());
            AddressDto address = addressService.selectByPrimaryKey(dto);
            av.addObject("address", address);
        }
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "myAddressEdit");
        return av;
    }

    @ResponseBody
    @RequestMapping("/myAddressSave")
    public JsonResult myAddressSave(HttpServletRequest request, AddressDto addressDto) {
        JsonResult json = new JsonResult();
        UserDto loginUser = LoginHelper.getLoginUser(request);
        String message = "";
        String type = "";
        try {
            if (ToolsUtils.isNotEmpty(addressDto.getId())) {
                addressDto.setUpdateTime(new Date());
                addressService.updateByPrimaryKeySelective(addressDto);
            } else {
                addressDto.setId(UUID.generate());
                addressDto.setUpdateTime(new Date());
                addressDto.setCreateTime(new Date());
                addressDto.setUserId(loginUser.getUserId());
                addressService.insert(addressDto);
            }
            message = MessageTipsCst.SUBMIT_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.SUBMIT_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }
}  
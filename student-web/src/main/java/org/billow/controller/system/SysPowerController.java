package org.billow.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.menu.MenuService;
import org.billow.api.system.RolePermissionService;
import org.billow.api.system.RoleService;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.MenuDto;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.RolePermissionDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-02 09:16:39
 */
@Controller
@RequestMapping("/sysPower")
public class SysPowerController {

    private static final Logger logger = Logger.getLogger(SysPowerController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private MenuService menuService;

    /**
     * 查询角色列表
     *
     * @param request
     * @param roleDto 查询条件
     * @return
     */
    @RequestMapping("/queryRoles")
    public ModelAndView queryRoles(HttpServletRequest request, RoleDto roleDto) {
        ModelAndView av = new ModelAndView();
        PageHelper.startPage();
        List<RoleDto> roles = roleService.selectAll(roleDto);
        PageInfo<RoleDto> page = new PageInfo<>(roles);
        av.addObject("page", page);
        av.setViewName(PagePathCst.BASEPATH_SYSTEM + "roleList");
        return av;
    }

    /**
     * 添加/修改菜单信息，包含菜单权限
     *
     * @param role
     * @return
     */
    @RequestMapping("/roleEdit")
    public ModelAndView roleEdit(RoleDto role) {
        RoleDto roleDto = new RoleDto();
        // 编辑时，显示数据
        if (role.getId() != null) {
            roleDto = roleService.selectByPrimaryKey(role);
        }
        ModelAndView av = new ModelAndView();
        // 用于修改后保持停留在页面
        roleDto.setPageNo(role.getPageNo());
        av.addObject("role", roleDto);
        av.setViewName(PagePathCst.BASEPATH_SYSTEM + "roleEdit");
        return av;
    }

    /**
     * 保存角色信息修改
     *
     * @param role    角色信息
     * @param menuIds 权限信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/roleSave")
    public JsonResult roleSave(RoleDto role, String menuIds) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            roleService.saveRoleAndRolePerssion(role,menuIds);
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
        json.setRoot("/sysPower/queryRoles?pageNo=" + role.getPageNo());
        return json;
    }

    @ResponseBody
    @RequestMapping("/roleDel")
    public JsonResult roleDel(RoleDto role) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            roleService.deleteDel(role);
            message = MessageTipsCst.DELETE_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.DELETE_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }

    /**
     * 构造zTree数据
     *
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRolePermission")
    public List<MenuDto> getRolePermission(RoleDto role) {
        List<RolePermissionDto> rolePermissionDtos = new ArrayList<>();
        // 编辑时，显示数据
        if (role.getId() != null) {
            //查询出该角色的所有权限
            RolePermissionDto rolePermissionDto = new RolePermissionDto();
            rolePermissionDto.setRoleId(role.getId());
            rolePermissionDtos = rolePermissionService.selectAll(rolePermissionDto);
        }
        //查询出菜单
        List<MenuDto> menuDtos = menuService.selectAll(null);
        for (MenuDto menu : menuDtos) {
            menu.setName(menu.getTitle());
            if(!menu.getValidind()){
                menu.setTitle("此菜单无效");
                //menu.setChecked(false);
                //menu.setChkDisabled(true);
            }
            menu.setIcon(null);
            if (menu.getPid() == 0) {
                menu.setOpen(true);
            }
            if (ToolsUtils.isNotEmpty(rolePermissionDtos)) {
                for (RolePermissionDto rolePermissionDto : rolePermissionDtos) {
                    if (rolePermissionDto.getMenuId() == menu.getId()) {//设置为选中
                        menu.setChecked(true);
                    }
                }
            }
        }
        return menuDtos;
    }
}

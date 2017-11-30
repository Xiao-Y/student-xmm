package org.billow.service.menu;

import org.billow.api.menu.MenuService;
import org.billow.dao.MenuDao;
import org.billow.dao.RoleDao;
import org.billow.dao.RolePermissionDao;
import org.billow.dao.UserDao;
import org.billow.dao.UserRoleDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.domain.MenuBase;
import org.billow.model.expand.MenuDto;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.RolePermissionDto;
import org.billow.model.expand.UserDto;
import org.billow.model.expand.UserRoleDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDto> implements MenuService, Comparator<MenuBase> {

    @Resource
    private MenuDao menuDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private RoleDao roleDao;
    @Value("${super.system.admin.role}")
    private String superSystemAdminRole;

    @Resource
    @Override
    public void setBaseDao(BaseDao<MenuDto> baseDao) {
        super.baseDao = this.menuDao;
    }

    @Override
    public List<MenuDto> getMenuChildList(Integer id) {
        return menuDao.getMenuChildList(id);
    }

    @Override
    public List<MenuDto> selectAll(MenuDto menu) {
        return menuDao.selectAll(menu);
    }

    @Override
    public List<MenuDto> findMenu(UserDto loginUser, String contextPath) {
        //查询用户的菜单权限信息
        List<Integer> menuIds = menuDao.findMenuIdsByUserId(loginUser.getUserId());
        if (ToolsUtils.isEmpty(menuIds)) {
            return null;
        }
        MenuDto menu = new MenuDto();
        menu.setPid(0);
        menu.setValidind(true);
        List<MenuDto> selectAll = menuDao.selectAll(menu);
        if (ToolsUtils.isEmpty(selectAll)) {
            return null;
        }
        Collections.sort(selectAll, this);
        Iterator<MenuDto> parentIterator = selectAll.iterator();
        while (parentIterator.hasNext()) {
            MenuDto temp = parentIterator.next();
            if (temp == null) {
                continue;
            }
            //没有权限的remove
            if (!menuIds.contains(temp.getId())) {
                parentIterator.remove();
                continue;
            }
            List<MenuDto> childList = menuDao.getMenuChildList(temp.getId());
            if (ToolsUtils.isEmpty(childList)) {
                return null;
            }
            Iterator<MenuDto> iterator = childList.iterator();
            while (iterator.hasNext()) {
                MenuDto tempChild = iterator.next();
                if (tempChild == null) {
                    continue;
                }
                //无效数据remove
                if (!tempChild.getValidind()) {
                    iterator.remove();
                    continue;
                }
                //没有权限的remove
                if (!menuIds.contains(tempChild.getId())) {
                    iterator.remove();
                    continue;
                }
                //父节点remove
                if (Long.compare(0, tempChild.getPid()) == 0) {
                    iterator.remove();
                    continue;
                }
                String href = tempChild.getHref();
                if (ToolsUtils.isNotEmpty(href) && !(href.startsWith("https") || href.startsWith("http"))) {
                    href = contextPath + href;
                }
                tempChild.setHref(href);
            }
            Collections.sort(childList, this);
            temp.setChildren(childList);
        }
        return selectAll;
    }

    @Override
    public void insertMenu(MenuDto menu) {
        menuDao.insert(menu);
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(superSystemAdminRole);
        List<RoleDto> roleDtos = roleDao.selectAll(roleDto);
        if (ToolsUtils.isEmpty(roleDtos)) {
            throw new RuntimeException("超级系统管理员:" + superSystemAdminRole + ",不存在!");
        }
        RolePermissionDto dto = new RolePermissionDto();
        dto.setRoleId(roleDtos.get(0).getId());
        dto.setMenuId(menu.getId());
        rolePermissionDao.insert(dto);
    }

    @Override
    public void deleteMenu(MenuDto menu) {
        menuDao.deleteByPrimaryKey(menu);
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(superSystemAdminRole);
        List<RoleDto> roleDtos = roleDao.selectAll(roleDto);
        if (ToolsUtils.isEmpty(roleDtos)) {
            throw new RuntimeException("超级系统管理员:" + superSystemAdminRole + ",不存在!");
        }
        RolePermissionDto dto = new RolePermissionDto();
        dto.setRoleId(roleDtos.get(0).getId());
        dto.setMenuId(menu.getId());
        rolePermissionDao.deleteByRoleIdAndMenuId(dto);
    }

    /**
     * 菜单排序
     */
    @Override
    public int compare(MenuBase m1, MenuBase m2) {
        return m1.getDisplayno().compareTo(m2.getDisplayno());
    }
}

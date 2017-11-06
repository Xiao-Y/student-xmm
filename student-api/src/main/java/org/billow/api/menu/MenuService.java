package org.billow.api.menu;

import org.billow.api.base.BaseService;
import org.billow.model.expand.MenuDto;
import org.billow.model.expand.UserDto;

import java.util.List;

public interface MenuService extends BaseService<MenuDto> {
    public List<MenuDto> getMenuChildList(Integer id);

    public List<MenuDto> selectAll(MenuDto menu);

    /**
     * 查询对应登陆用户的菜单
     *
     * @param loginUser
     * @param contextPath
     * @return
     */
    List<MenuDto> findMenu(UserDto loginUser, String contextPath);

    /**
     * 插入菜单信息，保存sa的权限信息
     *
     * @param menu
     */
    void insertMenu(MenuDto menu);

    /**
     * 删除菜单信息,同时删除sa的权限信息
     *
     * @param menu
     */
    void deleteMenu(MenuDto menu);
}

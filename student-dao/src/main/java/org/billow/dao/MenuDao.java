package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.MenuDto;

public interface MenuDao extends BaseDao<MenuDto> {

    List<MenuDto> getMenuChildList(Integer id);

    /**
     * 通过用户Id查询出用户的菜单权限
     *
     * @param userId 用户Id
     * @return 菜单权限
     */
    List<Integer> findMenuIdsByUserId(Integer userId);

}
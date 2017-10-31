package org.billow.service.menu;

import org.billow.api.menu.MenuService;
import org.billow.dao.MenuDao;
import org.billow.dao.UserRoleDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.domain.MenuBase;
import org.billow.model.expand.MenuDto;
import org.billow.model.expand.UserDto;
import org.billow.model.expand.UserRoleDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        Integer userId = loginUser.getUserId();
        //查询用户的角色
        //List<UserRoleDto> userRoleDtos = userRoleDao.findUserRoleByUserId(userId);
        MenuDto menu = new MenuDto();
        menu.setPid(0);
        List<MenuDto> selectAll = menuDao.selectAll(menu);
        Collections.sort(selectAll, this);
        if (ToolsUtils.isNotEmpty(selectAll)) {
            for (MenuBase temp : selectAll) {
                List<MenuDto> childList = menuDao.getMenuChildList(temp.getId());
                if (ToolsUtils.isNotEmpty(childList)) {
                    Iterator<MenuDto> iterator = childList.iterator();
                    while (iterator.hasNext()) {
                        MenuBase tempChild = iterator.next();
                        if (Long.compare(0, tempChild.getPid()) == 0) {
                            iterator.remove();
                        }
                        String href = tempChild.getHref();
                        if (ToolsUtils.isNotEmpty(href) && !(href.startsWith("https") || href.startsWith("http"))) {
                            href = contextPath + href;
                        }
                        tempChild.setHref(href);
                    }
                }
                Collections.sort(childList, this);
                temp.setChildren(childList);
            }
        }
        return selectAll;
    }

    public int deleteByPrimaryKey(MenuDto record) {
        return menuDao.deleteByPrimaryKey(record);
    }

    public int insert(MenuDto record) {
        return menuDao.insert(record);
    }

    public int insertSelective(MenuDto record) {
        return menuDao.insertSelective(record);
    }

    public MenuDto selectByPrimaryKey(MenuDto record) {
        return menuDao.selectByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(MenuDto record) {
        return menuDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MenuDto record) {
        return menuDao.updateByPrimaryKey(record);
    }


    /**
     * 菜单排序
     */
    @Override
    public int compare(MenuBase m1, MenuBase m2) {
        return m1.getDisplayno().compareTo(m2.getDisplayno());
    }
}

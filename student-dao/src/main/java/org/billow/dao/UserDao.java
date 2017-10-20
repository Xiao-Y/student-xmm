package org.billow.dao;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.UserDto;

public interface UserDao extends BaseDao<UserDto> {

    /**
     * 微信查询
     *
     * @param openId
     * @return
     * @author XiaoY
     * @date: 2017年6月19日 下午9:52:43
     */
    UserDto getUserByOpenId(String openId);

    /**
     * 通过用户id查询出所有的角色
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param userId 用户id
     * @return 角色
     * @date 2017年6月19日 下午3:48:57
     */
    UserDto findRoleListByUserId(int userId);

    /**
     * 通过用户名和密码查询出用户信息和角色信息
     *
     * @param user
     * @return
     */
    UserDto findUserByUserNameAndPwd(UserDto user);

    /**
     * 通过用户名查询出用户信息
     *
     * @param userName
     * @return
     */
    UserDto findUserByUserName(String userName);
}
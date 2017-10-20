package org.billow.api.user;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.expand.UserDto;

public interface UserService extends BaseService<UserDto> {

    public void deleteTest();

    public List<UserDto> findUserList(UserDto user);

    public int findUserCount(UserDto user);

    public UserDto findUserById(Integer id);

    /**
     * 微信查询
     *
     * @param openId
     * @return
     * @author XiaoY
     * @date: 2017年6月17日 上午7:54:00
     */
    public UserDto getUserByOpenId(String openId);

    /**
     * 通过用户id查询出角色信息
     *
     * @param userId 用户id
     * @return
     * @author XiaoY
     * @date: 2017年6月19日 下午9:32:11
     */
    public UserDto findRoleListByUserId(int userId);

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

package org.billow.api.user;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.billow.api.base.BaseService;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;

public interface UserService extends BaseService<UserDto> {

    public List<UserDto> findUserList(UserDto user);

    public int findUserCount(UserDto user);

    public UserDto findUserById(Integer id);

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

    /**
     * 保存用户注册信息，分配角色为客户customer
     *
     * @param userDto
     */
    void saveRegister(UserDto userDto);

    /**
     * 查询用户殂，包含角色
     *
     * @param userDto
     * @param loginUser 登陆用户
     */
    PageInfo<UserDto> queryUsers(UserDto loginUser, UserDto userDto);

    /**
     * 查询某个用户的信息和角色信息
     *
     * @param user
     * @param loginUser 登陆用户
     * @return
     */
    UserDto userEdit(UserDto loginUser, UserDto user);

    /**
     * 保存用户信息和角色信息
     *
     * @param user    用户信息
     * @param roleIds 角色信息
     */
    void saveUserAndRole(UserDto user, String[] roleIds);

    /**
     * 删除用户信息，同时删除用户的角色信息
     *
     * @param user
     */
    void deleteDel(UserDto user);

    /**
     * 微信通过openId登陆
     *
     * @param openId
     * @return
     */
    UserDto getUserByOpenId(String openId);
}

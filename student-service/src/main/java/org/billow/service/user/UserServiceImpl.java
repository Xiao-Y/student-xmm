package org.billow.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.billow.api.user.UserService;
import org.billow.dao.RoleDao;
import org.billow.dao.UserDao;
import org.billow.dao.UserRoleDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;
import org.billow.model.expand.UserRoleDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDto> implements UserService {

    @Resource
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Value("${customerRole}")
    private String roleName;
    //超级系统管理员用户
    @Value("${super.system.admin.user}")
    private String superSystemAdminUser;
    @Value("${super.system.admin.role}")
    private String superSystemAdminRole;

    @Resource
    @Override
    public void setBaseDao(BaseDao<UserDto> baseDao) {
        super.baseDao = this.userDao;
    }

    @Override
    public List<UserDto> findUserList(UserDto user) {
        return userDao.selectAll(user);
    }

    @Override
    public int findUserCount(UserDto user) {
        return userDao.selectAllCount(user);
    }

    @Override
    public UserDto findUserById(Integer id) {
        UserDto dto = new UserDto();
        dto.setUserId(id);
        return super.selectByPrimaryKey(dto);
    }

    @Override
    public UserDto findRoleListByUserId(int userId) {
        UserDto userDto = userDao.findRoleListByUserId(userId);
        if (userDto != null) {
            List<RoleDto> roleDtos = new ArrayList<>();
            List<UserRoleDto> userRoleDtos = userDto.getUserRoleDtos();
            if (ToolsUtils.isNotEmpty(userRoleDtos)) {
                for (UserRoleDto userRoleDto : userRoleDtos) {
                    roleDtos.add(userRoleDto.getRoleDto());
                }
            }
            userDto.setRoleDtos(roleDtos);
        }
        return userDto;
    }

    @Override
    public UserDto findUserByUserNameAndPwd(UserDto user) {
        return userDao.findUserByUserNameAndPwd(user);
    }

    @Override
    public UserDto findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Override
    public void saveRegister(UserDto userDto) {
        userDao.insert(userDto);
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        List<RoleDto> roleDtos = roleDao.selectAll(roleDto);
        if (ToolsUtils.isEmpty(roleDtos)) {
            throw new RuntimeException("customer:客户角色不存在了！");
        }
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUserId(userDto.getUserId());
        userRoleDto.setRoleId(roleDtos.get(0).getId());
        userRoleDao.insert(userRoleDto);
    }

    @Override
    public PageInfo<UserDto> queryUsers(UserDto loginUser, UserDto userDto) {
        //如果不是超级系统管理员不查询出自己
        if (!superSystemAdminUser.equals(loginUser.getUserName())) {
            userDto.setSuperSystemAdminUser(superSystemAdminUser);
        }
        PageHelper.startPage();
        List<UserDto> users = userDao.selectAll(userDto);
        if (ToolsUtils.isNotEmpty(users)) {
            Iterator<UserDto> iterator = users.iterator();
            while (iterator.hasNext()) {
                UserDto user = iterator.next();
                UserDto dto = userDao.findRoleListByUserId(user.getUserId());
                List<UserRoleDto> userRoleDtos = dto.getUserRoleDtos();
                List<String> roles = new ArrayList<>();
                List<RoleDto> roleDtos = new ArrayList<>();
                if (ToolsUtils.isNotEmpty(userRoleDtos)) {
                    for (UserRoleDto userRoleDto : userRoleDtos) {
                        RoleDto role = userRoleDto.getRoleDto();
                        if (role != null) {
                            roleDtos.add(role);
                            roles.add(role.getRemark());
                        }
                    }
                }
                //角色信息放入到用户对象中
                if (ToolsUtils.isNotEmpty(roleDtos)) {
                    user.setRoleDtos(roleDtos);
                }
                //用于页面显示
                if (ToolsUtils.isNotEmpty(roles)) {
                    user.setRoles(StringUtils.join(roles, ","));
                }
            }
        }
        PageInfo<UserDto> page = new PageInfo<>(users);
        return page;
    }

    @Override
    public UserDto userEdit(UserDto loginUser, UserDto user) {
        UserDto userDto = new UserDto();
        //查询所有角色信息
        List<RoleDto> roleDtos = roleDao.selectAll(null);
        // 编辑时，显示数据
        if (user.getUserId() != null) {
            //查询用户的信息和角色
            userDto = userDao.findRoleListByUserId(user.getUserId());
            List<UserRoleDto> userRoleDtos = userDto.getUserRoleDtos();
            if (ToolsUtils.isNotEmpty(userRoleDtos) && ToolsUtils.isNotEmpty(roleDtos)) {
                Iterator<RoleDto> iterator = roleDtos.iterator();
                while (iterator.hasNext()) {
                    RoleDto dto = iterator.next();
                    //如果不是超级系统管理员不查询出自己
                    if (!superSystemAdminUser.equals(loginUser.getUserName()) && superSystemAdminRole.equals(dto.getRoleName())) {
                        iterator.remove();
                        continue;
                    }
                    for (UserRoleDto userRoleDto : userRoleDtos) {
                        RoleDto temp = userRoleDto.getRoleDto();
                        //用户有角色信息时选中
                        if (dto.getId() == temp.getId()) {
                            dto.setChecked(true);
                        }
                    }
                }
            }
        }
        userDto.setRoleDtos(roleDtos);
        return userDto;
    }

    @Override
    public void saveUserAndRole(UserDto user, String[] roleIds) {
        if (user.getUserId() == null) {
            userDao.insert(user);
        } else {
            userDao.updateByPrimaryKeySelective(user);
        }
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        if (ToolsUtils.isNotEmpty(roleIds)) {
            for (String roleId : roleIds) {
                UserRoleDto userRoleDto = new UserRoleDto();
                userRoleDto.setRoleId(new Integer(roleId));
                userRoleDto.setUserId(user.getUserId());
                userRoleDao.insert(userRoleDto);
            }
        }
    }

    @Override
    public void deleteDel(UserDto user) {
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        userDao.deleteByPrimaryKey(user);
    }

    @Override
    public UserDto getUserByOpenId(String openId) {
        return userDao.getUserByOpenId(openId);
    }
}

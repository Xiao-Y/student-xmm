package org.billow.service.user;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDto> implements UserService {

    @Resource
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;

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
        roleDto.setRoleName("customer");
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
    public PageInfo<UserDto> queryUsers(UserDto userDto) {
        PageHelper.startPage();
        List<UserDto> users = userDao.selectAll(userDto);
        if (ToolsUtils.isNotEmpty(users)) {
            for (UserDto user : users) {
                UserDto dto = userDao.findRoleListByUserId(user.getUserId());
                List<RoleDto> roleDtos = dto.getRoleDtos();
                List<String> roles = new ArrayList<>();
                if (ToolsUtils.isNotEmpty(roleDtos)) {
                    for (RoleDto roleDto : roleDtos) {
                        roles.add(roleDto.getRemark());
                    }
                }
                if (ToolsUtils.isNotEmpty(roles)) {
                    user.setRoles(StringUtils.join(roles, ","));
                }
            }
        }
        PageInfo<UserDto> page = new PageInfo<>(users);
        return page;
    }

    @Override
    public UserDto userEdit(UserDto user) {
        UserDto userDto = new UserDto();
        //查询所有角色信息
        List<RoleDto> roleDtos = roleDao.selectAll(null);
        // 编辑时，显示数据
        if (user.getUserId() != null) {
            //查询用户的信息和角色
            userDto = userDao.findRoleListByUserId(user.getUserId());
            List<RoleDto> roleDtoList = userDto.getRoleDtos();
            if (ToolsUtils.isNotEmpty(roleDtoList) && ToolsUtils.isNotEmpty(roleDtos)) {
                for (RoleDto dto : roleDtos) {
                    for (RoleDto temp : roleDtoList) {
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

    }

    @Override
    public void deleteDel(UserDto user) {
        UserDto userDto = userDao.findRoleListByUserId(user.getUserId());
        List<RoleDto> roleDtoList = userDto.getRoleDtos();
        if (ToolsUtils.isNotEmpty(roleDtoList)) {
            for (RoleDto temp : roleDtoList) {
                userRoleDao.deleteUserRoleByRoleId(temp.getId());

            }
        }
        userDao.deleteByPrimaryKey(userDto);
    }
}

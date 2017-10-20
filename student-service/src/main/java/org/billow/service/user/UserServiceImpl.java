package org.billow.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.billow.api.user.UserService;
import org.billow.dao.UserDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;
import org.billow.model.expand.UserRoleDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDto> implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<UserDto> baseDao) {
        super.baseDao = this.userDao;
    }

    @Override
    public void deleteTest() {
        // this.deleteByPrimaryKey(15);
        UserDto record = new UserDto();
        record.setUserId(52);
        record.setUserName("XXXX");
        record.setPhoneNumber("22222");
        this.insert(record);
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
    public UserDto getUserByOpenId(String openId) {
        return userDao.getUserByOpenId(openId);
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
}

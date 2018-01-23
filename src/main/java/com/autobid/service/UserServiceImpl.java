package com.autobid.service;

import javax.annotation.Resource;

import com.autobid.dao.IUserDao;
import com.autobid.model.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;
    @Override
    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }

}
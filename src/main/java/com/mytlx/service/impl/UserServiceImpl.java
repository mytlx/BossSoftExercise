package com.mytlx.service.impl;

import com.mytlx.dao.IUserDao;
import com.mytlx.dao.impl.UserDao;
import com.mytlx.domain.User;
import com.mytlx.service.ILogin;

/**
 * @author TLX
 * @date 2019.11.8
 * @time 18:12
 */
public class UserServiceImpl implements ILogin {



    @Override
    public User login(User user) {
        IUserDao userDao = new UserDao();
        return userDao.login(user);
    }
}

package com.ghc.starter.service.Impl;

import com.ghc.starter.dao.UserDao;
import com.ghc.starter.domain.model.User;
import com.ghc.starter.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getAllUsers() {
        List userList = null;
        try{userList = userDao.getAllUsers();
        }catch(DataAccessException de){
            logger.error(de);
            throw new RuntimeException("数据库访问不到");
        }
        return userList;
    }
}

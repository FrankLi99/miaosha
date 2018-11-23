package com.ghc.starter.dao;

import com.ghc.starter.domain.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao {
    List<User> getAllUsers();
}

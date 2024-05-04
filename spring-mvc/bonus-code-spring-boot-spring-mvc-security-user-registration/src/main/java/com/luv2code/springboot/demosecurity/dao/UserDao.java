package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.Community;
import com.luv2code.springboot.demosecurity.entity.User;

import java.util.List;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);

    List<Community> getFollowedCommunities(String username);
}

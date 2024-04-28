package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.Community;

import java.util.List;

public interface CommunityDao {
    void save(Community community);
    Community findByName(String name);
}

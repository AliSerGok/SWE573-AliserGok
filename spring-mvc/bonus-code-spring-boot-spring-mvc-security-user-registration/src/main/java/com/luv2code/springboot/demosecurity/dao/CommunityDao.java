package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.Community;

import java.util.List;

public interface CommunityDao {
    void save(Community community);
    Community findByName(String name);

    List<Community> getCommunitiesByUserName(String name);
    List<Community> getAllCommunities();
    Community getCommunityById(int id);
    void followCommunityById(int id);
}

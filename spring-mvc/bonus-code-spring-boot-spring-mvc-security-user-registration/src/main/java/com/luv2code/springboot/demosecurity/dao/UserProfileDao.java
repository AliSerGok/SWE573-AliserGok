package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.UserProfile;

public interface UserProfileDao {
    UserProfile findUserProfileByUserName(String username);

    void save(UserProfile theUserProfile);
}

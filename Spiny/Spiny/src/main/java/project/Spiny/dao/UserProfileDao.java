package project.Spiny.dao;


import project.Spiny.entity.UserProfile;

import java.util.List;

public interface UserProfileDao {
    UserProfile findUserProfileByUserName(String username);
    UserProfile findUserProfileById(int id);
    void updateUserProfile(UserProfile userProfile);
    List<UserProfile> getAllUsers();

    void save(UserProfile theUserProfile);
}

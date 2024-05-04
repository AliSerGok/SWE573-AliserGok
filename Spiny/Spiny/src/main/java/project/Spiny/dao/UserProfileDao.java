package project.Spiny.dao;


import project.Spiny.entity.UserProfile;

public interface UserProfileDao {
    UserProfile findUserProfileByUserName(String username);

    void save(UserProfile theUserProfile);
}

package project.Spiny.dao;



import project.Spiny.entity.Community;

import java.util.List;

public interface CommunityDao {
    void save(Community community);
    Community findByName(String name);

    List<Community> getCommunitiesByUserName(String name);
    List<Community> getAllCommunities();
    Community getCommunityById(int id);
    void followCommunityById(int id);
}

package project.Spiny.dao;



import project.Spiny.entity.Community;
import project.Spiny.entity.User;

import java.util.List;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);

    List<Community> getFollowedCommunities(String username);
}

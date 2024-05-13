package project.Spiny.dao;

import project.Spiny.entity.*;

import java.util.List;

public interface SearchDao {
    List<Community> getCommunitiesByKeySearch(Search search);
    List<UserProfile>  getUsersByKeySearch(Search search);
    List<Post>  getPostsByKeySearch(Search search);
}

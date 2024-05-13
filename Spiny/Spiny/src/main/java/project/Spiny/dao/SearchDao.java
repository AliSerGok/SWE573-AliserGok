package project.Spiny.dao;

import project.Spiny.entity.Community;
import project.Spiny.entity.Post;
import project.Spiny.entity.Search;
import project.Spiny.entity.User;

import java.util.List;

public interface SearchDao {
    List<Community> getCommunitiesByKeySearch(Search search);
    List<User>  getUsersByKeySearch(Search search);
    List<Post>  getPostsByKeySearch(Search search);
}

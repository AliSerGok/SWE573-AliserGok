package project.Spiny.dao;

import project.Spiny.entity.Post;

public interface PostDao {
    Post getPostFormByTemplateId(long id);
    void save(Post post);
}

package project.Spiny.dao;

import project.Spiny.entity.DataField;
import project.Spiny.entity.Post;

import java.util.List;

public interface PostDao {
    Post getPostFormByTemplateId(long id);
    void save(Post post);
    void savePostbyDatafields(List<DataField> dataFields);
    void savePostbyDefaultDatafields(List<DataField> dataFields,int id);
    void addlike(int id);
    void addDislike(int id);
    List<Post> getPostFormByUserId(int id);
}

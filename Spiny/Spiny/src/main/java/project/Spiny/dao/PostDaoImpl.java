package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.Spiny.entity.Post;
import project.Spiny.entity.Template;
@Repository
public class PostDaoImpl implements PostDao{
    private EntityManager entityManager;

    @Autowired
    public PostDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Post getPostFormByTemplateId(long id) {
        Template template=entityManager.find(Template.class,id);
        Post post=new Post();
        if(template!=null){
            post.setCommunity(template.getCommunity());
        }
        return new Post(template);
    }

    @Override
    @Transactional
    public void save(Post post) {
        entityManager.persist(post);
    }
}

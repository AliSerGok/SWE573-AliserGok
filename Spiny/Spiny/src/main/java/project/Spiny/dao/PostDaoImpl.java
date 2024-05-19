package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.Spiny.entity.DataField;
import project.Spiny.entity.Post;
import project.Spiny.entity.Template;
import project.Spiny.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao{
    private EntityManager entityManager;
    private UserDao userDao;

    @Autowired
    public PostDaoImpl(EntityManager entityManager,UserDao userDao) {
        this.entityManager = entityManager;
        this.userDao=userDao;
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

    @Override
    @Transactional
    public void savePostbyDatafields(List<DataField> dataFields) {
        List<DataField> dataFieldsNew=new ArrayList<>();
        Post post=new Post();

        long fieldId=dataFields.get(0).getId();
        DataField tempDataField=entityManager.find(DataField.class,fieldId);
        post.setTemplate(tempDataField.getTemplate());
        post.setTitle(tempDataField.getTemplate().getName());
        post.setCommunity(tempDataField.getTemplate().getCommunity());

        String templateName;
        int templateId;

        for (DataField n: dataFields){
            DataField dataField=entityManager.find(DataField.class,n.getId());
            DataField d=new DataField();

            d.setName(dataField.getName());
            d.setInputValue(n.getInputValue());
            d.setRequired(true);
            d.setValue(dataField.getValue());
            d.setPost(post);
            dataFieldsNew.add(d);
        }
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userDao.findByUserName(userName);
        post.setUser(user);
        post.setDataFields(dataFieldsNew);
        post.setCreationDate(LocalDateTime.now());

        entityManager.persist(post);


    }

    @Override
    @Transactional
    public void addlike(int id) {
        Post post=entityManager.find(Post.class,id);
        post.addLike();
        entityManager.merge(post);
    }

    @Override
    @Transactional
    public void addDislike(int id) {
        Post post=entityManager.find(Post.class,id);
        post.addDislike();
        entityManager.merge(post);
    }
}

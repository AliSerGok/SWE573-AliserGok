package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.Spiny.entity.Community;
import project.Spiny.entity.DataField;
import project.Spiny.entity.Template;
import project.Spiny.entity.User;

import java.util.List;

@Repository
public class TemplateDaoImpl implements TemplateDao{
    private EntityManager entityManager;
    UserDao userDao;
    @Autowired
    public TemplateDaoImpl(EntityManager entityManager, UserDao userDao) {
        this.entityManager = entityManager;
        this.userDao=userDao;
    }


    @Override
    @Transactional
    public void saveTemplateByCommunityId(Template template, int communityId, List<DataField> dataFields) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user=userDao.findByUserName(userName);
        Community community= entityManager.find(Community.class,communityId);

        template.setOwner(user);
        template.setCommunity(community);

        for (DataField d: dataFields){
            d.setTemplate(template);
        }
        template.setDataFields(dataFields);
        entityManager.persist(template);

    }

    @Override
    public Template getTemplateById(int id) {
        return entityManager.find(Template.class,id);
    }
}

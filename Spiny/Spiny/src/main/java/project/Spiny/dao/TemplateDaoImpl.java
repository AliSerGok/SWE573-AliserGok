package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.Spiny.entity.Template;

@Repository
public class TemplateDaoImpl implements TemplateDao{
    private EntityManager entityManager;
    @Autowired
    public TemplateDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void saveOrUpdate(Template template) {
        Template theTemplate=entityManager.find(Template.class,template);
        if(theTemplate==null){
            entityManager.persist(template);
        }
        entityManager.merge(template);
    }
}

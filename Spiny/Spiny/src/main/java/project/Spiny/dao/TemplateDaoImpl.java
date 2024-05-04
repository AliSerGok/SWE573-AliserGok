package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateDaoImpl implements TemplateDao{
    private EntityManager entityManager;
    @Autowired
    public TemplateDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}

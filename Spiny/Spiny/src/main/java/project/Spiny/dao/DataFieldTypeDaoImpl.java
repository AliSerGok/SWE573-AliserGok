package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.Spiny.entity.DataFieldType;

import java.util.List;

@Repository
public class DataFieldTypeDaoImpl implements DataFieldTypeDao{

    private EntityManager entityManager;

    @Autowired
    public DataFieldTypeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DataFieldType> getAllDataFieldType() {
        TypedQuery<DataFieldType> query = entityManager.createQuery("select d from DataFieldType d", DataFieldType.class);
        return query.getResultList();
    }

    @Override
    public DataFieldType getDataFieldTypeById(long id) {
        return entityManager.find(DataFieldType.class, id);
    }
}

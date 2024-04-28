package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.Community;
import com.luv2code.springboot.demosecurity.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class CommunityDaoImpl implements CommunityDao{
    EntityManager entityManager;

    public CommunityDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Community community) {
        entityManager.persist(community);
    }


    @Override
    public Community findByName(String name) {

        // retrieve/read from database using username
        TypedQuery<Community> theQuery = entityManager.createQuery("from Community where name=:name", Community.class);
        theQuery.setParameter("name", name);

        Community theCommunity = null;
        try {
            theCommunity = theQuery.getSingleResult();
        } catch (Exception e) {
            theCommunity = null;
        }

        return theCommunity;
    }
}

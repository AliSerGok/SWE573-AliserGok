package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.Community;
import com.luv2code.springboot.demosecurity.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class CommunityDaoImpl implements CommunityDao{
    private EntityManager entityManager;
    private UserDao userDao;
    @Autowired
    public CommunityDaoImpl(EntityManager entityManager, UserDao userDao) {
        this.entityManager = entityManager;
        this.userDao=userDao;
    }

    @Override
    @Transactional
    public void save(Community community) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userDao.findByUserName(userName);
        community.setOwner(user);
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

    @Override
    public List<Community> getCommunitiesByUserName(String username) {
        User user=userDao.findByUserName(username);


        TypedQuery<Community> theQuery = entityManager.createQuery("from Community where owner=:data", Community.class);
        theQuery.setParameter("data", user);

        List<Community> theCommunities;
        try {
            theCommunities = theQuery.getResultList();
        } catch (Exception e) {
            theCommunities = null;
        }

        return theCommunities;
    }
}

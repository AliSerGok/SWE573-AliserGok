package project.Spiny.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.Spiny.entity.User;
import project.Spiny.entity.UserProfile;

import java.util.List;

@Repository
public class UserProfileDaoImpl implements UserProfileDao{
    private EntityManager entityManager;
    private UserDao userDao;


    @Autowired
    public UserProfileDaoImpl(EntityManager entityManager, UserDao userDao) {
        this.entityManager = entityManager;

    }




    @Override
    public UserProfile findUserProfileByUserName(String username) {

        TypedQuery<UserProfile> theQuery = entityManager.createQuery("from UserProfile where user.userName=:uName", UserProfile.class);
        theQuery.setParameter("uName", username);

        UserProfile theUserProfile;
        try {
            theUserProfile = theQuery.getSingleResult();
        } catch (Exception e) {
            theUserProfile = null;
        }

        return theUserProfile;
    }

    @Override
    public UserProfile findUserProfileById(int id) {

        return entityManager.find(UserProfile.class,id);
    }

    @Override
    @Transactional
    public void updateUserProfile(UserProfile userProfile) {
        UserProfile userProfileMerge=entityManager.find(UserProfile.class,userProfile.getId());

        userProfileMerge.setName(userProfile.getName());
        userProfileMerge.setDescription(userProfile.getDescription());
        userProfileMerge.setAge(userProfile.getAge());
        userProfileMerge.setCity(userProfile.getCity());
        userProfileMerge.setGender(userProfile.getGender());
        entityManager.merge(userProfileMerge);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        TypedQuery<UserProfile> theQuery= entityManager.createQuery("select u from UserProfile u", UserProfile.class);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(UserProfile theUserProfile) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userDao.findByUserName(userName);
        theUserProfile.setUser(user);
        entityManager.persist(theUserProfile);

    }
}

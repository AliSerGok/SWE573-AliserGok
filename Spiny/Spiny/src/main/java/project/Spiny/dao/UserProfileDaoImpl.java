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

@Repository
public class UserProfileDaoImpl implements UserProfileDao{
    private EntityManager entityManager;
    private UserDao userDao;


    @Autowired
    public UserProfileDaoImpl(EntityManager entityManager, UserDao userDao) {
        this.entityManager = entityManager;
        this.userDao = userDao;

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
    @Transactional
    public void save(UserProfile theUserProfile) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userDao.findByUserName(userName);
        theUserProfile.setUser(user);
        entityManager.persist(theUserProfile);

    }
}

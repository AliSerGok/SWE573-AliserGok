package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.Spiny.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {

    EntityManager entityManager;

    @Autowired
    public SearchDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Community> getCommunitiesByKeySearch(Search search) {
        if(search == null || search.getKeyword() == null){
            return Collections.emptyList();
        }
        String keyWord=search.getKeyword();
        if(search.getSearchInCommunity()){
            TypedQuery<Community> theQuery=entityManager.createQuery("SELECT c FROM Community c WHERE c.name LIKE CONCAT('%', :keyword, '%')", Community.class);
            theQuery.setParameter("keyword", keyWord);
            List<Community> communityByTitles=theQuery.getResultList();
            if(search.getInDescriptions()){
                TypedQuery<Community> theQuery2=entityManager.createQuery("SELECT c FROM Community c WHERE c.description LIKE CONCAT('%', :keyword, '%')", Community.class);
                theQuery2.setParameter("keyword", keyWord);
                List<Community> communityByDescriptions=theQuery2.getResultList();

                for (Community community : communityByTitles) {
                    if (communityByDescriptions.contains(community)) {
                        continue;
                    }else {
                        communityByDescriptions.add(community);
                    }
                }
                return communityByDescriptions;
            }else {
                return communityByTitles;
            }
        }
        return null;
    }

    @Override
    public List<UserProfile> getUsersByKeySearch(Search search) {
        if(search == null || search.getKeyword() == null){
            return Collections.emptyList();
        }
        String keyWord=search.getKeyword();
        List<UserProfile> userFoundList=new ArrayList<>();
        if(search.getSearchInPeople()){
            TypedQuery<UserProfile> theQuery=entityManager.createQuery("SELECT u FROM UserProfile u WHERE u.name LIKE CONCAT('%', :keyword, '%')", UserProfile.class);
            theQuery.setParameter("keyword",keyWord);
            userFoundList=theQuery.getResultList();

        }
        return userFoundList;
    }

    @Override
    public List<User> getUsersByKeySearch(Search search, int id) {
        if(search == null || search.getKeyword() == null){
            return Collections.emptyList();
        }
        String keyWord=search.getKeyword();
        List<User> userFoundList=new ArrayList<>();
        if(search.getSearchInPeople()){
            TypedQuery<User> theQuery=entityManager.createQuery("SELECT DISTINCT u FROM User u " +
                    "JOIN u.followedCommunities c " + // Assuming 'communities' is the name of the collection in User entity representing the communities they follow
                    "WHERE u.userName LIKE CONCAT('%', :keyword, '%') " +
                    "AND c.id = :communityId", User.class);
            theQuery.setParameter("keyword",keyWord);
            theQuery.setParameter("communityId", id);
            userFoundList=theQuery.getResultList();

        }
        return userFoundList;
    }

    @Override
    public List<Post> getPostsByKeySearch(Search search) {
        return null;
    }

    @Override
    public List<Post> getPostsByKeySearch(Search search, int id) {
        return null;
    }
}

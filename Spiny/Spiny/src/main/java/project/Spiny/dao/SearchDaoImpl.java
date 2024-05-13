package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.Spiny.entity.Community;
import project.Spiny.entity.Post;
import project.Spiny.entity.Search;
import project.Spiny.entity.User;

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
    public List<User> getUsersByKeySearch(Search search) {
        return null;
    }

    @Override
    public List<Post> getPostsByKeySearch(Search search) {
        return null;
    }
}

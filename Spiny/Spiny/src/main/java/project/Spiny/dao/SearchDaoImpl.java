package project.Spiny.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.Spiny.entity.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        if (search == null || search.getKeyword() == null) {
            return Collections.emptyList();
        }
        String keyWord = search.getKeyword();
        if (search.getSearchInCommunity()) {
            TypedQuery<Community> theQuery = entityManager.createQuery("SELECT c FROM Community c WHERE c.name LIKE CONCAT('%', :keyword, '%')", Community.class);
            theQuery.setParameter("keyword", keyWord);
            List<Community> communityByTitles = theQuery.getResultList();
            if (search.getInDescriptions()) {
                TypedQuery<Community> theQuery2 = entityManager.createQuery("SELECT c FROM Community c WHERE c.description LIKE CONCAT('%', :keyword, '%')", Community.class);
                theQuery2.setParameter("keyword", keyWord);
                List<Community> communityByDescriptions = theQuery2.getResultList();

                for (Community community : communityByTitles) {
                    if (communityByDescriptions.contains(community)) {
                        continue;
                    } else {
                        communityByDescriptions.add(community);
                    }
                }
                return communityByDescriptions;
            } else {
                return communityByTitles;
            }
        }
        return null;
    }

    @Override
    public List<UserProfile> getUsersByKeySearch(Search search) {
        if (search == null || search.getKeyword() == null) {
            return Collections.emptyList();
        }
        String keyWord = search.getKeyword();
        List<UserProfile> userFoundList = new ArrayList<>();
        if (search.getSearchInPeople()) {
            TypedQuery<UserProfile> theQuery = entityManager.createQuery("SELECT u FROM UserProfile u WHERE u.name LIKE CONCAT('%', :keyword, '%')", UserProfile.class);
            theQuery.setParameter("keyword", keyWord);
            userFoundList = theQuery.getResultList();

        }
        return userFoundList;
    }

    @Override
    public List<User> getUsersByKeySearch(Search search, int id) {
        if (search == null || search.getKeyword() == null) {
            return Collections.emptyList();
        }
        String keyWord = search.getKeyword();
        List<User> userFoundList = new ArrayList<>();
        if (search.getSearchInPeople()) {
            TypedQuery<User> theQuery = entityManager.createQuery("SELECT DISTINCT u FROM User u " +
                    "JOIN u.followedCommunities c " + // Assuming 'communities' is the name of the collection in User entity representing the communities they follow
                    "WHERE u.userName LIKE CONCAT('%', :keyword, '%') " +
                    "AND c.id = :communityId", User.class);
            theQuery.setParameter("keyword", keyWord);
            theQuery.setParameter("communityId", id);
            userFoundList = theQuery.getResultList();

        }
        return userFoundList;
    }

    @Override
    public List<Post> getPostsByDate(Search search, int id) {

        List<Post> postsFoundList = new ArrayList<>();
        String dateStr=search.getPostDate();

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       LocalDate dateTime = LocalDate.parse(dateStr, formatter);

        TypedQuery<Post> theQuery = entityManager.createQuery("SELECT DISTINCT p FROM Post p " +
                "WHERE p.creationDate > :date " +
                "AND p.community.id = :communityId", Post.class);
        theQuery.setParameter("date", dateTime);
        theQuery.setParameter("communityId", id);
        postsFoundList = theQuery.getResultList();


        return postsFoundList;
    }


    @Override
    public List<Post> getPostsByKeySearch(Search search, int id) {
        if (search == null || search.getKeyword() == null) {
            System.out.println("null");
            return Collections.emptyList();
        }
        String keyWord = search.getKeyword();
        String temValue = search.getFieldName();
        System.out.println("Burada 1");
        System.out.println(id);
        System.out.println(keyWord);

        // post title a göre
        List<Post> postsFoundList = new ArrayList<>();
        if (search.getSearchInPosts() && !search.getSearchWithTemplates() && !search.getSearchWithDataField()) {
            TypedQuery<Post> theQuery = entityManager.createQuery("SELECT DISTINCT p FROM Post p " +
                    "WHERE p.title LIKE CONCAT('%', :keyword, '%') " +
                    "AND p.community.id = :communityId", Post.class);
            theQuery.setParameter("keyword", keyWord);
            theQuery.setParameter("communityId", id);
            postsFoundList = theQuery.getResultList();

        }
        // post title ve template
        else if (search.getSearchInPosts() && search.getSearchWithTemplates() && !search.getSearchWithDataField()) {
            TypedQuery<Post> theQuery = entityManager.createQuery("SELECT DISTINCT p FROM Post p " +
                    "WHERE p.title LIKE CONCAT('%', :keyword, '%') " +
                    "AND p.community.id = :communityId " +
                    "AND p.template.id = :templateId", Post.class);
            theQuery.setParameter("keyword", keyWord);
            theQuery.setParameter("communityId", id);
            theQuery.setParameter("templateId", search.getTemplateId());
            postsFoundList = theQuery.getResultList();
        }
        // Data field ve input value ve getAll true
        else if (!search.getSearchInPosts() && !search.getSearchWithTemplates() && search.getAllFields() && search.getSearchWithDataField()) {
            TypedQuery<Post> theQuery = entityManager.createQuery(
                    "SELECT DISTINCT p FROM Post p " +
                            "JOIN p.dataFields df " +
                            "WHERE df.inputValue LIKE CONCAT('%', :keyword, '%') " +
                            "AND p.community.id = :communityId ", Post.class);
            theQuery.setParameter("keyword", keyWord);
            theQuery.setParameter("communityId", id);
            postsFoundList = theQuery.getResultList();
        }
        // Data field ve input value ve getAll false
        else if (!search.getSearchInPosts() && !search.getSearchWithTemplates() && !search.getAllFields() && search.getSearchWithDataField()) {
            TypedQuery<Post> theQuery = entityManager.createQuery(
                    "SELECT DISTINCT p FROM Post p " +
                            "JOIN p.dataFields df " +
                            "WHERE df.inputValue LIKE CONCAT('%', :keyword, '%') " +
                            "AND df.value = :value " +
                            "AND p.community.id = :communityId ", Post.class);
            theQuery.setParameter("keyword", keyWord);
            theQuery.setParameter("value", temValue);
            theQuery.setParameter("communityId", id);
            postsFoundList = theQuery.getResultList();
        }
        //
        else if (search.getAllFields() && !search.getSearchWithTemplates()) {
            TypedQuery<Post> theQuery = entityManager.createQuery(
                    "SELECT DISTINCT p FROM Post p " +
                            "JOIN p.dataFields df " +
                            "WHERE df.inputValue LIKE CONCAT('%', :keyword, '%') " +
                            "AND p.community.id = :communityId ", Post.class);
            theQuery.setParameter("keyword", keyWord);
            theQuery.setParameter("communityId", id);
            postsFoundList = theQuery.getResultList();
        }
        else if (search.getAllFields() && search.getSearchWithTemplates() && search.getSearchWithDataField()) {
            TypedQuery<Post> theQuery = entityManager.createQuery(
                    "SELECT DISTINCT p FROM Post p " +
                            "JOIN p.dataFields df " + // DataFields'a JOIN işlemi uygulanır ve df alias'ı atanır
                            "WHERE df.inputValue LIKE CONCAT('%', :keyword, '%') " + // DataField'ın name özelliği aranır
                            "AND p.community.id = :communityId " +
                            "AND p.template.id = :templateId", Post.class);
            theQuery.setParameter("keyword", keyWord);
            theQuery.setParameter("templateId", search.getTemplateId());
            theQuery.setParameter("communityId", id);
            postsFoundList = theQuery.getResultList();
        }

        return postsFoundList;
    }


}

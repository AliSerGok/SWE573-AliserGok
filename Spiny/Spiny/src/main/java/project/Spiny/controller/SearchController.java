package project.Spiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.SearchDao;
import project.Spiny.entity.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    SearchDao searchDao;

    @Autowired
    public SearchController(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @GetMapping("/searchForm")
    public String searchForm(Model model){
        Search search=new Search();
        model.addAttribute("search",search);
        return "search/search-form";
    }

    @PostMapping("/processSearchForm")
    public String processSearchForm(@ModelAttribute("search") Search search, Model model){
        System.out.println("successes");
        if(search!=null && search.getSearchInCommunity()){
            List<Community> communities=searchDao.getCommunitiesByKeySearch(search);
            model.addAttribute("communitiesFound",communities);
        }
        if(search!=null && search.getSearchInPeople()){
            List<UserProfile> people=searchDao.getUsersByKeySearch(search);
            model.addAttribute("peopleFound",people);
        }
        if(search!=null && search.getSearchInPosts()){
            List<Post> posts=searchDao.getPostsByKeySearch(search);
            model.addAttribute("postsFound",posts);
        }

        return "search/search-results-form";
    }

    @PostMapping("/processCommunitySearchForm")
    public String processCommunitySearchForm(@ModelAttribute("search") Search search,
                                             @RequestParam("communityId")int communityId,
                                             Model model){
        System.out.println("successes");
        System.out.println(communityId);

        if(search!=null && search.getSearchInPeople()){
            List<User> people=searchDao.getUsersByKeySearch(search,communityId);
            model.addAttribute("peopleFound",people);
        }
        if(search!=null && search.getSearchInPosts()){
            List<Post> posts=searchDao.getPostsByKeySearch(search,communityId);
            model.addAttribute("postsFound",posts);
            System.out.println(posts);
        }


        return "search/community-search-results";
    }







}

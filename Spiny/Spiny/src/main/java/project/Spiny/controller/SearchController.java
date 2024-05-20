package project.Spiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.SearchDao;
import project.Spiny.entity.*;

import java.util.ArrayList;
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


        return "search/search-results-form";
    }

    @PostMapping("/processCommunitySearchForm")
    public String processCommunitySearchForm(@ModelAttribute("search") Search search,
                                             @RequestParam("communityId")int communityId,
                                             Model model){
        System.out.println("successes");
        System.out.println(communityId);
        System.out.println(search.getPostDate());

        if(search!=null && search.getSearchInPeople()){
            List<User> people=searchDao.getUsersByKeySearch(search,communityId);
            model.addAttribute("peopleFound",people);
        }
        List<Post> posts2=new ArrayList<>();
        if(search!=null){
            List<Post> posts=searchDao.getPostsByKeySearch(search,communityId);

            if(search.getPostDate()!=null&& search.getSearchByDate()&&search.getPostDate()!=null){
                posts2=searchDao.getPostsByDate(search,communityId);
                for(Post p: posts2){
                    if (!posts.contains(p)) {
                        posts.add(p);
                    }
                }
            }

            model.addAttribute("postsFound",posts);


        }


        model.addAttribute("communityId",communityId);



        return "search/community-search-results";
    }







}

package project.Spiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.SearchDao;
import project.Spiny.entity.Community;
import project.Spiny.entity.Post;
import project.Spiny.entity.Search;
import project.Spiny.entity.User;

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
        List<Community> communities=searchDao.getCommunitiesByKeySearch(search);
        List<User> people=searchDao.getUsersByKeySearch(search);
        List<Post> posts=searchDao.getPostsByKeySearch(search);

        return "search/search-form";
    }








}

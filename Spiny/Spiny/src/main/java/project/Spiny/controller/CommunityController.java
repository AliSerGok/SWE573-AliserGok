package project.Spiny.controller;


import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.CommunityDao;
import project.Spiny.entity.Community;
import project.Spiny.entity.Search;

import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

    private CommunityDao communityDao;

    public CommunityController(CommunityDao communityDao) {
        this.communityDao = communityDao;
    }

    @GetMapping("/createCommunity")
    public String createCommunityForm(Model theModel){
        Community community=new Community();
        theModel.addAttribute("community", community);
        return "community/create-community-form";
    }

    @GetMapping("/showCreatedCommunities")
    public String showCreatedCommunities(Model theModel){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        List<Community> communities=communityDao.getCommunitiesByUserName(username);
        theModel.addAttribute("communities", communities);
        return "community/show-created-communities";
    }

    @GetMapping("/showAllCommunities")
    public String showAllCommunities(Model theModel){

        List<Community> communities=communityDao.getAllCommunities();
        theModel.addAttribute("communities", communities);
        return "community/show-all-communities";
    }


    @GetMapping("/showCommunity")
    public String showCommunity(@RequestParam("communityId") int theId,Model theModel){
        Community community=communityDao.getCommunityById(theId);
        Search search=new Search();
        if(community!=null){
            theModel.addAttribute("community", community);
            theModel.addAttribute("search", search);
            return "community/community-page";
        }
        return "exceptions/access-denied";
    }


    @GetMapping("/updateCommunityPage")
    public String updateCommunityPage(@RequestParam("communityId") int theId,Model theModel){
        Community community=communityDao.getCommunityById(theId);
        if(community!=null){
            theModel.addAttribute("community", community);
            return "community/update-community-page";
        }
        return "exceptions/access-denied";
    }

    @PostMapping("/processCommunity")
    public String saveCommunity(@Valid @ModelAttribute("community") Community theCommunity){

        communityDao.save(theCommunity);
        return "community/community-page";
    }

    @PostMapping("/updateCommunity")
    public String updateCommunity(@Valid @ModelAttribute("community") Community theCommunity,Model theModel){

        communityDao.update(theCommunity);
        Community community=communityDao.getCommunityById(theCommunity.getId());
        if(community!=null){
            theModel.addAttribute("community", community);
            return "community/community-page";
        }
        return "exceptions/access-denied";
    }

    @GetMapping("/followCommunity")
    public String followCommunity(@RequestParam("communityId") int theId,Model theModel){

        communityDao.followCommunityById(theId);

        Community community=communityDao.getCommunityById(theId);
        theModel.addAttribute("community", community);
        return "community/community-page";
    }

}

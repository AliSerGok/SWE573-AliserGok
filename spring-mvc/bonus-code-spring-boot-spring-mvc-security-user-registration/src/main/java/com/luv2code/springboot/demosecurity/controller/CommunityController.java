package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.dao.CommunityDao;
import com.luv2code.springboot.demosecurity.entity.Community;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/processCommunity")
    public String saveCommunity(@Valid @ModelAttribute("community") Community theCommunity){
        String name=theCommunity.getName();
        Community community =communityDao.findByName(name);
        System.out.println(name);
        if(community!=null){
            return "community/creation-denied";
        }
        communityDao.save(theCommunity);
        return "community/creation-confirm";
    }

}

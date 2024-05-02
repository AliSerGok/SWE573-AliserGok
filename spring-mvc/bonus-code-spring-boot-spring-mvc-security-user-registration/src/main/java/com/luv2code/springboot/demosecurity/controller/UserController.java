package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.dao.UserProfileDao;
import com.luv2code.springboot.demosecurity.entity.UserProfile;
import com.luv2code.springboot.demosecurity.user.WebUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserProfileDao userProfileDao;

    @Autowired
    public UserController(UserProfileDao userProfileDao) {
        this.userProfileDao=userProfileDao;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @GetMapping("/showProfile")
    public String showUserProfile(Model theModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        System.out.println(username);
        UserProfile userProfile=userProfileDao.findUserProfileByUserName(username);
        theModel.addAttribute("UserProfile",userProfile);
        return "user/userProfile";

    }

    @GetMapping("/createProfile")
    public String showUserProfileForm(Model theModel){
        UserProfile userProfile=new UserProfile();
        theModel.addAttribute("UserProfile",userProfile);
        return "user/userProfile-form";

    }
    @PostMapping("/saveUserProfile")
    public String saveUserProfile(@Valid @ModelAttribute("UserProfile") UserProfile theUserProfile,
                                  BindingResult theBindingResult){
        userProfileDao.save(theUserProfile);
        return "redirect:/user/showProfile";
    }
}

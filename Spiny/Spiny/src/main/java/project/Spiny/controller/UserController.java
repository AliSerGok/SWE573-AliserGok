package project.Spiny.controller;


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
import project.Spiny.dao.UserDao;
import project.Spiny.dao.UserProfileDao;
import project.Spiny.entity.Community;
import project.Spiny.entity.UserProfile;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserProfileDao userProfileDao;
    private UserDao userDao;

    @Autowired
    public UserController(UserProfileDao userProfileDao, UserDao userDao) {
        this.userProfileDao=userProfileDao;
        this.userDao=userDao;
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

    @GetMapping("/showFollowedCommunities")
    public String showFollowedCommunities(Model theModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        List<Community> communities=userDao.getFollowedCommunities(username);
        theModel.addAttribute("communities", communities);
        return "community/show-followed-communities";
    }

    @PostMapping("/saveUserProfile")
    public String saveUserProfile(@Valid @ModelAttribute("UserProfile") UserProfile theUserProfile,
                                  BindingResult theBindingResult){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserProfile userProfile=userProfileDao.findUserProfileByUserName(username);
        if(userProfile==null){
        userProfileDao.save(theUserProfile);
            return "redirect:/user/showProfile";
        }
        return "redirect:/user/createProfile";
    }
}

package project.Spiny.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.Spiny.entity.Search;

@Controller
public class HomePageController {

    private Authentication authentication=SecurityContextHolder.getContext().getAuthentication();



    @GetMapping("/")
    public String showHome(Model model) {
        Search search=new Search();
        model.addAttribute("search",search);

        return "home";
    }

    // add a request mapping for /leaders

    @GetMapping("/leaders")
    public String showLeaders() {

        return "leaders";
    }

    // add request mapping for /systems

    @GetMapping("/systems")
    public String showSystems() {

        return "systems";
    }

}










package project.Spiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.Spiny.dao.PostDao;
import project.Spiny.entity.Post;
import project.Spiny.entity.Template;

@Controller
@RequestMapping("/post")
public class PostController {
    private PostDao postDao;

    @Autowired
    public PostController(PostDao postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/showPostForm")
    public String showPostForm(@RequestParam("templateId")int id, Model theModel){
        Post thePost=postDao.getPostFormByTemplateId(id);
        theModel.addAttribute(thePost);
        return "post/post-form";
    }

    @PostMapping("/processPostForm")
    public String savePost(@RequestParam("post")Post post){
        postDao.save(post);
        return "redirect:/user/showProfile";
    }
}

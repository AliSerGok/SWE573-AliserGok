package project.Spiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.PostDao;
import project.Spiny.dao.TemplateDao;
import project.Spiny.entity.DataField;
import project.Spiny.entity.Post;
import project.Spiny.entity.Template;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private PostDao postDao;
    private TemplateDao templateDao;

    @Autowired
    public PostController(PostDao postDao, TemplateDao templateDao) {
        this.postDao = postDao;
        this.templateDao=templateDao;
    }

    @GetMapping("/showPostForm")
    public String showPostForm(@RequestParam("templateId")int id, Model theModel){
        Post thePost=new Post();
        Template theTemplate=templateDao.getTemplateById(id);
        List<DataField> dataFields=theTemplate.getDataFields();



        thePost.setTitle(theTemplate.getName());
        thePost.setDataFields(dataFields);

        theModel.addAttribute("post", thePost);
        theModel.addAttribute("modelDataFields",dataFields);
        return "post/post-form";
    }

    @PostMapping("/processPostForm")
    public String savePost(@ModelAttribute("post")Post post){
        System.out.println(post.getDataFields());
        return "redirect:/user/showProfile";
    }
}

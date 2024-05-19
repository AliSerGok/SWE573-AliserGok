package project.Spiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.PostDao;
import project.Spiny.dao.TemplateDao;
import project.Spiny.entity.DataField;
import project.Spiny.entity.Post;
import project.Spiny.entity.Template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.println(dataFields);


        thePost.setTitle(theTemplate.getName());
        thePost.setCommunity(theTemplate.getCommunity());

        theModel.addAttribute("post", thePost);
        theModel.addAttribute("DataFields",dataFields);
        return "post/post-form";
    }

    @PostMapping("/processPostForm")
    public String savePost(@RequestBody MultiValueMap<String,String> formdata,@RequestParam("communityId")int communityId) {

        List<DataField> dataFields=new ArrayList<>();

        int index = 0;
        while (formdata.containsKey("id." + index) && formdata.containsKey("value." + index)) {
            DataField newDataField=new DataField();
            long dfid = Integer.parseInt(formdata.getFirst("id." + index));
            newDataField.setId(dfid);
            String dfvalue = formdata.getFirst("value." + index);
            newDataField.setInputValue(dfvalue);
            dataFields.add(newDataField);
            index++;
        }
        System.out.println(dataFields);
        postDao.savePostbyDatafields(dataFields);
        return "redirect:/community/showCommunity?communityId=" + communityId;
    }

    @PostMapping("/addLike")
    public String addLike(@RequestParam("postId")int postId,@RequestParam("communityId")int communityId){
        System.out.println(communityId);
        postDao.addlike(postId);
        return "redirect:/community/showCommunity?communityId=" + communityId;
    }

    @PostMapping("/addDislike")
    public String addDislike(@RequestParam("postId")int postId,@RequestParam("communityId")int communityId){
        System.out.println(communityId);
        postDao.addDislike(postId);
        return "redirect:/community/showCommunity?communityId=" + communityId;
    }
}

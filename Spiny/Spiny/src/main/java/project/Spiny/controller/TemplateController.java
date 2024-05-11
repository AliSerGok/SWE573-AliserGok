package project.Spiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.DataFieldDao;
import project.Spiny.dao.DataFieldTypeDao;
import project.Spiny.dao.TemplateDao;
import project.Spiny.entity.DataField;
import project.Spiny.entity.DataFieldType;
import project.Spiny.entity.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/template")
public class TemplateController {
    TemplateDao templateDao;
    DataFieldDao dataFieldDao;
    DataFieldTypeDao dataFieldTypeDao;


    @Autowired
    public TemplateController(TemplateDao templateDao, DataFieldDao dataFieldDao, DataFieldTypeDao dataFieldTypeDao) {
        this.templateDao = templateDao;
        this.dataFieldDao = dataFieldDao;
        this.dataFieldTypeDao = dataFieldTypeDao;
    }

    @GetMapping("/templateForm")
    public String templateForm(Model theModel){
        Template theTemplate=new Template();
        List<DataFieldType> dataFieldTypes=dataFieldTypeDao.getAllDataFieldType();
        DataField dataField=new DataField();
        theModel.addAttribute("dataFieldTypes",dataFieldTypes);
        theModel.addAttribute("template",theTemplate);
        theModel.addAttribute("dataField",dataField);

        return "template/test";
    }




    // Spring controller
    @PostMapping("/processTemplate")
    public String createTemplate(@RequestParam(value = "template") Template template) {

        templateDao.saveOrUpdate(template);

        return "redirect:/template/templateForm";
    }





}


package project.Spiny.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.Spiny.dao.DataFieldDao;
import project.Spiny.dao.DataFieldTypeDao;
import project.Spiny.dao.TemplateDao;
import project.Spiny.entity.DataField;
import project.Spiny.entity.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        theModel.addAttribute("template",theTemplate);


        return "template/template-form";
    }

    @PostMapping("/processTemplate")
    public String processTemplate(@ModelAttribute("template") Template template,
                                  @RequestParam("dataFieldList") String dataFieldListJson,
                                  Model model)  {

        System.out.println(template);
        System.out.println(dataFieldListJson);

        // JSON formatındaki dataFieldList'i Java listesine dönüştür
        ObjectMapper objectMapper = new ObjectMapper();
        List<DataField> dataFields = null;
        try {
            dataFields = objectMapper.readValue(dataFieldListJson, new TypeReference<List<DataField>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DataField listesini yazdır
        if (dataFields != null) {
            for (DataField dataField : dataFields) {
                String fieldName = dataField.getName();
                String fieldValue = dataField.getValue();
                System.out.println("Field Name: " + fieldName + ", Field Value: " + fieldValue);
            }
        }
        System.out.println(template);
        System.out.println(dataFieldListJson);

        // Template ve dataField listesini model'e ekle
        model.addAttribute("template", template);
        model.addAttribute("dataFields", dataFields);

        // İşlem tamamlandıktan sonra bir başka sayfaya yönlendirme yapabilirsiniz
        return "redirect:/success";
    }
}








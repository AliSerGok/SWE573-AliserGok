package project.Spiny.dao;

import project.Spiny.entity.DataField;
import project.Spiny.entity.Template;

import java.util.List;

public interface TemplateDao {
    void saveTemplateByCommunityId(Template template, int communityId, List<DataField> dataFields);
    Template getTemplateById(int id);
}

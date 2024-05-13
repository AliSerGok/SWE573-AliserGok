package project.Spiny.dao;

import project.Spiny.entity.Template;

public interface TemplateDao {
    void saveTemplateByCommunityId(Template template,int communityId);
}

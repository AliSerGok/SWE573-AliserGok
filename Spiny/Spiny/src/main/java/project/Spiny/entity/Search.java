package project.Spiny.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Search {
    private String keyword;
    private Boolean searchInCommunity;
    private Boolean searchInPeople;
    private Boolean searchInPosts;
    private Boolean searchWithTemplates;
    private Boolean searchWithDataField;
    private Boolean allFields;
    private Boolean searchByDate;
    private int templateId;
    private String fieldName;

    private Boolean inDescriptions;
    private String postDate;

    @Override
    public String toString() {
        return "Search{" +
                "keyword='" + keyword + '\'' +
                ", searchInCommunity=" + searchInCommunity +
                ", searchInPeople=" + searchInPeople +
                ", searchInPosts=" + searchInPosts +
                ", searchWithTemplates=" + searchWithTemplates +
                ", searchWithDataField=" + searchWithDataField +
                ", templateId=" + templateId +
                ", fieldName='" + fieldName + '\'' +
                ", inDescriptions=" + inDescriptions +
                ", postDate='" + postDate + '\'' +
                '}';
    }
}

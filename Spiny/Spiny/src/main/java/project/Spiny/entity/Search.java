package project.Spiny.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Search {
    private String keyword;
    private Boolean searchInCommunity;
    private Boolean searchInPeople;
    private Boolean searchInPosts;
    private Boolean searchWithTemplates;
    private int templateId;

    private Boolean inDescriptions;
    private String postDate;

}

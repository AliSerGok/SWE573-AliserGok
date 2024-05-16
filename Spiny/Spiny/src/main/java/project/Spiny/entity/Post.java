package project.Spiny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "post_data_field",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "data_field_id")
    )
    private List<DataField> dataFields=new ArrayList<>();

    public Post() {
        DataField dataFieldContent=new DataField();
        dataFieldContent.setName("Content");
        dataFields.add(dataFieldContent);
    }

    public Post(Template template) {
        this.title=template.getName();
        this.dataFields=template.getDataFields();
    }

    @Column(name = "creation_date")
    private LocalDateTime CreationDate;

    @Column(name="update_date")
    private LocalDateTime UpdateDate;

    @Column(name = "like")
    private int like;

    @Column(name = "dislike")
    private int dislike;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public void addLike(){
        this.like+=1;
    }
    public void addDislike(){
        this.dislike+=1;
    }

}



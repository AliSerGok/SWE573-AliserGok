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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post",cascade = CascadeType.ALL)
    private List<DataField> dataFields=new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "template_id")
    private Template template;

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
    private LocalDateTime creationDate;

    @Column(name="update_date")
    private LocalDateTime updateDate;

    @Column(name = "like_post")
    private int like;

    @Column(name = "dislike_post")
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}



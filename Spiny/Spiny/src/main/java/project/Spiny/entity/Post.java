package project.Spiny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_date")
    private LocalDateTime CreationDate;

    @Column(name="update_date")
    private LocalDateTime UpdateDate;

    @Column(name = "image")
    private String image;

    @Column(name = "like")
    private int like;

    @Column(name = "dislike")
    private int dislike;

    @OneToOne(mappedBy = "post",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "geolocation_id")
    private Geolocation geolocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void addLike(){
        this.like+=1;
    }
    public void addDislike(){
        this.dislike+=1;
    }

}



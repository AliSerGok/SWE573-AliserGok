package com.luv2code.springboot.demosecurity.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private UserProfile userProfile;

    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Community> createdCommunity;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "community_followers",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    private List<Community> followedCommunities;
    public User() {
    }

    public User(String userName, String password, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String userName, String password, boolean enabled,
                Collection<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }


    public void addRoles(Role role) {
        this.roles.add(role);
    }

    public void addCreatedCommunity(Community community) {
        this.createdCommunity.add(community);
    }
    public void addFollowedCommunities(Community community){
        this.followedCommunities.add(community);
    }

}
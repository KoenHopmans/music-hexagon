package com.novi.hexagon.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Demos")
public class Demo implements Serializable {

    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String demo;

    @Column
    private String cover;

    @Column
    private String artist;

    @Column
    private String trackName;

    @OneToMany(
            targetEntity = com.novi.hexagon.model.Comment.class,
            mappedBy = "demo",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<com.novi.hexagon.model.Comment> comments = new HashSet<>();

    @OneToMany(
            targetEntity = com.novi.hexagon.model.Feedback.class,
            mappedBy = "demo",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<com.novi.hexagon.model.Feedback> feedbacks = new HashSet<>();

    public Demo() {
    }

    public Demo(String username, String demo) {
        this.username = username;
        this.demo = demo;
    }

    public Demo(String username, String demo, String cover, String artist, String trackName) {
        this.username = username;
        this.demo = demo;
        this.cover = cover;
        this.artist = artist;
        this.trackName = trackName;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDemo() {
        return demo;
    }
    public void setDemo(String demo) {
        this.demo = demo;
    }
    public String getCover() { return cover;}
    public void setCover(String cover) { this.cover = cover;}
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getTrackName() { return trackName;}
    public void setTrackName(String trackName) { this.trackName = trackName;}
    public Set<Comment> getComments() { return comments; }
    public void addComment(Comment comment) { this.comments.add(comment); }
    public void removeComment(Comment comment) { this.comments.remove(comment); }
    public Set<Feedback> getFeedbacks() { return feedbacks; }
    public void addFeedback(Feedback feedback) { this.feedbacks.add(feedback); }
    public void removeFeedback(Feedback feedback) { this.feedbacks.remove(feedback); }
}



package com.novi.hexagon.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "comments")
public class Comment implements Serializable {

    public Comment() {
    }

    public Comment(String demo, String comment, String date, String messenger) {
        this.demo = demo;
        this.comment = comment;
        this.date = date;
        this.messenger = messenger;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String demo;


    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String messenger;

    @Column
    private boolean read ;

    public Comment(String demo, String comment) { this.demo = demo; this.comment = comment;}
    public String getDemo() {  return demo;}public void setDemo(String demo) {  this.demo = demo;}
    public String getComment() {  return comment;}
    public void setComment(String comment) {  this.comment = comment;}
    public boolean isRead() {return read;}
    public void setRead(boolean read) {this.read = read;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public String getMessenger() {return messenger;}
    public void setMessenger(String messenger) {this.messenger = messenger;}

}

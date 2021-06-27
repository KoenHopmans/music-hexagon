package com.novi.hexagon.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "feedbacks")
public class Feedback implements Serializable {

    public Feedback(String demo, String feedback, String date) {
        this.demo = demo;
        this.feedback = feedback;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String demo;


    @Column(nullable = false)
    private String feedback;


    @Column(nullable = false)
    private String date;

    @Column
    private boolean read ;

    public Feedback() {
    }

    public Feedback(String demo, String feedback) { this.demo = demo; this.feedback = feedback;}
    public String getDemo() {  return demo;}public void setDemo(String demo) {  this.demo = demo;}
    public String getFeedback() {  return feedback;}
    public void setFeedback(String feedback) {  this.feedback = feedback;}
    public boolean isRead() {return read;}
    public void setRead(boolean read) {this.read = read;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}


}

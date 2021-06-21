package com.novi.hexagon.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "feedbacks")
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String demo;


    @Column(nullable = false)
    private String feedback;

    public Feedback() {
    }

    public Feedback(String demo, String feedback) { this.demo = demo; this.feedback = feedback;}
    public String getDemo() {  return demo;}public void setDemo(String demo) {  this.demo = demo;}
    public String getFeedback() {  return feedback;}
    public void setFeedback(String feedback) {  this.feedback = feedback;}


}

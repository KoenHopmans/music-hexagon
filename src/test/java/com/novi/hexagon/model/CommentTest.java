package com.novi.hexagon.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {

    @Autowired
    private  Comment comment;

    @BeforeEach
    void setUp(){
        this.comment = new Comment("testDemo", "testComment", "testDate", "testMessenger");
        this.comment.setRead(false);
    }

    @Test
    void testGetDemo(){
        String expectDemo = "testDemo";
        String actualDemo = this.comment.getDemo();
        assertEquals(expectDemo, actualDemo);
    }

    @Test
    void testSetDemo(){
        String expectDemo = "testDemo2";
        this.comment.setDemo("testDemo2");
        String actualDemo = this.comment.getDemo();
        assertEquals(expectDemo, actualDemo);
    }

    @Test
    void testGetComment(){
        String expectComment = "testComment";
        String actualComment = this.comment.getComment();
        assertEquals(expectComment, actualComment);
    }

    @Test
    void testSetComment(){
        String expectComment = "testComment2";
        this.comment.setComment("testComment2");
        String actualComment = this.comment.getComment();
        assertEquals(expectComment, actualComment);
    }

    @Test
    void testGetDate(){
        String expectDate = "testDate";
        String actualDate = this.comment.getDate();
        assertEquals(expectDate, actualDate);
    }

    @Test
    void testSetDate(){
        String expectDate = "testDate2";
        this.comment.setDate("testDate2");
        String actualDate = this.comment.getDate();
        assertEquals(expectDate, actualDate);
    }

    @Test
    void testGetMessenger(){
        String expectMessenger = "testMessenger";
        String actualMessenger = this.comment.getMessenger();
        assertEquals(expectMessenger, actualMessenger);
    }

    @Test
    void testSetMessenger(){
        String expectMessenger = "testMessenger2";
        this.comment.setMessenger("testMessenger2");
        String actualMessenger = this.comment.getMessenger();
        assertEquals(expectMessenger, actualMessenger);
    }

    @Test
    void testGetRead(){
        boolean expectRead = false;
        boolean actualRead = this.comment.isRead();
        assertEquals(expectRead, actualRead);
    }

    @Test
    void testSetRead(){
        boolean expectRead = true;
        this.comment.setRead(true);
        boolean actualRead = this.comment.isRead();
        assertEquals(expectRead, actualRead);
    }

}
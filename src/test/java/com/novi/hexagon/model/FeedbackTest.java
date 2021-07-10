package com.novi.hexagon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTest {

    @Autowired
    private  Feedback feedback;

    @BeforeEach
    void setUp(){
        this.feedback = new Feedback("testDemo", "testFeedback", "testDate", "testMessenger");
        this.feedback.setRead(false);
    }

    @Test
    void testGetDemo(){
        String expectDemo = "testDemo";
        String actualDemo = this.feedback.getDemo();
        assertEquals(expectDemo, actualDemo);
    }

    @Test
    void testSetDemo(){
        String expectDemo = "testDemo2";
        this.feedback.setDemo("testDemo2");
        String actualDemo = this.feedback.getDemo();
        assertEquals(expectDemo, actualDemo);
    }

    @Test
    void testGetFeedback(){
        String expectFeedback = "testFeedback";
        String actualFeedback = this.feedback.getFeedback();
        assertEquals(expectFeedback, actualFeedback);
    }

    @Test
    void testSetFeedback(){
        String expectFeedback = "testFeedback2";
        this.feedback.setFeedback("testFeedback2");
        String actualFeedback = this.feedback.getFeedback();
        assertEquals(expectFeedback, actualFeedback);
    }

    @Test
    void testGetDate(){
        String expectDate = "testDate";
        String actualDate = this.feedback.getDate();
        assertEquals(expectDate, actualDate);
    }

    @Test
    void testSetDate(){
        String expectDate = "testDate2";
        this.feedback.setDate("testDate2");
        String actualDate = this.feedback.getDate();
        assertEquals(expectDate, actualDate);
    }

    @Test
    void testGetMessenger(){
        String expectMessenger = "testMessenger";
        String actualMessenger = this.feedback.getMessenger();
        assertEquals(expectMessenger, actualMessenger);
    }

    @Test
    void testSetMessenger(){
        String expectMessenger = "testMessenger2";
        this.feedback.setMessenger("testMessenger2");
        String actualMessenger = this.feedback.getMessenger();
        assertEquals(expectMessenger, actualMessenger);
    }

    @Test
    void testGetRead(){
        boolean expectRead = false;
        boolean actualRead = this.feedback.isRead();
        assertEquals(expectRead, actualRead);
    }

    @Test
    void testSetRead(){
        boolean expectRead = true;
        this.feedback.setRead(true);
        boolean actualRead = this.feedback.isRead();
        assertEquals(expectRead, actualRead);
    }
}
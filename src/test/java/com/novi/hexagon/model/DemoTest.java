package com.novi.hexagon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DemoTest {

    @Autowired
    private  Demo demo;

    @BeforeEach
    void setUp(){
        this.demo = new Demo("testUsername","testDemo", "testCover", "testArtist", "testTrackName");
    }


    @Test
    void testGetUsername(){
        String expectUsername = "testUsername";
        String actualUsername = this.demo.getUsername();
        assertEquals(expectUsername, actualUsername);
    }

    @Test
    void testSetUsername(){
        String expectUsername = "testUsername2";
        this.demo.setUsername("testUsername2");
        String actualUsername = this.demo.getUsername();
        assertEquals(expectUsername, actualUsername);
    }


    @Test
    void testGetDemo(){
        String expectDemo = "testDemo";
        String actualDemo = this.demo.getDemo();
        assertEquals(expectDemo, actualDemo);
    }

    @Test
    void testSetDemo(){
        String expectDemo = "testDemo2";
        this.demo.setDemo("testDemo2");
        String actualDemo = this.demo.getDemo();
        assertEquals(expectDemo, actualDemo);
    }

    @Test
    void testGetCover(){
        String expectCover = "testCover";
        String actualCover = this.demo.getCover();
        assertEquals(expectCover, actualCover);
    }

    @Test
    void testSetCover(){
        String expectCover = "testCover2";
        this.demo.setCover("testCover2");
        String actualCover = this.demo.getCover();
        assertEquals(expectCover, actualCover);
    }

    @Test
    void testGetArtist(){
        String expectArtist = "testArtist";
        String actualArtist = this.demo.getArtist();
        assertEquals(expectArtist, actualArtist);
    }

    @Test
    void testSetArtist(){
        String expectArtist = "testArtist2";
        this.demo.setArtist("testArtist2");
        String actualArtist = this.demo.getArtist();
        assertEquals(expectArtist, actualArtist);
    }

    @Test
    void testGetTrackName(){
        String expectTrackName = "testTrackName";
        String actualTrackName = this.demo.getTrackName();
        assertEquals(expectTrackName, actualTrackName);
    }

    @Test
    void testSetTrackName(){
        String expectTrackName = "testTrackName2";
        this.demo.setTrackName("testTrackName2");
        String actualTrackName = this.demo.getTrackName();
        assertEquals(expectTrackName, actualTrackName);
    }

    @Test
    void testGetComments() {
    }


    @Test
    void testAddComment() {
    }

    @Test
    void testRemoveComment() {
    }

    @Test
    void testGetFeedbacks() {
    }


    @Test
    void testAddFeedback() {
    }

    @Test
    void testRemoveFeedback() {
    }
}
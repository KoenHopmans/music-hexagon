package com.novi.hexagon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorityTest {

    @Autowired
    private Authority authority;

    @BeforeEach
    void setUp(){
        this.authority = new Authority("testUsername", "ADMIN");
    }

    @Test
    void testGetUserName(){
        String expectUsername = "testUsername";
        String actualUsername = this.authority.getUsername();
        assertEquals(expectUsername, actualUsername);
    }

    @Test
    void testSetUserName(){
        String expectUsername = "testUsername2";
        this.authority.setUsername("testUsername2");
        String actualUsername = this.authority.getUsername();
        assertEquals(expectUsername, actualUsername);
    }

    @Test
    void testGetAuthority(){
        String expectAuthority = "ADMIN";
        String actualAuthority = this.authority.getAuthority();
        assertEquals(expectAuthority, actualAuthority);
    }

    @Test
    void testSetAuthority(){
        String expectAuthority = "USER";
        this.authority.setAuthority("USER");
        String actualAuthority = this.authority.getAuthority();
        assertEquals(expectAuthority, actualAuthority);
    }


}

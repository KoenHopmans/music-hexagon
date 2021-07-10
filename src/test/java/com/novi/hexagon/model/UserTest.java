package com.novi.hexagon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Autowired
    private  User user;

    @BeforeEach
    void setUp(){
        this.user = new User("testUsername","testPassword","testEmail","testPhoto","testBirthDate","testFirstName",
                "testLastName","testAbout","testLocation","testGender");
    }

    @Test
    void testGetUsername(){
        String expectUsername = "testUsername";
        String actualUsername = this.user.getUsername();
        assertEquals(expectUsername, actualUsername);
    }

    @Test
    void testSetUsername(){
        String expectUsername = "testUsername2";
        this.user.setUsername("testUsername2");
        String actualUsername = this.user.getUsername();
        assertEquals(expectUsername, actualUsername);
    }

    @Test
    void testGetPassword(){
        String expectPassword = "testPassword";
        String actualPassword = this.user.getPassword();
        assertEquals(expectPassword, actualPassword);
    }

    @Test
    void testSetPassword(){
        String expectPassword = "testPassword2";
        this.user.setPassword("testPassword2");
        String actualPassword = this.user.getPassword();
        assertEquals(expectPassword, actualPassword);
    }


    @Test
    void testGetEmail(){
        String expectEmail = "testEmail";
        String actualEmail = this.user.getEmail();
        assertEquals(expectEmail, actualEmail);
    }

    @Test
    void testSetEmail(){
        String expectEmail = "testEmail2";
        this.user.setEmail("testEmail2");
        String actualEmail = this.user.getEmail();
        assertEquals(expectEmail, actualEmail);
    }

    @Test
    void testGetPhoto(){
        String expectPhoto = "testPhoto";
        String actualPhoto = this.user.getPhoto();
        assertEquals(expectPhoto, actualPhoto);
    }

    @Test
    void testSetPhoto(){
        String expectPhoto = "testPhoto2";
        this.user.setPhoto("testPhoto2");
        String actualPhoto = this.user.getPhoto();
        assertEquals(expectPhoto, actualPhoto);
    }

    @Test
    void testGetBirthDate(){
        String expectBirthDate = "testBirthDate";
        String actualBirthDate = this.user.getBirthDate();
        assertEquals(expectBirthDate, actualBirthDate);
    }

    @Test
    void testSetBirthDate(){
        String expectBirthDate = "testBirthDate2";
        this.user.setBirthDate("testBirthDate2");
        String actualBirthDate = this.user.getBirthDate();
        assertEquals(expectBirthDate, actualBirthDate);
    }

    @Test
    void testGetFirstName(){
        String expectFirstName = "testFirstName";
        String actualFirstName = this.user.getFirstName();
        assertEquals(expectFirstName, actualFirstName);
    }

    @Test
    void testSetFirstName(){
        String expectFirstName = "testFirstName2";
        this.user.setFirstName("testFirstName2");
        String actualFirstName = this.user.getFirstName();
        assertEquals(expectFirstName, actualFirstName);
    }

    @Test
    void testGetLastName(){
        String expectLastName = "testLastName";
        String actualLastName = this.user.getLastName();
        assertEquals(expectLastName, actualLastName);
    }

    @Test
    void testSetLastName(){
        String expectLastName = "testLastName2";
        this.user.setLastName("testLastName2");
        String actualLastName = this.user.getLastName();
        assertEquals(expectLastName, actualLastName);
    }

    @Test
    void testGetAbout(){
        String expectAbout = "testAbout";
        String actualAbout = this.user.getAbout();
        assertEquals(expectAbout, actualAbout);
    }

    @Test
    void testSetAbout(){
        String expectAbout = "testAbout2";
        this.user.setAbout("testAbout2");
        String actualAbout = this.user.getAbout();
        assertEquals(expectAbout, actualAbout);
    }

    @Test
    void testGetLocation(){
        String expectLocation = "testLocation";
        String actualLocation = this.user.getLocation();
        assertEquals(expectLocation, actualLocation);
    }

    @Test
    void testSetLocation(){
        String expectLocation = "testLocation2";
        this.user.setLocation("testLocation2");
        String actualLocation = this.user.getLocation();
        assertEquals(expectLocation, actualLocation);
    }

    @Test
    void testGetGender(){
        String expectGender = "testGender";
        String actualGender = this.user.getGender();
        assertEquals(expectGender, actualGender);
    }

    @Test
    void testSetGender(){
        String expectGender = "testGender2";
        this.user.setGender("testGender2");
        String actualGender = this.user.getGender();
        assertEquals(expectGender, actualGender);
    }
}

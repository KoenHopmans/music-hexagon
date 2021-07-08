//package com.novi.hexagon.controller;
//
//import com.novi.hexagon.model.User;
//import com.novi.hexagon.service.CustomUserDetailsService;
//import com.novi.hexagon.service.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private UserService service;
//
//
//    @Test
//    public void givenUser_whenGetUser_thenReturnJsonArray() throws Exception {
//
//        User user = new User("testUsername","testPassword","testEmail","testPhoto","testBirthDate","testFirstName",
//                "testLastName","testAbout","testLocation","testGender");
//        List<User> allUsers = Arrays.asList(user);
//        given(service.getUsers()).willReturn(allUsers);
//
//        mvc.perform(get("")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].username", is(user.getUsername())));
//    }
//
//}
//package com.novi.hexagon.service;
//
//import com.novi.hexagon.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//
//
//class CustomUserDetailsServiceTest {
//
//    @InjectMocks
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Mock
//    UserRepository userRepository;
//
//    @BeforeEach
//    public void setup() {
//        customUserDetailsService = new CustomUserDetailsService(userRepository);
//    }
//
//    @Test
//    void loadUserByUsername() {
//        userService.getUser("testUsername");
//        verify(userRepository).findById("testUsername");
//
//    }
//}
package com.novi.hexagon.service;

import com.novi.hexagon.model.User;
import com.novi.hexagon.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    @Test
    void loadUserByUsername() {
        User testUser = new User("testUsername","testPassword","testEmail","testPhoto","testBirthDate","testFirstName",
                "testLastName","testAbout","testLocation","testGender");
        when(userService.getUser(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        customUserDetailsService.loadUserByUsername(testUser.getUsername());
        assertEquals(testUser.getUsername(),customUserDetailsService.loadUserByUsername(testUser.getUsername()).getUsername());
        assertEquals(testUser.getPassword(),customUserDetailsService.loadUserByUsername(testUser.getUsername()).getPassword());
    }
}

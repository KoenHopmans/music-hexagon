package com.novi.hexagon.service;

import com.novi.hexagon.exceptions.RecordNotFoundException;
import com.novi.hexagon.exceptions.UsernameNotFoundException;
import com.novi.hexagon.model.Authority;
import com.novi.hexagon.model.User;
import com.novi.hexagon.repository.DemoRepository;
import com.novi.hexagon.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
    }

    @Test
    void testGetUsers() {
    userService.getUsers();
    verify(userRepository).findAll();
    }

    @Test
    void testGetUser() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        userService.getUser("testUsername");
        verify(userRepository).findById("testUsername");
    }

    @Test
    void testGetUser_NotFoundException() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,()->{userService.getUser(testUser.getUsername());});
    }


    @Test
    void testCreateUser() {
        User testUser = new User("testUsername");
        testUser.setPassword("testPassword");
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn(testUser.getPassword());
        when(userRepository.save(testUser)).thenReturn(testUser);
        userService.createUser(testUser);
        verify(userRepository).save(testUser);
    }

    @Test
    void testDeleteUser() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        userService.deleteUser(testUser.getUsername());
        verify(userRepository).deleteByUsername("testUsername");
    }


    @Test
    void testDeleteUser_NotFoundException() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,()->{userService.deleteUser(testUser.getUsername());});
    }

    @Test
    void testUpdateUser() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        userService.updateUser(testUser.getUsername(),testUser);
        verify(userRepository).save(testUser);
    }

    @Test
    void testUpdateUser_NotFoundException() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,()->{userService.updateUser(testUser.getUsername(),testUser);});
    }

    @Captor
    ArgumentCaptor<User> userCaptor;
    @Test
    void testUpdatePassword() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn("encodedPassword");
        userService.updatePassword(testUser.getUsername(),testUser);
        verify(userRepository).save(userCaptor.capture());
        assertEquals(userCaptor.getValue().getPassword(), "encodedPassword");
    }

    @Test
    void testUpdatePassword_NotFoundException() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(RecordNotFoundException.class,()->{userService.updatePassword(testUser.getUsername(),testUser);});
    }


    @Test
    void testGetAuthorities() {
        User testUser = new User("testUsername");
        testUser.addAuthority(new Authority(testUser.getUsername(),"ADMIN"));
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        assertEquals(testUser.getAuthorities(),userService.getAuthorities(testUser.getUsername()));
    }

    @Test
    void testGetAuthorities_NotFoundException() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(UsernameNotFoundException.class,()->{userService.getAuthorities(testUser.getUsername());});
    }

    @Test
    void testAddAuthority() {
        User testUser = new User("testUsername");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        userService.addAuthority(testUser.getUsername(),"ADMIN");
        verify(userRepository).save(testUser);
    }

    @Test
    void testAddAuthority_NotFoundException() {
        User testUser = new User("testUsername");
        String testAuthority = "Admin";
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(UsernameNotFoundException.class,()->{userService.addAuthority(testUser.getUsername(),testAuthority);});
    }

    @Test
    void testRemoveAuthority() {
        User testUser = new User("testUsername");
        testUser.addAuthority(new Authority(testUser.getUsername(),"ADMIN"));
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        userService.removeAuthority(testUser.getUsername(),"ADMIN");
        verify(userRepository).save(testUser);
    }

    @Test
    void testRemoveAuthority_NotFoundException() {
        User testUser = new User("testUsername");
        String testAuthority = "Admin";
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(UsernameNotFoundException.class,()->{userService.addAuthority(testUser.getUsername(),testAuthority);});
    }
}
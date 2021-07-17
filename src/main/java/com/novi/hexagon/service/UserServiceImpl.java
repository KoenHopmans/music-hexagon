package com.novi.hexagon.service;

import com.novi.hexagon.exceptions.RecordNotFoundException;
import com.novi.hexagon.exceptions.UsernameNotFoundException;
import com.novi.hexagon.model.Authority;
import com.novi.hexagon.model.User;
import com.novi.hexagon.repository.UserRepository;
import com.novi.hexagon.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements com.novi.hexagon.service.UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserServiceImpl() {
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String username) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        Optional<User> user = userRepository.findById(username);
        return user;
}

    @Override
    public String createUser(User user) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        user.setApikey(randomString);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return newUser.getUsername();
    }

    @Override
    public void deleteUser(String username) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        userRepository.deleteByUsername(username);
    }

    @Override
    public void updateUser(String username, User newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setEmail(newUser.getEmail());
        user.setBirthDate(newUser.getBirthDate());
        user.setLocation(newUser.getLocation());
        user.setAbout(newUser.getAbout());
        user.setGender(newUser.getGender());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        String photo = newUser.getPhoto();
        if(!(photo==null)){
        user.setPhoto(newUser.getPhoto());}
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String username, User newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }

    @Override
    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    @Override
    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }
}
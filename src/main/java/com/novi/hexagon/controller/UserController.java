package com.novi.hexagon.controller;

import com.novi.hexagon.exceptions.BadRequestException;
import com.novi.hexagon.model.User;
import com.novi.hexagon.repository.UserRepository;
import com.novi.hexagon.service.FileStorageService;
import com.novi.hexagon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    FileStorageService fileStorageService;

//    @PutMapping(value = "/profile/{username}")
    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "username" ,required = false) String username,
                                             @RequestParam(value = "birthDate",required = false) String birthDate,
                                             @RequestParam(value = "email" ,required = false) String email,
                                             @RequestParam(value = "firstName" ,required = false) String firstName,
                                             @RequestParam(value = "lastName" ,required = false) String lastName,
                                             @RequestParam(value = "about" ,required = false) String about,
                                             @RequestParam(value = "gender" ,required = false) String gender,
                                             @RequestParam(value = "location" ,required = false) String location,
                                             @RequestParam(value = "file", required = false) Optional<MultipartFile> file
    ) {
        User newUser = new User();


        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setAbout(about);
        newUser.setGender(gender);
        newUser.setLocation(location);
        newUser.setBirthDate(birthDate);
        if(!(file.isEmpty())){newUser.setPhoto(file.get().getOriginalFilename());}


        try {
            if(!(file.isEmpty())){fileStorageService.uploadFile(file.get());}
            if(!(file.isEmpty())){fileStorageService.updateFile(file.get(),username);}
            userService.updateUser(username, newUser);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }




    @GetMapping(value = "")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }


//    @PostMapping(value = "")
//    public ResponseEntity<Object> createUser(@RequestBody User user) {
//        if (userRepository.existsById(user.getUsername())){
////            To Do make User exist exception
//            throw new BadRequestException();}
//        String newUsername = userService.createUser(user);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
//                .buildAndExpand(newUsername).toUri();
//
//        return ResponseEntity.created(location).build();
//    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        if (userRepository.existsById(user.getUsername())){
//            To Do make User exist exception
            throw new BadRequestException();}
        String newUsername = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }



//    @PutMapping(value = "/{username}")
//    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user) {
//        userService.updateUser(username, user);
//        return ResponseEntity.noContent().build();
//    }

//    @GetMapping(value = "/{username}/password")
//    @ResponseStatus(HttpStatus.OK)
//    public String hello() {
//        return "Hello password";
//    }

    @PutMapping(value = "/{username}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable("username") String username, @RequestBody User user) {
        userService.updatePassword(username, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

}

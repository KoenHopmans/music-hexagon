package com.novi.hexagon.controller;

import com.novi.hexagon.exceptions.BadRequestException;
import com.novi.hexagon.model.User;
import com.novi.hexagon.service.FileStorageService;
import com.novi.hexagon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api/v1/users")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    FileStorageService fileStorageService;

    @PutMapping(value = "/profile/{username}")
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
}

package com.novi.hexagon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins={"*"})
@RequestMapping
public class BaseController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "Hello World";
    }

}

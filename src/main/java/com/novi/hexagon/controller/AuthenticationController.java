package com.novi.hexagon.controller;

import com.novi.hexagon.model.Authority;
import com.novi.hexagon.model.Dto;
import com.novi.hexagon.payload.AuthenticationRequest;
import com.novi.hexagon.payload.AuthenticationResponse;
import com.novi.hexagon.payload.AuthenticationResponseDto;
import com.novi.hexagon.service.CustomUserDetailsService;
import com.novi.hexagon.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtl;



    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);

    }

    @GetMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "Hello World";}





    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        System.out.println(userDetails.getAuthorities());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//        System.out.println(authorities);
        final String jwt = jwtUtl.generateToken(userDetails);
        Dto dto = new Dto();

        dto.setJwt(jwtUtl.generateToken(userDetails));
        boolean admin = false;
        for(GrantedAuthority authority : authorities){
            System.out.println("AUTHORITYS");
            System.out.println(authority.getAuthority());
            if(authority.getAuthority().equals("ROLE_ADMIN"))
                admin = true;
            break;
        }
        if (admin
//            authorities.stream().anyMatch(ga -> ga.equals("ROLE_ADMIN"))
//            authorities.equals("ADMIN");
//            authorities.stream().anyMatch(ga -> ga.equals("ADMIN"))
//            authorities.contains("ADMIN")
        ){
        dto.setRole("ADMIN");
            System.out.println("ADMIN");
        } else{
            dto.setRole("USER");
            System.out.println("USER");
        ;}


//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
        return ResponseEntity.ok(new AuthenticationResponseDto(dto));

    }

}
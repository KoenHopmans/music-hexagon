package com.novi.hexagon.controller;

import com.novi.hexagon.payload.AuthorityDto;
import com.novi.hexagon.payload.AuthenticationRequest;
import com.novi.hexagon.payload.AuthenticationResponseDto;
import com.novi.hexagon.service.CustomUserDetailsService;
import com.novi.hexagon.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

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
        final String jwt = jwtUtl.generateToken(userDetails);
        AuthorityDto dto = new AuthorityDto();
        dto.setJwt(jwtUtl.generateToken(userDetails));
        boolean admin = false;
        for(GrantedAuthority authority : authorities){
            System.out.println("AUTHORITYS");
            System.out.println(authority.getAuthority());
            if(authority.getAuthority().equals("ROLE_ADMIN"))
                admin = true;
            break;
        }
        if (admin){ dto.setRole("ADMIN");
        } else{ dto.setRole("USER"); }
        return ResponseEntity.ok(new AuthenticationResponseDto(dto));
    }
}
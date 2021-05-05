package com.library.controllers;

import com.library.dto.AuthRequestDTO;
import com.library.dto.AuthResponseDTO;
import com.library.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequestDTO authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex){
            throw new Exception("invalid username/password");
        }

        final String token = jwtUtil.generateToken(authRequest.getUsername());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}

package com.project.controller;

import com.project.model.AuthRequest;
import com.project.model.SignInRequest;
import com.project.model.dto.AuthenticationResponse;
import com.project.response.SuccessResponse;
import com.project.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<AuthenticationResponse>> login(@RequestBody AuthRequest request) {
         return new ResponseEntity<>(new SuccessResponse<>(authService.authenticate(request)), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<AuthenticationResponse>> signup(@RequestBody SignInRequest request) {
        return new ResponseEntity<>(new SuccessResponse<>(authService.signup(request)), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<SuccessResponse<AuthenticationResponse>> refreshToken(HttpServletRequest request)  {
        return new ResponseEntity<>(new SuccessResponse<>(authService.refreshToken(request)), HttpStatus.OK);
    }

}




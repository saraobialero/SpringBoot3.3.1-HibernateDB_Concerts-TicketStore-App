package com.project.service;

import com.project.exception.AuthException;
import com.project.model.AuthRequest;
import com.project.model.SignInRequest;
import com.project.model.User;
import com.project.model.dto.AuthenticationResponse;
import com.project.model.enums.ErrorCode;
import com.project.repository.UserRepository;
import com.project.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //Login and return the authentication response
    public AuthenticationResponse authenticate(AuthRequest request) {
        //Verify user credentials and return authentication token
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException
                                   (new ErrorResponse(ErrorCode.EUN, "User not found")));
        return createAuthenticationResponse(user);
    }
    //Refresh the access token
    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        User user = customUserDetailsService.getUserFromToken(request);
        return createAuthenticationResponse(user);
    }

    // Register a new user and return the authentication response
    public AuthenticationResponse signup(SignInRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AuthException(new ErrorResponse(ErrorCode.EAE, "Email already exists"));
        }

        // Check if passwords match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AuthException(new ErrorResponse(ErrorCode.PWM, "Passwords do not match"));
        }

        //Encode the password before saving
        String encodePsw = encodePsw(request.getPassword());
        //Save the new user
        User user = User.builder()
                .email(request.getEmail())
                .password(encodePsw)
                .name(request.getName())
                .surname(request.getSurname())
                .build();
        userRepository.save(user);
        return createAuthenticationResponse(user);
    }

    //Encode the password
    private String encodePsw(String password) {
        return passwordEncoder.encode(password);
    }

    //Build the authentication response with access and refresh tokens
    private AuthenticationResponse createAuthenticationResponse(User user) {
        return AuthenticationResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }
}

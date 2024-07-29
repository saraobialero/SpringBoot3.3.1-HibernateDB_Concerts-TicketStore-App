package com.project.service;

import com.project.exception.UserException;
import com.project.model.User;
import com.project.model.dto.UserDTO;
import com.project.model.enums.ErrorCode;
import com.project.repository.UserRepository;
import com.project.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException(
                new ErrorResponse(ErrorCode.EUN, "User not found with email: " + email)
        ));
    }

    public UserDTO loadUser(HttpServletRequest request) {
        User user = getUserFromToken(request);
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }

    public User getUserFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UserException(new ErrorResponse(ErrorCode.BR, "missing or invalid Bearer token"));
        }

        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);
        //Get user from token
        UserDetails userDetails = this.loadUserByUsername(email);

        //verify token and user
        if(!jwtService.isTokenValid(token, userDetails)) {
            throw new UserException(new ErrorResponse(ErrorCode.BR, "Token is not valid or expired"));
        }
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(new ErrorResponse(ErrorCode.EUN, "User not found")));
    }
}

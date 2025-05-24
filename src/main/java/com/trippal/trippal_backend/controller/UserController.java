package com.trippal.trippal_backend.controller;

import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.dtos.UserInfoDto;
import com.trippal.trippal_backend.model.AuthRequest;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.service.JwtService;
import com.trippal.trippal_backend.service.UserInfoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserInfoService userInfoService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserInfoDto> createUser(@RequestBody UserInfo userInfo) {
        UserInfoDto savedUser = userInfoService.createUser(userInfo);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAndSetCookie(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                // Generate a JWT token if authenticated
                String jwt = jwtService.generateToken(authRequest.getUsername());

                // Create a secure HTTP-only cookie to store the JWT token
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(3600);

                response.addCookie(cookie);

                UserInfo user = userInfoService.getUserByEmail(authRequest.getUsername());

                List<TripDto> tripDtos = userInfoService.getUserTripDtos(user);

                UserInfoDto dto = new UserInfoDto(user, tripDtos);

                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out successfully");
    }
}
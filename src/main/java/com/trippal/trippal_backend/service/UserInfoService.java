package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.dtos.UserInfoDto;
import com.trippal.trippal_backend.exception.DuplicateUserException;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.UserInfoRepository;
import com.trippal.trippal_backend.security.UserInfoDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository, PasswordEncoder encoder) {
        this.userInfoRepository = userInfoRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.findByEmail(username)
                .map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public UserInfo findById(Long userId) {
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    public UserInfoDto createUser(UserInfo userInfo) {
        try {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));

            UserInfo savedUser = userInfoRepository.save(userInfo);

            return new UserInfoDto(savedUser);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUserException("Email or username already exists");
        }
    }

    public UserInfo getUserByEmail(String email) {
        return userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Transactional
    public List<TripDto> getUserTripDtos(UserInfo user) {
        return user.getTrips().stream()
                .map(TripDto::new)
                .collect(Collectors.toList());
    }
}

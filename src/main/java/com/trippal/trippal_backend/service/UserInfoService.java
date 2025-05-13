package com.trippal.trippal_backend.service;

import com.trippal.trippal_backend.dtos.TripDto;
import com.trippal.trippal_backend.dtos.UserInfoDto;
import com.trippal.trippal_backend.exception.DuplicateUserException;
import com.trippal.trippal_backend.model.Trip;
import com.trippal.trippal_backend.model.UserInfo;
import com.trippal.trippal_backend.repository.UserInfoRepository;

import jakarta.transaction.Transactional;
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

    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;

    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public UserInfoDto createUser(UserInfo userInfo) {
        try {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));

            UserInfo savedUser = repository.save(userInfo);
            return new UserInfoDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), getUserTripDtos(savedUser));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUserException("Email or username already exists");
        }
    }

    public UserInfo getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Transactional
    public List<TripDto> getUserTripDtos(UserInfo user) {
        return user.getTrips().stream()
                .map(trip -> new TripDto(trip.getId(), trip.getTitle(), trip.isPublic(), user.getId()))
                .collect(Collectors.toList());
    }
}

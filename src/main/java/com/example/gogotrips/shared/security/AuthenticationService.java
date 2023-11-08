package com.example.gogotrips.shared.security;

import com.example.gogotrips.shared.dto.JwtAuthenticationDtoResponse;
import com.example.gogotrips.shared.dto.SignUpDtoRequest;
import com.example.gogotrips.shared.dto.SigninDtoRequest;
import com.example.gogotrips.shared.dto.UserDTO;
import com.example.gogotrips.shared.mappers.UserMapper;
import com.example.gogotrips.shared.model.Role;
import com.example.gogotrips.shared.model.User;
import com.example.gogotrips.shared.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    public UserDTO signup(SignUpDtoRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        return  userMapper.mapToDTO(user);
    }


    public JwtAuthenticationDtoResponse signin(SigninDtoRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Correo o usuario incorrectos."));


        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationDtoResponse.builder().token(jwt).userDto(userMapper.mapToDTO(user)).build();
    }
}
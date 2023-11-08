package com.example.gogotrips.shared.api;

import com.example.gogotrips.shared.dto.JwtAuthenticationDtoResponse;
import com.example.gogotrips.shared.dto.SignUpDtoRequest;
import com.example.gogotrips.shared.dto.SigninDtoRequest;
import com.example.gogotrips.shared.dto.UserDTO;
import com.example.gogotrips.shared.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignUpDtoRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationDtoResponse> signin(@RequestBody SigninDtoRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
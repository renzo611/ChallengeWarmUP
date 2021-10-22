package com.alkemy.ChallengeWarmUP.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ChallengeWarmUP.auth.dto.AuthenticationRequestDto;
import com.alkemy.ChallengeWarmUP.auth.dto.AuthenticationResponseDto;
import com.alkemy.ChallengeWarmUP.auth.dto.UserDto;
import com.alkemy.ChallengeWarmUP.auth.service.JwtUtils;
import com.alkemy.ChallengeWarmUP.auth.service.UserDetailsCustomService;


@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
	private UserDetailsCustomService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtTokenUtil;


    @PostMapping("/sing_up")
    public ResponseEntity<?> singUp(@Valid @RequestBody UserDto user) throws Exception {
        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> singIn(@RequestBody AuthenticationRequestDto authRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Nombre de usuario o contraseña incorrecta", e);
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}

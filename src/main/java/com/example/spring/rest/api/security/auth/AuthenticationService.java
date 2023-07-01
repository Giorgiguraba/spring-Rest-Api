package com.example.spring.rest.api.security.auth;

import com.example.spring.rest.api.entities.User;
import com.example.spring.rest.api.repositories.UserRepository;
import com.example.spring.rest.api.security.confg.JwtService;
import com.example.spring.rest.api.security.token.Token;
import com.example.spring.rest.api.security.token.TokenRepository;
import com.example.spring.rest.api.utils.GeneratUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;


    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager,TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }
    public AuthenticationResponse register(RegistrationRequest request) throws Exception{
        User user = new User();
        GeneratUtil.getCopyOf(request, user, "password");
        user.setPassword(passwordEncoder.encode(request.getPasswrod()));

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        tokenRepository.save(new Token(jwtToken, user));
        return new AuthenticationResponse(jwtToken);
    }
    public AuthenticationResponse authentication(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        tokenRepository.save(new Token(jwtToken, user));
        return new AuthenticationResponse(jwtToken);
    }


}

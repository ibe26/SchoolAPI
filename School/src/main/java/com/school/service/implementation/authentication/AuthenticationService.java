package com.school.service.implementation.authentication;

import com.school.dto.auth.AuthenticateRequest;
import com.school.dto.auth.AuthenticationResponse;
import com.school.dto.auth.RegisterRequest;
import com.school.model.user.Role;
import com.school.model.user.User;
import com.school.repository.user.UserRepository;
import com.school.service.implementation.jwt.JwtService;
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

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request){
        var user= new User()
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt=jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;

    }
    public AuthenticationResponse authenticate(AuthenticateRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwt=jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }
}

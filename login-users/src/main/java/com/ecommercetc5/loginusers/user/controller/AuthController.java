package com.ecommercetc5.loginusers.user.controller;

import com.ecommercetc5.loginusers.security.TokenService;
import com.ecommercetc5.loginusers.user.entity.User;
import com.ecommercetc5.loginusers.user.repository.IUserRepository;
import com.ecommercetc5.loginusers.user.request.UserAuthRequest;
import com.ecommercetc5.loginusers.user.request.UserRequest;
import com.ecommercetc5.loginusers.user.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, IUserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthRequest data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserResponse(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequest data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();

    }
}

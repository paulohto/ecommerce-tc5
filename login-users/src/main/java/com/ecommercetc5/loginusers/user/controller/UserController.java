package com.ecommercetc5.loginusers.user.controller;

import com.ecommercetc5.loginusers.user.dto.UserComplementDto;
import com.ecommercetc5.loginusers.user.dto.UserDto;
import com.ecommercetc5.loginusers.user.entity.User;
import com.ecommercetc5.loginusers.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/save")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        var userSaved = userService.saveUser(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(userSaved.id()).toUri();
        return ResponseEntity.created(uri).body(userSaved);
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<UserComplementDto> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserComplementDto>> findAll(
            @RequestParam (value = "page", defaultValue = "0") int page,
            @RequestParam (value = "size", defaultValue = "10") int size
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        var users = userService.findAll(pageRequest);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto userSaved = userService.updateUser(id,userDto);
        return ResponseEntity.ok(userSaved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    /* DADOS ATRAVÉS DO OAUTH */

    @GetMapping("/principal") //cookie  // curl http://localhost:8080/users/principal --cookie "JSESSIONID= "
    String principal(@AuthenticationPrincipal OidcUser principal) {
        return String.format("""
				<h2> Oauth2 🔐 </h2>
				<h3>Principal: %s</h3>
				<h3>Email attribute: %s</h3>
				<h3>Name attribute: %s</h3>
				<h3>Authorities: %s</h3>
				<h3>JWT: %s</h3>
				""", principal, principal.getAttribute("email"),principal.getAttribute("name"), principal.getAuthorities(),
                principal.getIdToken().getTokenValue());
    }

    @GetMapping("/jwt") //jwt //curl http://localhost:8080/users/jwt -H "Authorization: Bearer ${TOKEN}" -v
    String jwt(@AuthenticationPrincipal Jwt jwt) {
        return String.format("""
				Principal: %s\n
				Email attribute: %s\n
				Name attribute: %s\n
				JWT: %s\n
				""", jwt.getClaims(), jwt.getClaim("email"),jwt.getClaim("name"), jwt.getTokenValue());
    }

}

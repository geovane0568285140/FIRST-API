package com.frota.project.controller;


import com.frota.project.dtos.users.AuthenticationDto;
import com.frota.project.dtos.users.InputUserRecordDto;
import com.frota.project.dtos.users.LoginResponseRecordDto;
import com.frota.project.dtos.users.RegisterRecordDto;
import com.frota.project.infra.security.TokenService;
import com.frota.project.model.UserModel;
import com.frota.project.repository.UserRepository;
import com.frota.project.service.AuthorizationService;
import com.frota.project.service.UserService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frotas/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDto data) {
        var user = userService.getUser(data.email());
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.getId_user(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.genereteToken((UserModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseRecordDto(token));
    }

}

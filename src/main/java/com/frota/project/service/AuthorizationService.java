package com.frota.project.service;

import com.frota.project.dtos.users.AuthenticationDto;
import com.frota.project.dtos.users.RegisterRecordDto;
import com.frota.project.infra.security.TokenService;
import com.frota.project.model.UserModel;
import com.frota.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String idToString) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(idToString)).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    /* public String authentication(AuthenticationDto dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.name(), dto.password());
        var auth = this.authenticationMenager.authenticate(usernamePassword);
        return tokenService.genereteToken((UserModel) auth.getPrincipal());
    } */


}

package br.gov.sp.fatec.login.controller;

import br.gov.sp.fatec.security.JwtUtils;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("dev/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthenticationManager auth;

    @PostMapping
    public UserDTO login(@RequestBody UserDTO login) throws JsonProcessingException {
        Authentication credentials = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        User user = (User) auth.authenticate(credentials).getPrincipal();
        login.setPassword(null);
        login.setToken(JwtUtils.generateToken(user));
        login.setName(user.getName());
        login.setId(user.getId());
        login.setAuthorizations(user.getAuthorizations());

        return login;
    }
}

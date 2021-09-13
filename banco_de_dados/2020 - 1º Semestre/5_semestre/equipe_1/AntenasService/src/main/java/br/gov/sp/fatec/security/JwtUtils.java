package br.gov.sp.fatec.security;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtUtils {

    private static final String KEY = "spring.jwt.sec";

    public static String generateToken(User user)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userWithoutPassword = new UserDTO();
        userWithoutPassword.setEmail(user.getUsername());
        userWithoutPassword.setName(user.getName());
        userWithoutPassword.setToken(user.getToken());
        userWithoutPassword.setId(user.getId());

        if(!user.getAuthorities().isEmpty()) {
            if (userWithoutPassword.getAuthorizations() == null ) {
                userWithoutPassword.setAuthorizations(new ArrayList<>());
            }
            Authorization authorization = new Authorization();
            authorization.setName(user.getAuthorities().iterator().next().getAuthority());
            authorization.setAuthority(user.getAuthorities().iterator().next().getAuthority());

            userWithoutPassword.getAuthorizations().add(authorization);
        }
        String userJson = mapper.writeValueAsString(userWithoutPassword);
        Date now = new Date();
        Long hora = 1000L * 60L * 60L; // Uma hora
        return Jwts.builder().claim("userDetails", userJson)
                .setIssuer("br.gov.sp.fatec")
                .setSubject(user.getId().toString())
                .setExpiration(new Date(now.getTime() + hora))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public static org.springframework.security.core.userdetails.User parseToken(String token) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("userDetails", String.class);

        UserDTO user = mapper.readValue(credentialsJson, UserDTO.class);

        return (org.springframework.security.core.userdetails.User) org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password("SECRET")
                .authorities(user.getAuthorizations())
                .build();
    }
}

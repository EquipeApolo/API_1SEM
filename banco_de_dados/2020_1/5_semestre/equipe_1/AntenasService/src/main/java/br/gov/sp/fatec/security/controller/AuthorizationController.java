package br.gov.sp.fatec.security.controller;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.service.AuthorizationService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("dev/authorization")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    public ResponseEntity<Collection<Authorization>> pesquisar(@PathVariable("nome") String nome) {
        return new ResponseEntity<Collection<Authorization>>(authorizationService.search(nome), HttpStatus.OK);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Authorization> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(authorizationService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<Collection<Authorization>> getAll() {
        return new ResponseEntity<>(authorizationService.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Authorization> Save(@RequestBody Authorization authorization, UriComponentsBuilder uriComponentsBuilder) {
        authorization = authorizationService.create(authorization);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + authorization.getId()).build().toUri());
        return new ResponseEntity<>(authorization, responseHeaders, HttpStatus.CREATED);
    }
    
}

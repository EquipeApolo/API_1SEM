package br.gov.sp.fatec.security.service;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("authorizationService")
@Transactional
public class AuthorizationService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    public void setAuthorizationRepository(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    public void delete(Long authorizationId) {
        authorizationRepository.deleteById(authorizationId);
    }

    public Authorization create(Authorization authorization) {
        return authorizationRepository.save(authorization);
    }

    public List<Authorization> list() {
        List<Authorization> authorizations = new ArrayList<>();
        for (Authorization authorization: authorizationRepository.findAll()) {
            authorizations.add(authorization);
        }
        return authorizations;
    }

    public List<Authorization> search(String name) {
        if (name == null || name.isEmpty()) {
            return list();
        }
        return authorizationRepository.findByNameContainsIgnoreCase(name);
    }

    public Authorization findById(Long authorizationId) {
        return authorizationRepository.findById(authorizationId).orElse(null);
    }
}

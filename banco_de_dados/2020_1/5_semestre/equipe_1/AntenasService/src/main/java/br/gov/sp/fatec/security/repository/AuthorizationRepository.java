package br.gov.sp.fatec.security.repository;

import br.gov.sp.fatec.security.domain.Authorization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {

    public Authorization findByName(String name);

    public List<Authorization> findByNameContainsIgnoreCase(String name);

}
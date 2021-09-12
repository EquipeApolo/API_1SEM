package br.gov.sp.fatec.cadi.repository;

import br.gov.sp.fatec.cadi.domain.Cadi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadiRepository extends JpaRepository<Cadi, Long>, JpaSpecificationExecutor<Cadi> {

    List<Cadi> findAllByActive(Boolean active);

    Cadi findByEmailAndPassword(String email, String password);

    Cadi findByEmail(String email);
}

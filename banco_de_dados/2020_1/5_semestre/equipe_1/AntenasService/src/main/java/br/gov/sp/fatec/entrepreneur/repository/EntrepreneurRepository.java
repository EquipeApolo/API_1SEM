package br.gov.sp.fatec.entrepreneur.repository;

import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long>, JpaSpecificationExecutor<Entrepreneur> {

    List<Entrepreneur> findAllByActive(Boolean active);

    Entrepreneur findByEmailAndPassword(String email, String password);

    Entrepreneur findByEmail(String email);
}

package br.gov.sp.fatec.student.repository;

import br.gov.sp.fatec.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    List<Student> findAllByActive(Boolean active);

    Student findByEmailAndPassword(String email, String password);

    Student findByEmail(String email);
}

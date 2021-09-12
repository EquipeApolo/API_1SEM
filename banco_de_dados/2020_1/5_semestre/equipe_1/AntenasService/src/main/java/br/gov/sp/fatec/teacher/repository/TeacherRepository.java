package br.gov.sp.fatec.teacher.repository;

import br.gov.sp.fatec.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    List<Teacher> findAllByActive(Boolean active);

    Teacher findByEmailAndPassword(String email, String password);

    Teacher findByEmail(String email);
}

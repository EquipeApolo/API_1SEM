package br.gov.sp.fatec.project.repository;

import br.gov.sp.fatec.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    List<Project> findByTeacherId(Long id);

    List<Project> findByStudentsId(Long id);

    List<Project> findByEntrepreneurId(Long id);

    List<Project> findByStudentResponsibleId(Long id);

}

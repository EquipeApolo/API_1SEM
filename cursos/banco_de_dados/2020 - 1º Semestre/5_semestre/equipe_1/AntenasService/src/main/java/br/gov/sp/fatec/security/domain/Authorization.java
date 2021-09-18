package br.gov.sp.fatec.security.domain;

import br.gov.sp.fatec.cadi.view.CadiView;
import br.gov.sp.fatec.entrepreneur.view.EntrepreneurView;
import br.gov.sp.fatec.project.view.ProjectView;
import br.gov.sp.fatec.student.view.StudentView;
import br.gov.sp.fatec.teacher.view.TeacherView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorization")
public class Authorization implements GrantedAuthority {

    private static final long serialVersionUID = 3078636239920155012L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    private Long id;

    @Column(unique=true, length = 20, nullable = false)
    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public void setAuthority(String authority) {
        this.name = authority;
    }



}

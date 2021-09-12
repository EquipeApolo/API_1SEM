package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.student.view.StudentView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {

    @JsonView({StudentView.Student.class})
    @ManyToMany(mappedBy = "students")
    private List<Project> projects = new LinkedList<>();

    public List<Project> getProjects() {
        return projects;
    }
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}

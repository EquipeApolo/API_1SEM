package br.gov.sp.fatec.project.domain;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;
import br.gov.sp.fatec.project.view.ProjectView;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.view.StudentView;
import br.gov.sp.fatec.teacher.domain.Teacher;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ProjectView.Project.class, StudentView.Student.class})
    private Long id;

    @JsonView({ProjectView.Project.class})
    private String title;

    @JsonView({ProjectView.Project.class})
    @Column(name = "short_description")
    private String shortDescription;

    @JsonView({ProjectView.Project.class})
    @Column(name = "complete_description")
    private String completeDescription;

    @JsonView({ProjectView.Project.class})
    @Column(name = "tecnology_description")
    private String technologyDescription;

    @JsonView({ProjectView.Project.class})
    @Column(name = "notes")
    private String notes;

    @JsonView({ProjectView.Project.class})
    private int progress;

    @JsonView({ProjectView.Project.class})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    private Meeting meeting;

    @JsonView({ProjectView.Project.class})
    @ManyToOne
    @JoinColumn(name = "cadi_id", referencedColumnName = "id")
    private Cadi cadi;

    @JsonView({ProjectView.Project.class})
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @JsonView({ProjectView.Project.class})
    @ManyToOne
    @JoinColumn(name = "entrepreneur_id", referencedColumnName = "id")
    private Entrepreneur entrepreneur;

    @JsonView({ProjectView.Project.class})
    @ManyToMany
    @JoinTable(name = "project_student",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
   private List<Student> students = new ArrayList<>();

    @JsonView({ProjectView.Project.class})
    @ManyToMany
    @JoinTable(name = "project_deliver",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "deliver_id"))
    private List<Deliver> deliver = new ArrayList<>();

    @JsonView({ProjectView.Project.class})
    @ManyToOne
    @JoinColumn(name = "student_responsible_id", referencedColumnName = "id")
    private Student studentResponsible;

    @CreatedDate
    @Column(nullable = false)
    @JsonView({ProjectView.Project.class})
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @JsonView({ProjectView.Project.class})
    private Boolean refused;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCompleteDescription() {
        return completeDescription;
    }

    public void setCompleteDescription(String completeDescription) {
        this.completeDescription = completeDescription;
    }

    public String getTechnologyDescription() {
        return technologyDescription;
    }

    public void setTechnologyDescription(String technologyDescription) {
        this.technologyDescription = technologyDescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Cadi getCadi() {
        return cadi;
    }

    public void setCadi(Cadi cadi) {
        this.cadi = cadi;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Deliver> getDeliver() {
        return deliver;
    }

    public void setDeliver(List<Deliver> deliver) {
        this.deliver = deliver;
    }

    public Student getStudentResponsible() {
        return studentResponsible;
    }

    public void setStudentResponsible(Student studentResponsible) {
        this.studentResponsible = studentResponsible;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getRefused() {
        return refused;
    }

    public void setRefused(Boolean refused) {
        this.refused = refused;
    }
}
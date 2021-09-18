package br.gov.sp.fatec.user.domain;

import br.gov.sp.fatec.cadi.view.CadiView;
import br.gov.sp.fatec.entrepreneur.view.EntrepreneurView;
import br.gov.sp.fatec.project.view.ProjectView;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.student.view.StudentView;
import br.gov.sp.fatec.teacher.view.TeacherView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    private Long id;

    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    protected String email;

    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    protected String password;

    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    protected String name;

    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    protected Boolean active;

    @Transient
    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    private String token;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authorization",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authorization_id") })
    @JsonView({ProjectView.Project.class, TeacherView.Teacher.class, EntrepreneurView.Entrepreneur.class, CadiView.Cadi.class, StudentView.Student.class})
    private List<Authorization> authorizations;

    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorizations;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}

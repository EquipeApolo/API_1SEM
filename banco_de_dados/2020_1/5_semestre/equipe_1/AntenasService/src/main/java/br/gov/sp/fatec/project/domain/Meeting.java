package br.gov.sp.fatec.project.domain;

import br.gov.sp.fatec.project.view.ProjectView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ProjectView.Project.class})
    private Long id;

    @JsonView({ProjectView.Project.class})
    @Column(name = "chosen_date")
    private java.util.Date chosenDate;

    @JsonView({ProjectView.Project.class})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @JsonView({ProjectView.Project.class})
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="meeting_possible_date",
            joinColumns=@JoinColumn(name="meeting_id"),
            inverseJoinColumns=@JoinColumn(name="possible_date_id"))
    private List<Date> possibleDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getChosenDate() {
        return chosenDate;
    }

    public void setChosenDate(java.util.Date chosenDate) {
        this.chosenDate = chosenDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Date> getPossibleDate() {
        return possibleDate;
    }

    public void setPossibleDate(List<Date> possibleDate) {
        this.possibleDate = possibleDate;
    }
}

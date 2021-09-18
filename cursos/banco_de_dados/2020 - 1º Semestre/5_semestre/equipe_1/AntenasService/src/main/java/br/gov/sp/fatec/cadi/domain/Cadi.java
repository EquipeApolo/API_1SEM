package br.gov.sp.fatec.cadi.domain;

import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.cadi.view.CadiView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "cadi")
@PrimaryKeyJoinColumn(name = "id")
public class Cadi extends User {

    @JsonView({CadiView.Cadi.class})
    private String cpf;

    @JsonView({CadiView.Cadi.class})
    private String position;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

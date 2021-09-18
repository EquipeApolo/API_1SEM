package br.gov.sp.fatec.teacher.domain;

import br.gov.sp.fatec.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends User {

}

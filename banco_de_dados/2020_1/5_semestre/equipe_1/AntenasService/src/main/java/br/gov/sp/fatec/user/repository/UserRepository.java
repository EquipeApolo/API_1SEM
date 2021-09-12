package br.gov.sp.fatec.user.repository;

import br.gov.sp.fatec.user.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByNameContainsIgnoreCase(String name);

    List<User> findAllByActive(Boolean active);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    @Query("select u from User u where u.name like %?1%")
    public List<User> searchUser(String name);
}

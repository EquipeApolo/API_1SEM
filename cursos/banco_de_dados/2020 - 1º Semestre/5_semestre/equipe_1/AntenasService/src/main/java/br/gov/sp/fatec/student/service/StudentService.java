package br.gov.sp.fatec.student.service;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.repository.StudentRepository;
import br.gov.sp.fatec.utils.commons.SendEmail;
import br.gov.sp.fatec.utils.exception.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static br.gov.sp.fatec.utils.exception.InactiveException.throwIfStudentIsInactive;
import static br.gov.sp.fatec.utils.exception.NotFoundException.throwIfStudentIsNull;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student save(Student student) {
        student.setActive(false);
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        List<Authorization> authorizations = new ArrayList<>();
        Authorization authorization = new Authorization();
        authorization.setName("STUDENT");
        authorization.setAuthority("STUDENT");
        authorizations.add(authorization);

        student.setAuthorizations(authorizations);

        sendEmail.sendMail(student.getEmail(), "student");
        return repository.save(student);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public List<Student> findAllById(List<Long> idList) {
        return repository.findAllById(idList);
    }

    public List<Student> findActive() {
        return repository.findAllByActive(true);
    }

    public Student findById(Long id) {
        Student found = repository.findById(id).orElse(null);
        NotFoundException.throwIfStudentIsNull(found, id);
        return found;
    }

    // todo - pode pegar o id do aluno....?
    public Student update(Long id, Student student) {
        Student found = findById(id);

        found.setName(student.getName());
        found.setEmail(student.getEmail());
        found.setActive(student.isActive());
        found.setProjects(student.getProjects());

        return found;
    }

    public Student login(Map<String, String> login) {
        String password = login.get("password");
        String email = login.get("email");

        password =  Base64.getEncoder().encodeToString(password.getBytes());
        Student student = repository.findByEmailAndPassword(email, password);

        throwIfStudentIsNull(student);
        throwIfStudentIsInactive(student);

        return student;
    }

    // todo - adicionar retorno para quando der erro
    public void activate(String b64) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new String(Base64.getDecoder().decode(b64)));
            Student found = repository.findByEmail(jsonObject.get("email").toString());
            throwIfStudentIsNull(found);

            found.setActive(true);
            repository.save(found);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

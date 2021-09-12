package br.gov.sp.fatec.teacher.service;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.repository.TeacherRepository;
import br.gov.sp.fatec.utils.commons.SendEmail;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.InactiveException.throwIfTeacherIsInactive;
import static br.gov.sp.fatec.utils.exception.NotFoundException.throwIfTeacherIsNull;

@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Teacher save(Teacher teacher, String url) {
        teacher.setActive(false);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));

        List<Authorization> authorizations = new ArrayList<>();
        Authorization authorization = new Authorization();
        authorization.setName("REPRESENTATIVE");
        authorization.setAuthority("REPRESENTATIVE");
        authorizations.add(authorization);

        teacher.setAuthorizations(authorizations);

        sendEmail.sendMail(teacher.getEmail(), url);

        return repository.save(teacher);
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public List<Teacher> findActive() {
        return repository.findAllByActive(true);
    }

    public Teacher findById(Long id) {
        Teacher teacher = repository.findById(id).orElse(null);
        throwIfTeacherIsNull(teacher, id);
        return teacher;
    }

    public Teacher update(Long id, Teacher teacher) {
        Teacher found = repository.findById(id).orElse(null);
        throwIfTeacherIsNull(found, id);

        found.setActive(teacher.isActive());
        found.setName(teacher.getName());
        found.setEmail(teacher.getEmail());

        return repository.save(found);
    }

    public Teacher login(String email, String password) {
        password =  Base64.getEncoder().encodeToString(password.getBytes());
        Teacher teacher = repository.findByEmailAndPassword(email, password);

        throwIfTeacherIsNull(teacher);
        throwIfTeacherIsInactive(teacher);

        return teacher;
    }

    public void activate(String b64) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new String(Base64.getDecoder().decode(b64)));
            Teacher found = repository.findByEmail(jsonObject.get("email").toString());
            throwIfTeacherIsNull(found);

            found.setActive(true);
            repository.save(found);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

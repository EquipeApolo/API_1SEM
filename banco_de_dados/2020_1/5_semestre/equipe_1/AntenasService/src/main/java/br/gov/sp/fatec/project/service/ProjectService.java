package br.gov.sp.fatec.project.service;

import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;
import br.gov.sp.fatec.entrepreneur.service.EntrepreneurService;
import br.gov.sp.fatec.project.domain.Date;
import br.gov.sp.fatec.project.domain.Deliver;
import br.gov.sp.fatec.project.domain.Meeting;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.repository.ProjectRepository;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.exception.StudentException;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.exception.TeacherException;
import br.gov.sp.fatec.teacher.service.TeacherService;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;

import static br.gov.sp.fatec.utils.exception.InactiveException.*;
import static br.gov.sp.fatec.utils.exception.NotFoundException.*;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EntrepreneurService entrepreneurService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Transient
    @Value("${picklejar.jwt.secret}")
    private String secret;

    // botar no userService
    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = userService.findByEmail(authentication.getName());
            return user.getId();
        }
        return null;
    }
//    @PreAuthorize("hasRole('REPRESENTATIVE')")
    public Project save(Project project) {
        Entrepreneur found = entrepreneurService.findById(getUserId());
        throwIfEntrepreneurIsNull(found);
        throwIfEntrepreneurIsInactive(found);
        project.setEntrepreneur(found);

        project.setCreatedAt(ZonedDateTime.now());
        project.setProgress(2);
        return repository.save(project);
    }

//    @PreAuthorize("hasAnyRole('CADI', 'REPRESENTATIVE', 'STUDENT', 'TEACHER')")
    public List<Project> findAll(Long id) {
    // todo -  pegar id pelo back
//        userService.search(getUserId(token));
       String authorization = userService.search(id).getAuthorizations().get(0).getName();

       List<Project> projects = new ArrayList<>();

       if (authorization.equals("REPRESENTATIVE")) {
           projects = getProjectByEntrepreneur(id);
       } else if (authorization.equals("STUDENT")) {
           projects = getProjectByStudent(id);
           for (Project project: getProjectByStudentResponsible(id)) {
               projects.add(project);
           }
       } else if (authorization.equals("CADI")) {
//           projects = getProjectByStudent(id);
           projects = repository.findAll();
       } else if (authorization.equals("TEACHER")) {
           projects = getProjectByTeacher(id);
       }

//        List<Project> projects = repository.findAll();

//        for (Project project : projects) {
//            checkIfFieldIsNull(project);
//        }

        return projects;
    }


    public List<Project> checkListProjectFieldIsNull(List<Project> projects) {
        return projects;
    }

//    @PreAuthorize("hasAnyRole('CADI', 'REPRESENTATIVE', 'STUDENT', 'TEACHER')")
    public Project findById(Long id) {
        Project project =  repository.findById(id).orElse(null);
        throwIfProjectIsNull(project, id);
        return project;
    }

//    @PreAuthorize("hasRole('REPRESENTATIVE')")
    public void delete(Long id) {
        Project project = findById(id);
        throwIfProjectIsNull(project, id);
        repository.delete(project);
    }

    public long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

//    @PreAuthorize("hasRole('TEACHER')")
    public Project setStudentResponsible(Long projectId, Long studentId, Long teacherId) {
        checkIfCanAddStudentToProject(teacherId, projectId);
        Project project = findById(projectId);
        throwIfProjectIsNull(project, projectId);

        Student student =  studentService.findById(studentId);
        throwIfStudentIsNull(student, studentId);
        throwIfStudentIsInactive(student);

        project.setStudentResponsible(student);
        return repository.save(project);
    }

    private void checkIfCanAddStudentToProject(Long teacherId, Long projectId) {
        Teacher teacher = teacherService.findById(teacherId);
        throwIfTeacherIsNull(teacher, teacherId);
        throwIfTeacherIsInactive(teacher);

        Project project = findById(projectId);
        throwIfProjectIsNull(project);

        if (project.getTeacher() == null || !project.getTeacher().getId().equals(teacher.getId())) {
            throw new TeacherException.CannotAddOrRemoveStudentsToThisProject();
        }
    }

//    @PreAuthorize("hasRole('TEACHER')")
    // serve para editar também. ele sobrescreve. Só deve ser passado todos os alunos
    public Project setStudents(Long projectId, List<Student> studentList, Long teacherId) {
        checkIfCanAddStudentToProject(teacherId, projectId);
        Project project = findById(projectId);
        throwIfProjectIsNull(project, projectId);

        List<Student> students = new LinkedList<>();

        for (Student student : studentList) {
            Student found = studentService.findById(student.getId());
            throwIfStudentIsNull(found, student.getId());
            throwIfStudentIsInactive(found);

            students.add(found);
        }
        project.setStudents(students);
        return repository.save(project);
    }

//    @PreAuthorize("hasRole('TEACHER')")
    public Project addStudent(Long projectId, Long studentId) {
        Project project = findById(projectId);
        throwIfProjectIsNull(project, projectId);

        Student student = studentService.findById(studentId);
        throwIfStudentIsNull(student, studentId);
        throwIfStudentIsInactive(student);

        project.getStudents().add(student);
        return repository.save(project);
    }

    public void setTeacher(Project project) {
        Teacher teacher = teacherService.findById(project.getTeacher().getId());
        throwIfTeacherIsNull(teacher, project.getTeacher().getId());
        throwIfTeacherIsInactive(teacher);

        project.setTeacher(teacher);
//        return repository.save(project);
    }

//    @PreAuthorize("hasRole('CADI')")
    public Project setTeacher(Long projectId, Long teacherId) {
        Project project = findById(projectId);
        throwIfProjectIsNull(project, projectId);

        Teacher teacher = teacherService.findById(teacherId);
        throwIfTeacherIsNull(teacher, teacherId);
        throwIfTeacherIsInactive(teacher);

        project.setTeacher(teacher);
        return repository.save(project);
    }

//    @PreAuthorize("hasRole('TEACHER')")
    public Project removeStudent(Long projectId, Long StudentId) {
        Project project = findById(projectId);
        throwIfProjectIsNull(project, projectId);

        Student student = studentService.findById(StudentId);
        throwIfStudentIsNull(student, StudentId);

        project.getStudents().remove(student);
        return repository.save(project);
    }

    public List<Project> getProjectByTeacher(Long teacherId) {
        List<Project> projects = repository.findByTeacherId(teacherId);
        return projects;
    }

    public List<Project> getProjectByStudent(Long studentId) {
        List<Project> projects = repository.findByStudentsId(studentId);

        return projects;
    }

    public List<Project> getProjectByStudentResponsible(Long studentId) {
        List<Project> projects = repository.findByStudentResponsibleId(studentId);
        return projects;
    }

    public List<Project> getProjectByEntrepreneur(Long entrepreneurId) {
        List<Project> projects = repository.findByEntrepreneurId(entrepreneurId);
        return projects;
    }

//    @PreAuthorize("hasRole('STUDENT')")
    public Project setSolution(Long projectId, Deliver deliver) {
        Project project = findById(projectId);
        throwIfProjectIsNull(project);

        project.setProgress(6);
        project.getDeliver().add(deliver);
        return repository.save(project);
    }

//    @PreAuthorize("hasRole('REPRESENTATIVE')")
    public Project update(Project project) {
        Project found = findById(project.getId());
        throwIfProjectIsNull(found);

        found.setCompleteDescription(project.getCompleteDescription());
        found.setTechnologyDescription(project.getTechnologyDescription());
        found.setTitle(project.getTitle());
        found.setShortDescription(project.getShortDescription());
        found.setNotes(project.getNotes());
        found.setMeeting(project.getMeeting());
        found.setProgress(getProgress(found));
        found.setMeeting(project.getMeeting());
        found.setDeliver(project.getDeliver());

        if (project.getStudentResponsible() != null) {
            Student studentResponsible = studentService.findById(project.getStudentResponsible().getId());
            throwIfStudentIsNull(studentResponsible);
            found.setStudentResponsible(studentResponsible);
        }

        found.setStudents(new ArrayList<>());
        for (Student student : project.getStudents()) {
            Student studentFound = studentService.findById(student.getId());
            throwIfStudentIsNull(student);
            found.getStudents().add(studentFound);
        }
        if (project.getTeacher() != null) {
            Teacher teacher = teacherService.findById(project.getTeacher().getId());
            throwIfTeacherIsNull(teacher);
            found.setTeacher(teacher);
        }

        return repository.save(found);
    }

    private int getProgress (Project project) {
        if (project.getCompleteDescription() != null && project.getTechnologyDescription() != null && project.getProgress() == 3) {
            return 4;
        } if (project.getProgress() == 4 && project.getCompleteDescription() != null && project.getTechnologyDescription() != null) {
            return 5;
        } else if (project.getProgress() == 2 && project.getCompleteDescription() == null && project.getTechnologyDescription() == null) {
            return 3;
        } else if (project.getProgress() == 5 && project.getMeeting() != null && project.getMeeting().getPossibleDate().size() > 0 && project.getMeeting().getChosenDate() != null) {
            return 6;
        } else if (project.getProgress() == 6 && project.getDeliver().size() > 0) {
            return 7;
        }
        else {
            return project.getProgress();
        }
    }

//    @PreAuthorize("hasRole('CADI')")
    public Project setMeetingPossibleDate(List<Date> possibleDate, Long projectId) {
        for (Date date : possibleDate) {
            throwIfDateIsNull(date);
        }

        Project project = findById(projectId);
        throwIfProjectIsNull(project);

        Meeting meeting = new Meeting();
        meeting.setPossibleDate(possibleDate);

        project.setMeeting(meeting);
        project.setProgress(getProgress(project));

        return repository.save(project);
    }

//    @PreAuthorize("hasRole('REPRESENTATIVE')")
    public Project setMeetingChosenDate(Long possibleDateId, Long projectId) {
        Project project = findById(projectId);
        throwIfProjectIsNull(project);

        List<Date> possibleDateList = project.getMeeting().getPossibleDate();

        Date date = possibleDateList.stream().filter(
                possibleDate -> possibleDateId.equals(possibleDate.getId()))
                .findFirst().orElse(null);

        throwIfDateIsNull(date);

        project.getMeeting().setChosenDate(date.getDateTime());
        project.setProgress(getProgress(project));

        return repository.save(project);
    }

//    @PreAuthorize("hasRole('CADI')")
    public Project approve(Long id) {
        Project project = findById(id);
        throwIfProjectIsNull(project);

        project.setProgress(getProgress(project));
        return repository.save(project);
    }

    public Map<String, List<Project>> findProjectByStudent(Long studentId) {
        Map<String, List<Project>> projects = new HashMap<>();
        projects.put("responsible", getProjectByStudentResponsible(studentId));
        projects.put("team", getProjectByStudent(studentId));

        return projects;
    }

//    @PreAuthorize("hasRole('STUDENT')")
    public Project setSolution(Deliver deliver, Long projectId) {
        // todo - pegar id do aluno responsavel
        Project project = findById(projectId);
        throwIfProjectIsNull(project);

        if (!project.getStudentResponsible().getId().equals(deliver.getStudentResponsible().getId())) {
            throw new StudentException.PostSolutionFailedException();
        }

        deliver.setStudents(project.getStudents());
        deliver.setStudentResponsible(project.getStudentResponsible());
        deliver.getProjects().add(project);

        Project returnProject = setSolution(projectId, deliver);;
        return returnProject;
    }

//    private String generateCode() {
//        boolean unique = false;
//
//        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        String key = "";
//
//        while (!unique) {
//            while (salt.length() < 7) {
//                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//                salt.append(SALTCHARS.charAt(index));
//            }
//
//            key = salt.toString();
//
//            if (repository.findByAccessKey(key) == null) {
//                unique = true;
//            }
//        }
//
//        return key;
//    }

//    public Project StudentsEnterProjectByAccessKey(String accessKey, Long studentId) {
//        Project project = repository.findByAccessKey(accessKey);
//        throwIfProjectIsNull(project);
//
//        Student student = studentService.findById(studentId);
//        throwIfStudentIsNull(student, studentId);
//        throwIfStudentIsInactive(student);
//
//        project.getStudents().add(student);
//
//        return project;
//    }
//
//    public Project studentResponsibleEnterProjectByAccessKey(String accessKey, Long studentId) {
//        Project project = repository.findByAccessKey(accessKey);
//        throwIfProjectIsNull(project);
//
//        Student student = studentService.findById(studentId);
//        throwIfStudentIsNull(student, studentId);
//        throwIfStudentIsInactive(student);
//
//        project.setStudentResponsible(student);
//
//        return project;
//    }
//
//    public Project teacherEnterProjectByAccessKey(String accessKey, Long teacherId) {
//        Project project = repository.findByAccessKey(accessKey);
//        throwIfProjectIsNull(project);
//
//        Teacher teacher = teacherService.findById(teacherId);
//        throwIfTeacherIsNull(teacher, teacherId);
//        throwIfTeacherIsInactive(teacher);
//
//        project.setTeacher(teacher);
//
//        return project;
//    }
}

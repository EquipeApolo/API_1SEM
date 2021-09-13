package br.gov.sp.fatec.project.fixture;

import br.gov.sp.fatec.project.domain.*;
import br.gov.sp.fatec.student.domain.Student;

import java.util.LinkedList;
import java.util.List;

import static br.gov.sp.fatec.entrepreneur.fixture.EntrepreneurFixture.newEntrepreneur;
import static br.gov.sp.fatec.student.fixture.StudentFixture.newStudent;
import static br.gov.sp.fatec.student.fixture.StudentFixture.newStudentNoProject;
import static br.gov.sp.fatec.teacher.fixture.TeacherFixture.newTeacher;

public class ProjectFixture {

    private static final String COMPLETE_DESCRIPTION = "projeto que faz uma banana de cimento comestível para combater carrapatos em cinemas";
    private static final String TECHNOLOGY_DESCRIPTION = "Uma banana feita de cimento que pode ser ingerido que persegue carrapatos e batalha com eles, para expula-los dos cinemas";
    private static final String SHORT_DESCRIPTION = "banana de cimento que combate carrapato";
    private static final String TITLE = "destruidora de carrapato";
    private static final String CLOUD_LINK = "cloud.com";
    private static final String REPOSITORY_LINK = "repository.com";
    private static final String COMMENT = "Comentario da entrega";
    private static final Long ID = 1L;

    public static Project newProject() {
        Project project = new Project();
        project.setId(ID);
        project.setCompleteDescription(COMPLETE_DESCRIPTION);
        project.setEntrepreneur(newEntrepreneur());
        project.setDeliver(getDeliver());
        project.setMeeting(newMeeting());
        project.setShortDescription(SHORT_DESCRIPTION);
        project.setStudentResponsible(newStudentNoProject(ID, true));
        project.setStudents(getStudents());
        project.setTeacher(newTeacher());
        project.setTechnologyDescription(TECHNOLOGY_DESCRIPTION);
        project.setTitle(TITLE);

        return project;
    }

    public static Project newProject(Long id) {
        Project project = new Project();
        project.setId(id);
        project.setCompleteDescription(COMPLETE_DESCRIPTION);
        project.setEntrepreneur(newEntrepreneur());
        project.setDeliver(getDeliver());
        project.setMeeting(newMeeting());
        project.setShortDescription(SHORT_DESCRIPTION);
        project.setStudentResponsible(newStudentNoProject(ID, true));
        project.setStudents(getStudents());
        project.setTeacher(newTeacher());
        project.setTechnologyDescription(TECHNOLOGY_DESCRIPTION);
        project.setTitle(TITLE);

        return project;
    }

    public static Deliver newDeliver() {
        Deliver deliver = new Deliver();
        deliver.setId(ID);
        deliver.setStudentResponsible(newStudent());
        deliver.setCloudLink(CLOUD_LINK);
        deliver.setStudents(getStudents());
        deliver.setComment(COMMENT);
        deliver.setRepositoryLink(REPOSITORY_LINK);

        return deliver;
    }

    public static Deliver newDeliver(Long id) {
        Deliver deliver = new Deliver();
        deliver.setId(id);
        deliver.setStudentResponsible(newStudentNoProject());
        deliver.setCloudLink(CLOUD_LINK);
        deliver.setStudents(getStudents());
        deliver.setComment(COMMENT);
        deliver.setRepositoryLink(REPOSITORY_LINK);

        return deliver;
    }

    public static List<Deliver> getDeliver() {
        List<Deliver> deliverList = new LinkedList<>();

        for (long i = 0; i < 3; i++) {
            deliverList.add(newDeliver(i));
        }

        return deliverList;
    }

    public static Meeting newMeeting() {
        Meeting meeting = new Meeting();
        meeting.setId(ID);
        meeting.setAddress(newAddress());
        meeting.setChosenDate(new java.util.Date());
        meeting.setPossibleDate(getPossibleDate());

        return meeting;
    }

    public static Address newAddress() {
        Address address = new Address();
        address.setId(ID);
        address.setCity("SJC");
        address.setNeighborhood("vila do chaves");
        address.setPlace("Barril do chaves");
        address.setStreet("rua dos bobos");
        address.setNumber(0);
        address.setZip("1234-123");

        return address;
    }

    public static Date newDate(Long id) {
        Date date = new Date();
        date.setId(id);
        date.setDateTime(new java.util.Date());

        return date;
    }

    public static List<Date> getPossibleDate() {
        List<Date> dateList = new LinkedList<>();

        for (long i = 0; i < 3; i++) {
            dateList.add(newDate(i));
        }

        return dateList;
    }

    public static List<Student> getStudents() {
        List<Student> studentList = new LinkedList<>();
        studentList.add(newStudentNoProject(1L, true));
        return studentList;
    }
}

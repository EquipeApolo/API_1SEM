package br.gov.sp.fatec.teacher.fixture;

import br.gov.sp.fatec.teacher.domain.Teacher;

public class TeacherFixture {

    private static final String EMAIL = "email@teste.com";
    private static final String NAME = "test";
    private static final String PASSWORD = "test";
    private static final Long ID = 1L;
    private static final Boolean ACTIVE = true;

    public static Teacher newTeacher(Long id, boolean active) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setActive(active);
        teacher.setName(NAME);
        teacher.setEmail(EMAIL);
        teacher.setPassword(PASSWORD);

        return teacher;
    }

    public static Teacher newTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(ID);
        teacher.setActive(ACTIVE);
        teacher.setName(NAME);
        teacher.setEmail(EMAIL);
        teacher.setPassword(PASSWORD);

        return teacher;
    }
}

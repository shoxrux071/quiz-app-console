package org.example.service.student;

import lombok.NonNull;
import org.example.configs.ApplicationContextHolder;
import org.example.dao.AbstractDAO;
import org.example.dao.auth.AuthUserDAO;
import org.example.dao.student.StudentDAO;
import org.example.domains.auth.AuthUser;
import org.example.domains.users.Student;
import org.example.exception.ValidationException;
import org.example.service.GenericCRUDService;
import org.example.utils.BaseUtils;
import org.example.utils.validator.student.StudentValidator;
import org.example.vo.auth.Session;
import org.example.vo.http.AppErrorVO;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;
import org.example.vo.student.StudentCreateVO;
import org.example.vo.student.StudentUpdateVO;
import org.example.vo.student.StudentVO;

import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 01:05 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class StudentService extends AbstractDAO<StudentDAO> implements GenericCRUDService<
        StudentVO,
        StudentCreateVO,
        StudentUpdateVO,
        Long> {



    private final StudentValidator studentValidator = ApplicationContextHolder.getBean(StudentValidator.class);


    private static AuthUserDAO authUserDAO = ApplicationContextHolder.getBean(AuthUserDAO.class);

    private static StudentService instance;


    public StudentService() {

        super(
                ApplicationContextHolder.getBean(StudentDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class));
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }


    @Override
    public Response<DataVO<Long>> create(@NonNull StudentCreateVO vo) {
        try {
            studentValidator.validOnCreate(vo);

            AuthUser authUser = authUserDAO.findById(Session.sessionUser.getId());
            Student student = Student
                    .builder()
                    .name(vo.getName())
                    .surname(vo.getSurname())
                    .user(authUser)
                    .build();

            Student save = dao.save(student);


            return new Response<>(new DataVO<>(save.getId()), 200);
        } catch (ValidationException e) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public Response<DataVO<Void>> update(@NonNull StudentUpdateVO vo) {
        return null;
    }

    @Override
    public Response<DataVO<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<DataVO<StudentVO>> get(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<DataVO<List<StudentVO>>> getAll() {
        return null;
    }
}

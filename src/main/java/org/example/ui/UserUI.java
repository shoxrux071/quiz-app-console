package org.example.ui;

import org.example.configs.ApplicationContextHolder;
import org.example.enums.AuthRole;
import org.example.service.auth.AuthUserService;
import org.example.service.student.StudentService;
import org.example.service.teacher.TeacherService;
import org.example.vo.auth.Session;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;
import org.example.vo.student.StudentCreateVO;
import org.example.vo.teacher.TeacherCreateVO;
import uz.jl.BaseUtils;
import uz.jl.Colors;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 02:51 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class UserUI {

    private static final AuthUserService authUserService = ApplicationContextHolder.getBean(AuthUserService.class);
    private static final StudentService studentService = ApplicationContextHolder.getBean(StudentService.class);
    private static final TeacherService teacherService = ApplicationContextHolder.getBean(TeacherService.class);

    public static void main(String[] args) {
        BaseUtils.println("2 -> Login as Teacher");
        BaseUtils.println("l -> LogOut");
        BaseUtils.println("q -> Quit");
        String choice = BaseUtils.readText("choice ? ");

        switch (choice) {
            case "1" -> setUserAsStudent();
            case "2" -> setUserAsTeacher();
            case "l" -> Session.setSessionUser(null);
            case "q" -> {
                BaseUtils.println("Bye");
                System.exit(0);
            }
            default -> BaseUtils.println("Invalid choice");
        }
    }

    private static void setUserAsTeacher() {
        TeacherCreateVO vo = TeacherCreateVO.builder()
                .name(BaseUtils.readText("Name ? "))
                .surname(BaseUtils.readText("Surname ? "))
                .build();


        Response<DataVO<Long>> response = teacherService.create(vo);
        print_response(response);
        if (response.getStatus() != 200)
            return;

        authUserService.setRole(Session.sessionUser.getId(), AuthRole.TEACHER);
        Session.sessionUser.setRole(AuthRole.TEACHER);
    }

    private static void setUserAsStudent() {
        StudentCreateVO vo = StudentCreateVO.builder()
                .name(BaseUtils.readText("Name ? "))
                .surname(BaseUtils.readText("Surname ? "))
                .build();
        Response<DataVO<Long>> response = studentService.create(vo);
        print_response(response);
        if (response.getStatus() != 200)
            return;
        authUserService.setRole(Session.sessionUser.getId(), AuthRole.STUDENT);
        Session.sessionUser.setRole(AuthRole.STUDENT);
    }

    public static void print_response(Response response) {

        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

}

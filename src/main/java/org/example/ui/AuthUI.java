package org.example.ui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.configs.ApplicationContextHolder;
import org.example.service.auth.AuthUserService;
import org.example.vo.auth.AuthCreateVO;
import org.example.vo.auth.AuthVO;
import org.example.vo.auth.Session;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;
import uz.jl.BaseUtils;
import uz.jl.Colors;

import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 02:26 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class AuthUI {

    private static final AuthUI authUI = new AuthUI();
    AuthUserService service = ApplicationContextHolder.getBean(AuthUserService.class);

    public static void main(String[] args) {

        if (Objects.isNull(Session.sessionUser)) {
            BaseUtils.println("============welcome to Quiz App===========", Colors.BLUE);
            BaseUtils.println("Login -> 1");
            BaseUtils.println("Register -> 2");
            BaseUtils.println("Quit -> q");

            String choice = BaseUtils.readText("choice ? ");

            switch (choice) {
                case "1" -> authUI.login();
                case "2" -> authUI.register();
                case "q" -> {
                    BaseUtils.println("Bye");
                    System.exit(0);
                }
                default -> BaseUtils.println("Invalid choice");
            }
        } else {
            switch (Session.sessionUser.getRole()) {
                case STUDENT -> StudentUI.main(args);
                case ADMIN -> AdminUI.main(args);
                case TEACHER -> TeacherUI.main(args);
                case USER -> UserUI.main(args);
            }
        }
        main(args);

    }


    private void register() {
        AuthCreateVO vo = AuthCreateVO.builder()
                .username(BaseUtils.readText("username ? "))
                .password(BaseUtils.readText("password ? "))
                .email(BaseUtils.readText("email ? "))
                .build();
        Response<DataVO<Long>> response = service.create(vo);
        print_response(response);
    }

    private void login() {
        Response<DataVO<AuthVO>> response = service.login(
                BaseUtils.readText("username ? "),
                BaseUtils.readText("password ? ")
        );
        print_response(response);
    }

    public static void print_response(Response response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

}

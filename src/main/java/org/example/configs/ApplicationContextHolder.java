package org.example.configs;

import org.example.dao.auth.AuthUserDAO;
import org.example.dao.qa.QuestionDAO;
import org.example.dao.student.StudentDAO;
import org.example.dao.subject.SubjectDAO;
import org.example.dao.teacher.TeacherDAO;
import org.example.dao.variant.VariantDAO;
import org.example.service.auth.AuthUserService;
import org.example.service.question.QuestionService;
import org.example.service.student.StudentService;
import org.example.service.subject.SubjectService;
import org.example.service.teacher.TeacherService;
import org.example.service.variant.VariantService;
import org.example.utils.BaseUtils;
import org.example.utils.validator.authUser.AuthUserValidator;
import org.example.utils.validator.question.QuestionValidator;
import org.example.utils.validator.student.StudentValidator;
import org.example.utils.validator.subject.SubjectValidator;
import org.example.utils.validator.teacher.TeacherValidator;
import org.example.utils.validator.variant.VariantValidator;


/**
 * @author "Berdimurodov Shoxrux"
 * @since 03/11/22 23:59 (Thursday)
 * quiz-app-console/IntelliJ IDEA
 */
@SuppressWarnings("unchecked")
public class ApplicationContextHolder {
    public static <T> T getBean(String beanName) {
        return switch (beanName) {
            case "BaseUtils" -> (T) BaseUtils.getInstance();
            case "AuthUserService" -> (T) AuthUserService.getInstance();
            case "StudentService" -> (T) StudentService.getInstance();
            case "SubjectService" -> (T) SubjectService.getInstance();
            case "VariantService" -> (T) VariantService.getInstance();
            case "TeacherService" -> (T) TeacherService.getInstance();
            case "QuestionService" -> (T) QuestionService.getInstance();
            case "AuthUserValidator" -> (T) AuthUserValidator.getInstance();
            case "StudentValidator" -> (T) StudentValidator.getInstance();
            case "VariantValidator" -> (T) VariantValidator.getInstance();
            case "SubjectValidator" -> (T) SubjectValidator.getInstance();
            case "QuestionValidator" -> (T) QuestionValidator.getInstance();
            case "TeacherValidator" -> (T) TeacherValidator.getInstance();
            case "QuestionDAO" -> (T) QuestionDAO.getInstance();
            case "AuthUserDAO" -> (T) AuthUserDAO.getInstance();
            case "SubjectDAO" -> (T) SubjectDAO.getInstance();
            case "VariantDAO" -> (T) VariantDAO.getInstance();
            case "StudentDAO" -> (T) StudentDAO.getInstance();
            case "TeacherDAO" -> (T) TeacherDAO.getInstance();
            default -> throw new RuntimeException("Bean Not Found");
        };
    }

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        return getBean(beanName);
    }


}

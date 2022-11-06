package org.example.utils.validator.teacher;

import org.example.exception.ValidationException;
import org.example.utils.validator.GenericValidator;
import org.example.vo.teacher.TeacherCreateVO;
import org.example.vo.teacher.TeacherUpdateVO;

import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 00:13 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class TeacherValidator extends GenericValidator<TeacherCreateVO, TeacherUpdateVO, Long> {

    private static TeacherValidator instance;

    public static TeacherValidator getInstance() {
        if (instance == null) {
            instance = new TeacherValidator();
        }
        return instance;

    }


    @Override
    public void validateKey(Long id) throws ValidationException {

    }

    @Override
    public void validOnCreate(TeacherCreateVO vo) throws ValidationException {


        existsByName(vo.getName());
        existsBySurname(vo.getSurname());

    }

    @Override
    public void validOnUpdate(TeacherUpdateVO vo) throws ValidationException {

    }


    private void existsBySurname(String surname) {
        if (Objects.nonNull(surname)) {
            if (surname.isBlank())
                throw new ValidationException("The surname cannot be empty ");
        } else
            throw new ValidationException("The surname cannot be null ");


    }

    private void existsByName(String name) {
        if (Objects.nonNull(name)) {
            if (name.isBlank()) {
                throw new ValidationException("The name cannot be empty ");

            }
        } else throw new ValidationException("The name cannot be null");

    }

}

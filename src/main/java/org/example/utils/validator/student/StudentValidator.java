package org.example.utils.validator.student;

import org.example.exception.ValidationException;
import org.example.utils.validator.GenericValidator;
import org.example.vo.student.StudentCreateVO;
import org.example.vo.student.StudentUpdateVO;

import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 00:08 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class StudentValidator extends GenericValidator<StudentCreateVO, StudentUpdateVO, Long> {


    private static StudentValidator instance;

    public static StudentValidator getInstance() {
        if (instance == null) {
            instance = new StudentValidator();
        }
        return instance;

    }

    @Override
    public void validateKey(Long id) throws ValidationException {

    }

    @Override
    public void validOnCreate(StudentCreateVO vo) throws ValidationException {

        existsByName(vo.getName());
        existsBySurname(vo.getSurname());

    }

    @Override
    public void validOnUpdate(StudentUpdateVO vo) throws ValidationException {

    }

    private void existsBySurname(String surname) {
        if (Objects.nonNull(surname)) {
            if (surname.isBlank()) {
                throw new ValidationException("The surname cannot be empty ");
            }
        } else
            throw new ValidationException("The surname cannot be null ");

    }


    private void existsByName(String name) {
        if (Objects.nonNull(name)) {
            if (name.isBlank()) {
                throw new ValidationException("The name cannot be empty ");

            }    }else throw new ValidationException("The name cannot be null");

    }
}

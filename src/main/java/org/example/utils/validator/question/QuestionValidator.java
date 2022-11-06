package org.example.utils.validator.question;

import org.example.configs.ApplicationContextHolder;
import org.example.dao.qa.QuestionDAO;
import org.example.exception.ValidationException;
import org.example.utils.validator.GenericValidator;
import org.example.vo.question.QuestionCreateVO;
import org.example.vo.question.QuestionUpdateVO;

import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 22:55 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */
public class QuestionValidator extends GenericValidator<QuestionCreateVO, QuestionUpdateVO, Long> {


    private static QuestionValidator instance;

    public static QuestionValidator getInstance() {
        if (instance == null) {
            instance = new QuestionValidator();
        }
        return instance;

    }


    QuestionDAO questionDAO = ApplicationContextHolder.getBean(QuestionDAO.class);



    @Override
    public void validateKey(Long id) throws ValidationException {

    }

    @Override
    public void validOnCreate(QuestionCreateVO vo) throws ValidationException {

        if (Objects.isNull(vo.getBody()) ) {
            throw new ValidationException("Question body can not be null");
        }
        if(Objects.isNull(vo.getAnswerCreateVOList()) ){
            throw new ValidationException("Answer list can not be null");
        }
        if(Objects.isNull(vo.getSubjectName())){
            throw new ValidationException("Subject name can not be null");
        }
    }

    @Override
    public void validOnUpdate(QuestionUpdateVO vo) throws ValidationException {

    }
}

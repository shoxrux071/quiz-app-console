package org.example.utils.validator;

import org.example.exception.ValidationException;
import org.example.vo.BaseVO;
import org.example.vo.GenericVO;
import org.example.vo.auth.AuthCreateVO;

import java.io.Serializable;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 22:10 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */
public abstract class GenericValidator<CreateVO extends BaseVO, UpdateVO extends GenericVO, K extends Serializable> implements BaseValidator {

    public abstract void validateKey(K id) throws ValidationException;

    public abstract void validOnCreate(CreateVO vo) throws ValidationException;

    public abstract void validOnUpdate(UpdateVO vo) throws ValidationException;


}

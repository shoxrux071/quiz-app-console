package org.example.utils.validator.authUser;

import org.example.configs.ApplicationContextHolder;
import org.example.dao.auth.AuthUserDAO;
import org.example.domains.auth.AuthUser;
import org.example.exception.ValidationException;
import org.example.utils.validator.GenericValidator;
import org.example.vo.BaseVO;
import org.example.vo.GenericVO;
import org.example.vo.auth.AuthCreateVO;
import org.example.vo.auth.AuthUpdateVO;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 02:05 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */
public class AuthUserValidator extends GenericValidator<AuthCreateVO, AuthUpdateVO, Long> {

    private static AuthUserValidator instance;

    public static AuthUserValidator getInstance(){
        if (instance == null){
            instance =new AuthUserValidator();
        }
        return instance;
    }

    AuthUserDAO authUserDAO = ApplicationContextHolder.getBean(AuthUserDAO.class);



    @Override
    public void validateKey(Long id) throws ValidationException {
        existsById(id);
    }

    @Override
    public void validOnCreate(AuthCreateVO vo) throws ValidationException {
        existsByUsername(vo.getUsername());
        existsByEmail(vo.getEmail());
    }

    @Override
    public void validOnUpdate(AuthUpdateVO vo) throws ValidationException {

    }


    private void existsByUsername(String username){
        if (Objects.nonNull(username)){
            Optional<AuthUser> byUserName = authUserDAO.findByUserName(username);
            if (byUserName.isPresent() && !byUserName.get().getDeleted()){
                throw new ValidationException("user already exists by username");
            }
        }
    }

    private void existsByEmail(String email){
        if (Objects.nonNull(email)){
            Optional<AuthUser> byEmail = authUserDAO.findByEmail(email);
            if (byEmail.isPresent() && !byEmail.get().getDeleted()){
                throw new ValidationException("USer already exists by email");
            }
        }
    }

    private void existsById(Long id){
        if (Objects.isNull(id) || Objects.isNull(authUserDAO.findById(id)) || authUserDAO.findById(id).getDeleted()){

            throw new ValidationException("User not found by id");
        }
    }



}

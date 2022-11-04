package org.example.vo.student;

import lombok.Getter;
import lombok.Setter;
import org.example.domains.auth.AuthUser;
import org.example.vo.GenericVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:56 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Getter
@Setter
public class StudentVO extends GenericVO {

    private String name;
    private String surname;
    private AuthUser user;

    public StudentVO(Long id) {
        super(id);
    }
}

package org.example.vo.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domains.auth.AuthUser;
import org.example.vo.BaseVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:50 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateVO implements BaseVO {

    private String name;
    private String surname;
    private AuthUser user;

}

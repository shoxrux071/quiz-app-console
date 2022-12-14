package org.example.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vo.BaseVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 03:37 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCreateVO implements BaseVO {

    private String username;
    private String password;
    private String email;
}

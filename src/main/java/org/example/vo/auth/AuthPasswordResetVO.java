package org.example.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:29 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthPasswordResetVO {

    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;


}

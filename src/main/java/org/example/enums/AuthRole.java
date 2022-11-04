package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 00:49 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@AllArgsConstructor
public enum AuthRole {

    ADMIN,
    TEACHER,
    STUDENT,
    USER;

}

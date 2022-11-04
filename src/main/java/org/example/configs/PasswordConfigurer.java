package org.example.configs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 01:31 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */

@NoArgsConstructor(access = AccessLevel.MODULE)
public class PasswordConfigurer {

    public static String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
    }

    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}

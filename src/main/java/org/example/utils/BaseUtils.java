package org.example.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.configs.PasswordConfigurer;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 22:37 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseUtils {

    private static BaseUtils instance;

    public static BaseUtils getInstance(){
        if (instance==null){
            instance = new BaseUtils();
        }
        return instance;
    }


    public String encode(String rowPassword){
        return PasswordConfigurer.encode(rowPassword);
    }

    public boolean matchPassword(String rowPassowrd, String encodedPassword){
        return PasswordConfigurer.matchPassword(rowPassowrd, encodedPassword);
    }


}

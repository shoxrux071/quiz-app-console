package org.example.exception;


import java.sql.Timestamp;
import java.util.Date;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 01:04 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
public class ValidationException extends RuntimeException{

    private Timestamp timestamp;

    public ValidationException(String message){
        super(message);
        this.timestamp = new Timestamp(new Date().getTime());
    }
}

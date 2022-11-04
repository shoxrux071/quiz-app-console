package org.example.vo.http;

import lombok.Getter;
import lombok.ToString;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 01:00 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@ToString
public class Response<T> {

    private final T data;
    private Integer status;

    public Response(T data) {
        this.data = data;
    }
    public Response(T data, Integer status) {
        this.data = data;
        this.status = status;
    }

}

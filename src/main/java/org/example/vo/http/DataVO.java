package org.example.vo.http;

import lombok.Getter;
import lombok.ToString;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 00:57 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@ToString
public class DataVO<T> {
    private T body;
    private boolean success;
    private Long total;
    private AppErrorVO errorDTO;

    public DataVO(T body, Long total) {
        this(body);
        this.total = total;
    }

    public DataVO(AppErrorVO errorDTO) {
        this.errorDTO = errorDTO;
        this.success = false;
    }

    public DataVO(T body) {
        this.body = body;
        this.success = true;
    }
}

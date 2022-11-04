package org.example.vo.http;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 00:53 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AppErrorVO {
    private Timestamp timestamp;
    private String friendlyMessage;
    private String developerMessage;

    public AppErrorVO(String friendlyMessage, String developerMessage) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.friendlyMessage = friendlyMessage;
        this.developerMessage = developerMessage;
    }

    public AppErrorVO(String friendlyMessage) {
        this(friendlyMessage, friendlyMessage);
    }
}

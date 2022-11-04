package org.example.vo.answer;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.AnswerStatus;
import org.example.vo.GenericVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:37 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Setter
@Getter

public class AnswerVO extends GenericVO {

    private String body;
    private AnswerStatus status;

    public AnswerVO(Long id, String body, AnswerStatus status) {
        super(id);
        this.body = body;
        this.status = status;
    }

    @Override
    public String toString() {
        return "AnswerVO{" +
                "body='" + body + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}

package org.example.vo.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.AnswerStatus;
import org.example.vo.BaseVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:34 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnswerCreateVO implements BaseVO {

    private String body;
    private AnswerStatus status;
}

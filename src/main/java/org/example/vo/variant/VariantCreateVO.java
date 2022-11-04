package org.example.vo.variant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.QuestionStatus;
import org.example.vo.BaseVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 00:48 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
@Builder
@Getter
@Setter
public class VariantCreateVO implements BaseVO {
    private String subjectName;
    private QuestionStatus status;
    private Integer numberOfQuestions;
    private Long userId;

}

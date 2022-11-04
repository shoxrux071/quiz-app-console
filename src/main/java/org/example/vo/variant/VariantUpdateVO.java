package org.example.vo.variant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.QuestionStatus;
import org.example.vo.BaseVO;
import org.example.vo.GenericVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 00:51 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
@Builder
@Getter
@Setter
public class VariantUpdateVO implements BaseVO {
    private String subjectName;
    private QuestionStatus level;
    private Integer numberOfQuestions;
    private Long userId;
}

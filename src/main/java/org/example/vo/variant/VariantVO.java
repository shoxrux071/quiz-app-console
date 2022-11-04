package org.example.vo.variant;

import lombok.Getter;
import lombok.Setter;
import org.example.domains.auth.AuthUser;
import org.example.enums.QuestionStatus;
import org.example.vo.GenericVO;
import org.example.vo.question.QuestionVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 00:35 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
@Getter
@Setter
public class VariantVO extends GenericVO {
    private AuthUser user;
    private QuestionStatus status;
    private List<QuestionVO> questionVOS;
    private Integer numberOfRightAnswers;
    private Timestamp createdAt;

    public VariantVO(Long id, AuthUser user, QuestionStatus status, List<QuestionVO> questionVOS, Integer numberOfRightAnswers, Timestamp createdAt) {
        super(id);
        this.user = user;
        this.status = status;
        this.questionVOS = questionVOS;
        this.numberOfRightAnswers = numberOfRightAnswers;
        this.createdAt = createdAt;
    }
}

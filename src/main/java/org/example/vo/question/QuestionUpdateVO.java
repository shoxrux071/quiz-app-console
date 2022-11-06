package org.example.vo.question;

import lombok.*;
import org.example.enums.QuestionStatus;
import org.example.vo.BaseVO;
import org.example.vo.GenericVO;
import org.example.vo.answer.AnswerCreateVO;

import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:43 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@Setter
public class QuestionUpdateVO extends GenericVO {

    private String body;
    private QuestionStatus status;
    private List<AnswerCreateVO> answers;
    private String subjectName;
    private Long createdBy;


    @Builder(builderMethodName = "childBuilder")

    public QuestionUpdateVO(Long id, String body, QuestionStatus status, List<AnswerCreateVO> answers, String subjectName, Long createdBy) {
        super(id);
        this.body = body;
        this.status = status;
        this.answers = answers;
        this.subjectName = subjectName;
        this.createdBy = createdBy;
    }
}

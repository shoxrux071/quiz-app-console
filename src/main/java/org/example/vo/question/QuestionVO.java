package org.example.vo.question;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.QuestionStatus;
import org.example.vo.BaseVO;
import org.example.vo.GenericVO;
import org.example.vo.answer.AnswerVO;
import org.example.vo.subject.SubjectVO;

import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:35 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Getter
@Setter
public class QuestionVO extends GenericVO {

    private String body;
    private QuestionStatus status;
    private List<AnswerVO> answerVOList;
    private SubjectVO subjectVO;

    public QuestionVO(Long id, String body, QuestionStatus status, List<AnswerVO> answerVOList, SubjectVO subjectVO) {
        super(id);
        this.body = body;
        this.status = status;
        this.answerVOList = answerVOList;
        this.subjectVO = subjectVO;
    }
}

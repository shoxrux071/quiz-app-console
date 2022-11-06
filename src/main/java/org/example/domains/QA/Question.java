package org.example.domains.QA;

import jakarta.persistence.*;
import lombok.*;
import org.example.domains.Auditable;
import org.example.domains.subject.Subject;
import org.example.enums.QuestionStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 01:59 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Entity
@Table(name = "questions")
@NoArgsConstructor
@Getter
@Setter
@ToString


public class Question extends Auditable {

    private String body;

    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Answer> answers;


    @OneToOne(cascade = CascadeType.ALL, targetEntity = Subject.class)
    @JoinColumn(name = "subject_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Subject subject;

    @Builder(builderMethodName = "childBuilder")
    public Question(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, Boolean deleted, String body, QuestionStatus status, List<Answer> answers, Subject subject) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.body = body;
        this.status = status;
        this.answers = answers;
        this.subject = subject;
    }
}

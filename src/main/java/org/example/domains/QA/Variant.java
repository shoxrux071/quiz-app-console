package org.example.domains.QA;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.domains.Auditable;
import org.example.domains.auth.AuthUser;
import org.example.enums.QuestionStatus;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 02:48 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "variants", schema = "test")
@Getter
@Setter

public class Variant extends Auditable {

    @OneToOne(targetEntity = AuthUser.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AuthUser user;

    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @Column(columnDefinition = "smallint default 0", nullable = false)
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean completed;

    @ManyToMany(targetEntity = Question.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "variant_question",

            joinColumns =@JoinColumn(name = "varian_id"),
            inverseJoinColumns =@JoinColumn(name = "question_id"),
            schema ="test"
    )
    private List<Question> questionList;

    @Column(name = "result")
    private Integer numberOfRightAnswers;

    @Column(name = "questions")
    private Integer numberOfQuestions;


    public Variant(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, Boolean deleted, AuthUser user, QuestionStatus status, Boolean completed, List<Question> questionList, Integer numberOfRightAnswers, Integer numberOfQuestions) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.user = user;
        this.status = status;
        this.completed = false;
        this.questionList = questionList;
        this.numberOfRightAnswers = numberOfRightAnswers;
        this.numberOfQuestions = numberOfQuestions;
    }
}

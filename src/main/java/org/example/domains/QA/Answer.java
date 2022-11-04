package org.example.domains.QA;

import jakarta.persistence.*;
import lombok.*;
import org.example.domains.Auditable;
import org.example.enums.AnswerStatus;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 02:02 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Entity
@Table(name = "answers", schema = "question")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class Answer extends Auditable {

    private String body;

    @Enumerated(EnumType.STRING)
    private AnswerStatus status;


    public Answer(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, Boolean deleted, String body, AnswerStatus status) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.body = body;
        this.status = status;
    }
}

package org.example.domains.subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.domains.Auditable;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 01:30 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Entity
@Table(schema = "subject", name = "subjects")
@NoArgsConstructor
@Getter
@ToString
public class Subject extends Auditable {

    @Column(nullable = false, unique = true)
    private String title;

    public Subject(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, Boolean deleted, String title) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {

        if (this==obj)
            return true;
        if (obj==null || getClass() != obj.getClass())
            return false;

        Subject that = (Subject) obj;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title);

    }
}

package org.example.domains;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 00:13 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass

public class Auditable implements BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    Timestamp createdAt;

    @Column(columnDefinition = "bigint default -1", nullable = false)
    private Long createdBy;

    @Column
    private Timestamp updatedAt;

    @Column
    private Long updatedBy;

    @Column(columnDefinition = "smallint default 0", nullable = false)
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean deleted;

    public Auditable(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, Boolean deleted) {
        this.id = id;
        this.createdAt = createdAt;
        if (Objects.isNull(createdBy))
            createdBy=-1L;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        if (Objects.isNull(deleted))
            deleted=false;
        this.deleted = deleted;
    }
}

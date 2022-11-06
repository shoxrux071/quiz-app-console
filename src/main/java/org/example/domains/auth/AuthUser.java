package org.example.domains.auth;

import jakarta.persistence.*;
import lombok.*;
import org.example.domains.Auditable;
import org.example.enums.AuthRole;
import org.example.enums.Status;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 00:42 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
@AllArgsConstructor
public class AuthUser extends Auditable {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private AuthRole role;

    @Convert(converter = Status.StatusConvertor.class)
    private Status status;

    @Builder(builderClassName = "childBuilder")
    public AuthUser(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, Boolean deleted, String username, String password, String email, AuthRole role, Status status) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.username = username;
        this.password = password;
        this.email = email;

        if (Objects.isNull(role))
            role = AuthRole.USER;
        this.role = role;

        if (Objects.isNull(status))
            status = Status.ACTIVE;
        this.status = status;
    }
}





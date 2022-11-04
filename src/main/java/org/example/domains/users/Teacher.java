package org.example.domains.users;

import jakarta.persistence.*;
import lombok.*;
import org.example.domains.auth.AuthUser;
import org.example.domains.subject.Subject;

import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 01:25 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Entity
@Table(name = "teachers", schema = "auth")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToMany(targetEntity = Subject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_subject",
            joinColumns =@JoinColumn(name = "teacher_id"),
            inverseJoinColumns =@JoinColumn(name = "subject_id"),
            schema ="subject"
    )
    private List<Subject> subjectList;

    @OneToOne(targetEntity = AuthUser.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user;
}

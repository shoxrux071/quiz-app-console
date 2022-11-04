package org.example.vo.subject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.vo.BaseVO;

import java.sql.Timestamp;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:46 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Builder
@Getter
@Setter
@ToString
public class SubjectCreateVO implements BaseVO {
    private String title;
    private Timestamp createdAt;
    private Long createdBy;
}


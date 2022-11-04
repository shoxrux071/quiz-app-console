package org.example.vo.subject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.vo.GenericVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:48 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Getter
@Setter
@ToString
public class SubjectUpdateVO extends GenericVO {

    private String title;

    public SubjectUpdateVO(Long id, String title) {
        super(id);
        this.title = title;
    }
}

package org.example.vo.subject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.vo.GenericVO;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:40 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */
@Getter
@Setter
@ToString
public class SubjectVO extends GenericVO {

    private String title;


    @Builder(builderMethodName = "childBuilder")
    public SubjectVO(Long id, String title) {
        super(id);
        this.title = title;
    }
}

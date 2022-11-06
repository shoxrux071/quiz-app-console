package org.example.vo.teacher;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.vo.GenericVO;
import org.example.vo.subject.SubjectVO;

import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 00:30 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@Setter
public class TeacherUpdateVO extends GenericVO {

    private String name;
    private String surname;
    private List<SubjectVO> subjectVOList;


    @Builder(builderMethodName = "childBuilder")
    public TeacherUpdateVO(Long id, String name, String surname, List<SubjectVO> subjectVOList) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.subjectVOList = subjectVOList;
    }
}

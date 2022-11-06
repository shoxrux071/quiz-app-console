package org.example.vo.teacher;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.vo.GenericVO;
import org.example.vo.subject.SubjectVO;

import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 04/11/22 23:59 (Friday)
 * quiz-app-console/IntelliJ IDEA
 */

@Getter
@Setter
@ToString
public class TeacherVO extends GenericVO {

    private String name;
    private String surname;
    private List<SubjectVO> subjectVOList;

    @Builder
    public TeacherVO(Long id, String name, String surname, List<SubjectVO> subjectVOList) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.subjectVOList = subjectVOList;
    }
}

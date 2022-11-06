package org.example.service.subject;

import lombok.NonNull;
import org.example.configs.ApplicationContextHolder;
import org.example.dao.AbstractDAO;
import org.example.dao.subject.SubjectDAO;
import org.example.domains.subject.Subject;
import org.example.exception.ValidationException;
import org.example.service.GenericCRUDService;
import org.example.utils.BaseUtils;
import org.example.utils.validator.subject.SubjectValidator;
import org.example.vo.http.AppErrorVO;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;
import org.example.vo.subject.SubjectCreateVO;
import org.example.vo.subject.SubjectUpdateVO;
import org.example.vo.subject.SubjectVO;

import java.util.List;
import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 00:51 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class SubjectService extends AbstractDAO<SubjectDAO> implements GenericCRUDService<
        SubjectVO,
        SubjectCreateVO,
        SubjectUpdateVO,
        Long> {


    private final SubjectValidator validator = ApplicationContextHolder.getBean(SubjectValidator.class);
    private static SubjectService instance;

    private SubjectService() {
        super(
                ApplicationContextHolder.getBean(SubjectDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class));
    }

    public static SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }
        return instance;
    }

    @Override
    public Response<DataVO<Long>> create(@NonNull SubjectCreateVO vo) {
        try {
            validator.validOnCreate(vo);
            Subject question = Subject.childBuilder()
                    .title(vo.getTitle())
                    .createdBy(vo.getCreatedBy())
                    .build();
            Subject save = dao.save(question);

            return new Response<>(new DataVO<>(save.getId()), 200);
        } catch (ValidationException e) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public Response<DataVO<Void>> update(@NonNull SubjectUpdateVO vo) {
        return null;
    }

    @Override
    public Response<DataVO<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<DataVO<SubjectVO>> get(@NonNull Long aLong) {
        return null;
    }

    public Response<DataVO<SubjectVO>> get(@NonNull String subjectName) {
        Subject subjectEntity = dao.findByName(subjectName);
        if (Objects.isNull(subjectEntity)) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("No subject found").build()), 500);
        }
        SubjectVO subjectVO = SubjectVO.childBuilder()
                .id(subjectEntity.getId())
                .title(subjectEntity.getTitle())
                .build();


        return new Response<>(new DataVO<>(subjectVO), 200);
    }

    @Override
    public Response<DataVO<List<SubjectVO>>> getAll() {
        return null;
    }
}

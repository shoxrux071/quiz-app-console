package org.example.service.variant;

import lombok.NonNull;
import org.example.configs.ApplicationContextHolder;
import org.example.dao.AbstractDAO;
import org.example.dao.auth.AuthUserDAO;
import org.example.dao.qa.QuestionDAO;
import org.example.dao.subject.SubjectDAO;
import org.example.dao.variant.VariantDAO;
import org.example.domains.QA.Answer;
import org.example.domains.QA.Question;
import org.example.domains.QA.Variant;
import org.example.domains.auth.AuthUser;
import org.example.domains.subject.Subject;
import org.example.service.GenericCRUDService;
import org.example.utils.BaseUtils;
import org.example.utils.validator.variant.VariantValidator;
import org.example.vo.answer.AnswerVO;
import org.example.vo.http.AppErrorVO;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;
import org.example.vo.question.QuestionVO;
import org.example.vo.subject.SubjectVO;
import org.example.vo.variant.VariantCreateVO;
import org.example.vo.variant.VariantUpdateVO;
import org.example.vo.variant.VariantVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 01:18 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class VariantService extends AbstractDAO<VariantDAO> implements GenericCRUDService<
        VariantVO,
        VariantCreateVO,
        VariantUpdateVO,
        Long> {

    private static VariantService instance;
    VariantValidator validator = ApplicationContextHolder.getBean(VariantValidator.class);
    private static AuthUserDAO authUserDAO = ApplicationContextHolder.getBean(AuthUserDAO.class);
    private static QuestionDAO questionDAO = ApplicationContextHolder.getBean(QuestionDAO.class);
    private static SubjectDAO subjectDAO = ApplicationContextHolder.getBean(SubjectDAO.class);

    private VariantService() {
        super(
                ApplicationContextHolder.getBean(VariantDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class));
    }

    public static VariantService getInstance() {
        if (instance == null) {
            instance = new VariantService();
        }
        return instance;
    }

    @Override
    public Response<DataVO<Long>> create(@NonNull VariantCreateVO vo) {
        return null;
    }

    @Override
    public Response<DataVO<Void>> update(@NonNull VariantUpdateVO vo) {
        return null;
    }

    @Override
    public Response<DataVO<Void>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<DataVO<VariantVO>> get(@NonNull Long variantId) {
        Variant variantEntity = dao.findById(variantId);
        if (Objects.isNull(variantEntity))
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("Variant not found by id")
                    .build()), 400);

        VariantVO variantVO = map(variantEntity);

        return new Response<>(new DataVO<>(variantVO), 200);
    }

    @Override
    public Response<DataVO<List<VariantVO>>> getAll() {
        return null;
    }

    public void UpdateVariantEntity(Variant variant) {
        dao.update(variant);
    }

    public Response<DataVO<Variant>> createAndGet(@NonNull VariantCreateVO vo) {
        try {
            validator.validOnCreate(vo);
            Subject subjectEntity = subjectDAO.findByName(vo.getSubjectName());
            AuthUser authUser = authUserDAO.findById(vo.getUserId());

            Set<Question> questionEntitiesList = questionDAO.getFixedNumber(subjectEntity.getId(), vo.getStatus(), vo.getNumberOfQuestions());
            Variant variantEntity = Variant.childBuilder()
                    .questions((List<Question>) questionEntitiesList)
                    .user(authUser)
                    .numberOfRightAnswers(0)
                    .numberOfQuestions(vo.getNumberOfQuestions())
                    .build();

            Variant save = dao.save(variantEntity);


            return new Response<>(new DataVO<>(save), 200);
        } catch (Exception e) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage(e.getMessage())
                    .build()), 400);
        }
    }

    public Response<DataVO<List<VariantVO>>> getAllByStudentId(Long studentId) {
        List<Variant> all = dao.findAllVariantsByUserId(studentId);

        if (all.isEmpty()) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("No variants found").build()), 404);
        }

        List<VariantVO> response = new ArrayList<>();
        for (Variant variantEntity : all) {
            VariantVO variantVO = map(variantEntity);
            response.add(variantVO);
        }
        return new Response<>(new DataVO<>(response), 200);
    }


    private static VariantVO map(Variant variantEntity) {
        VariantVO variantVO = VariantVO.childBuilder()
                .id(variantEntity.getId())
                .createdAt(variantEntity.getCreatedAt())
                .status(variantEntity.getStatus())
                .numberOfRightAnswers(variantEntity.getNumberOfRightAnswers()).build();

        List<QuestionVO> questionVOList = new ArrayList<>();

        for (Question questionEntity : variantEntity.getQuestionList()) {
            Subject subject = questionEntity.getSubject();

            SubjectVO subjectVO = SubjectVO.childBuilder()
                    .id(subject.getId())
                    .title(subject.getTitle())
                    .build();

            QuestionVO questionVO = QuestionVO.childBuilder()
                    .id(questionEntity.getId())
                    .body(questionEntity.getBody())
                    .status(questionEntity.getStatus())
                    .subjectVO(subjectVO)
                    .build();

            List<AnswerVO> answerVOList = new ArrayList<>();
            for (Answer answer : questionEntity.getAnswers()) {
                AnswerVO answerVO = AnswerVO.childBuilder()
                        .body(answer.getBody())
                        .id(answer.getId())
                        .status(answer.getStatus())
                        .build();
                answerVOList.add(answerVO);
            }
            questionVO.setAnswerVOList(answerVOList);
            questionVOList.add(questionVO);
        }
        variantVO.setQuestionVOS(questionVOList);
        return variantVO;
    }

}

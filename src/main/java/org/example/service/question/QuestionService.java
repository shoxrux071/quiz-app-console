package org.example.service.question;

import lombok.NonNull;
import org.example.configs.ApplicationContextHolder;
import org.example.dao.AbstractDAO;
import org.example.dao.qa.QuestionDAO;
import org.example.dao.subject.SubjectDAO;
import org.example.domains.QA.Answer;
import org.example.domains.QA.Question;
import org.example.domains.subject.Subject;
import org.example.enums.QuestionStatus;
import org.example.exception.ValidationException;
import org.example.service.GenericCRUDService;
import org.example.service.subject.SubjectService;
import org.example.utils.BaseUtils;
import org.example.utils.validator.question.QuestionValidator;
import org.example.vo.answer.AnswerCreateVO;
import org.example.vo.answer.AnswerVO;
import org.example.vo.http.AppErrorVO;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;
import org.example.vo.question.QuestionCreateVO;
import org.example.vo.question.QuestionUpdateVO;
import org.example.vo.question.QuestionVO;
import org.example.vo.subject.SubjectVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 00:47 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class QuestionService extends AbstractDAO<QuestionDAO> implements GenericCRUDService<
        QuestionVO,
        QuestionCreateVO,
        QuestionUpdateVO,
        Long> {

    private static QuestionService instance;

    private static SubjectDAO subjectDAO = ApplicationContextHolder.getBean(SubjectDAO.class);
    private static final SubjectService subjectService = ApplicationContextHolder.getBean(SubjectService.class);
    private final QuestionValidator validator = ApplicationContextHolder.getBean(QuestionValidator.class);


    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }
        return instance;
    }

    public QuestionService() {

        super(ApplicationContextHolder.getBean(QuestionDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class));
    }

    @Override
    public Response<DataVO<Long>> create(@NonNull QuestionCreateVO vo) {
        try {
            validator.validOnCreate(vo);
            List<Answer> answerList = new ArrayList<>();
            for (AnswerCreateVO answer : vo.getAnswerCreateVOList()) {
                Answer build = Answer.childBuilder()
                        .body(answer.getBody())
                        .status(answer.getStatus())
                        .build();
                answerList.add(build);
            }
            Subject subjectEntity = subjectDAO.findByName(vo.getSubjectName());
            if (Objects.isNull(subjectEntity))
                return new Response<>(new DataVO<>(AppErrorVO.builder()
                        .friendlyMessage("subject not found by name '%s'".formatted(vo.getSubjectName()))
                        .build()), 500);
            Question question = Question.childBuilder()
                    .body(vo.getBody())
                    .answers(answerList)
                    .status(vo.getStatus())
                    .createdBy(vo.getCreatedBy())
                    .subject(subjectEntity)
                    .build();

            Question save = dao.save(question);
            return new Response<>(new DataVO<>(save.getId()), 200);
        } catch (ValidationException e) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage(e.getMessage())
                    .build()), 400);
        }
    }

    @Override
    public Response<DataVO<Void>> update(@NonNull QuestionUpdateVO vo) {
        try {
            Optional<Question> findById = Optional.ofNullable(dao.findById(vo.id));
            if (findById.isEmpty())
                return new Response<>(new DataVO<>(AppErrorVO.builder()
                        .friendlyMessage("Question not found")
                        .build()), 500);

            Question questionEntity = findById.get();

            if (Objects.nonNull(vo.getId())) {
                questionEntity.setBody(vo.getBody());
            }

            if (Objects.nonNull(vo.getStatus())) {
                questionEntity.setStatus(vo.getStatus());
            }


            dao.update(questionEntity);

            return new Response<>(new DataVO<>(null), 200);
        } catch (Exception e) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("Oops something went wrong")
                    .build()), 400);
        }
    }

    @Override
    public Response<DataVO<Void>> delete(@NonNull Long questionId) {
        try {
            Question questionEntity = dao.findById(questionId);
            if (Objects.isNull(questionEntity)) {
                return new Response<>(new DataVO<>(AppErrorVO.builder()
                        .friendlyMessage("Question not found by id")
                        .build()), 404);
            }
            questionEntity.setDeleted(true);
            dao.update(questionEntity);
            return new Response<>(new DataVO<>(null), 200);
        } catch (Exception e) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("Oops something went wrong")
                    .build()), 500);
        }
    }

    @Override
    public Response<DataVO<QuestionVO>> get(@NonNull Long aLong) {
        Question questionEntity = dao.findById(aLong);
        if (Objects.isNull(questionEntity))
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("Question not found")
                    .build()), 500);
        QuestionVO questionVO = QuestionVO.childBuilder()
                .id(questionEntity.getId())
                .body(questionEntity.getBody())
                .status(questionEntity.getStatus())
                .build();
        return new Response<>(new DataVO<>(questionVO), 200);
    }

    @Override
    public Response<DataVO<List<QuestionVO>>> getAll() {
        List<Question> all = dao.findAll();
        if (all.isEmpty()) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("No question find")
                    .build()), 500);
        }
        List<QuestionVO> response = new ArrayList<>();
        for (Question questionEntity : all) {
            Subject subject = questionEntity.getSubject();

            SubjectVO subjectVO = SubjectVO.childBuilder()
                    .id(subject.getId())
                    .title(subject.getTitle())
                    .build();

            QuestionVO questionVO = QuestionVO.childBuilder()
                    .body(questionEntity.getBody())
                    .id(questionEntity.getId())
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
            response.add(questionVO);
        }

        return new Response<>(new DataVO<>(response), 200);

    }


    public Response<DataVO<List<QuestionVO>>> getAll(String name, QuestionStatus level, Integer numberOfQuestion) {
        try {
            Response<DataVO<SubjectVO>> subjectResponse = subjectService.get(name);
            if (subjectResponse.getStatus() != 200) {
                return new Response<>(new DataVO<>(AppErrorVO.builder()
                        .friendlyMessage("Subject not found")
                        .build()), 400);
            }

            SubjectVO subjectVO = subjectResponse.getData().getBody();


            Long subjectId = subjectVO.getId();
            List<Question> resultList;
            if (Objects.isNull(level) && Objects.isNull(numberOfQuestion)) {
                resultList = dao.findAllBySubjectId(subjectId);
            } else if (Objects.isNull(numberOfQuestion)) {
                resultList = dao.findAllBySubjectIdAndLevel(subjectId, level);
            } else {
                resultList = dao.findAllBySubjectIdAndLevel(subjectId, level);
            }

            if (resultList.isEmpty()) {
                return new Response<>(new DataVO<>(AppErrorVO.builder()
                        .friendlyMessage("No result found").build()), 500);
            }

            List<QuestionVO> response = new ArrayList<>();
            for (Question question : resultList) {
                QuestionVO questionVO = QuestionVO.childBuilder()
                        .id(question.getId())
                        .body(question.getBody())
                        .status(question.getStatus())
                        .subjectVO(subjectVO).build();
                List<AnswerVO> answerVOList = new ArrayList<>();
                for (Answer answer : question.getAnswers()) {
                    AnswerVO answerVO = AnswerVO.childBuilder()
                            .body(answer.getBody())
                            .id(answer.getId())
                            .status(answer.getStatus())
                            .build();
                    answerVOList.add(answerVO);
                }

                questionVO.setAnswerVOList(answerVOList);
                response.add(questionVO);
            }
            return new Response<>(new DataVO<>(response), 200);
        } catch (Exception e) {
            return new Response<>(new DataVO<>(AppErrorVO.builder()
                    .friendlyMessage("oops something went wrong")
                    .developerMessage(e.getMessage())
                    .build()), 500);
        }
    }

}

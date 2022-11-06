package org.example.ui;

import org.example.configs.ApplicationContextHolder;
import org.example.domains.QA.Answer;
import org.example.domains.QA.Question;
import org.example.domains.QA.Variant;
import org.example.enums.AnswerStatus;
import org.example.enums.QuestionStatus;
import org.example.service.auth.AuthUserService;
import org.example.service.subject.SubjectService;
import org.example.service.variant.VariantService;
import org.example.vo.answer.AnswerVO;
import org.example.vo.auth.AuthPasswordResetVO;
import org.example.vo.auth.Session;
import org.example.vo.http.DataVO;
import org.example.vo.http.Response;
import org.example.vo.question.QuestionVO;
import org.example.vo.subject.SubjectVO;
import org.example.vo.variant.VariantCreateVO;
import org.example.vo.variant.VariantVO;
import uz.jl.BaseUtils;
import uz.jl.Colors;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 07/11/22 02:29 (Monday)
 * quiz-app-console/IntelliJ IDEA
 */
public class StudentUI {

    private static final StudentUI studentUI = new StudentUI();
    private static final AuthUserService authUserService = ApplicationContextHolder.getBean(AuthUserService.class);
    private static final SubjectService subjectService = ApplicationContextHolder.getBean(SubjectService.class);
    private static final VariantService variantService = ApplicationContextHolder.getBean(VariantService.class);

    public static void main(String[] args) {

        if (Objects.nonNull(Session.sessionUser)) {
            System.out.println("=================Student Page=================");
            BaseUtils.println("Do test -> 1");
            BaseUtils.println("Show history ->2");
            BaseUtils.println("Change auth info -> 3");
            BaseUtils.println("Delete account-> 4");
            BaseUtils.println("logout -> l");
            BaseUtils.println("Quit -> q");

            String choice = BaseUtils.readText("choice ? ");
            switch (choice) {
                case "1" -> studentUI.doTest();
                case "2" -> studentUI.showHistory();
                case "3" -> studentUI.updateAuthInfo();
                case "4" -> studentUI.deletedStudent();
                case "l" -> Session.setSessionUser(null);
                case "q" -> {
                    BaseUtils.println("Bye");
                    System.exit(0);
                }
                default -> BaseUtils.println("Invalid choice");
            }
            main(args);
        }
    }

    private void updateAuthInfo() {

        BaseUtils.println("1.Change username");
        BaseUtils.println("2.Change password");
        BaseUtils.println("default go back");
        String option = BaseUtils.readText("Choose option: ");
        switch (option) {
            case "1" -> changeUserName();
            case "2" -> changePassword();
            default -> BaseUtils.println("Wrong option", Colors.RED);
        }
    }

    public static void changePassword() {
        AuthPasswordResetVO resetVO = AuthPasswordResetVO.builder()
                .oldPassword(BaseUtils.readText("Insert old password: "))
                .newPassword(BaseUtils.readText("Insert new password: "))
                .confirmNewPassword(BaseUtils.readText("confirm new password: "))
                .build();

        Response<DataVO<Void>> response = authUserService.changePassword(resetVO);
        if (response.getStatus() != 200)
            print_response(response);
        else BaseUtils.println("Done", Colors.GREEN);

    }

    public static void changeUserName() {
        String newUsername = BaseUtils.readText("Insert new username: ");
        Response<DataVO<Void>> response = authUserService.changeUsername(newUsername);
        if (response.getStatus() != 200)
            print_response(response);
        else BaseUtils.println("Done", Colors.GREEN);
    }


    private void showHistory() {

        Response<DataVO<List<VariantVO>>> allVariants = variantService.getAllByStudentId(Session.sessionUser.getId());
        if (allVariants.getStatus() != 200) {
            print_response(allVariants);
            return;
        } else {
            for (VariantVO variantVO : allVariants.getData().getBody()) {
                BaseUtils.println("Variant id: " + variantVO.id + ", time: " + variantVO.getCreatedAt() + ", status: " + variantVO.getStatus() + ", Number of correct answers: " + variantVO.getNumberOfRightAnswers());
            }
        }

        BaseUtils.println("show detailed -1");
        BaseUtils.println("go back  -2");

        String choice = BaseUtils.readText("choice ? ");
        switch (choice) {

            case "1" -> BaseUtils.println("");

            case "2" -> {
                return;
            }
            default -> {
                BaseUtils.println("Invalid option");
                System.exit(0);
            }
        }
        Long variantId = Long.valueOf(BaseUtils.readText("Enter variantId: "));
        Response<DataVO<VariantVO>> voResponse = variantService.get(variantId);
        if (voResponse.getStatus() != 200) {
            print_response(voResponse);
        } else {
            for (QuestionVO question : voResponse.getData().getBody().getQuestionVOS()) {
                BaseUtils.println("");
                BaseUtils.println("Question id: " + question.id + ", Body: " + question.getBody() + ", status: " + question.getStatus() + ", subject: " + question.getSubjectVO().getTitle());
                for (AnswerVO answer : question.getAnswerVOList()) {
                    BaseUtils.println("Answer: " + answer.getBody() + ", status: " + answer.getStatus());
                }
            }
        }
    }

    private void doTest() {
        try {
            Variant variant = createVariant();
            BaseUtils.println("\n\nStart 1", Colors.YELLOW);
            BaseUtils.println("cancel any key", Colors.YELLOW);
            String choice = BaseUtils.readText("choice ? ");
            switch (choice) {
                case "1" -> BaseUtils.println("Test started");
                default -> {
                    BaseUtils.println("Test canceled");
                    return;
                }
            }
            LocalTime startTime = LocalTime.now();
            int numberOfQuestion = variant.getQuestionList().size();
            LocalTime endTime = startTime.plusMinutes(numberOfQuestion);
            BaseUtils.println("Start time " + startTime);
            BaseUtils.println("End time " + endTime);
            BaseUtils.println("You have %s minutes\n".formatted(numberOfQuestion));
            Integer numberOfRightAnswers = 0;
            for (Question question : variant.getQuestionList()) {

                if (LocalTime.now().isAfter(endTime))
                    break;
                BaseUtils.println("Savol: "+question.getBody(), Colors.PURPLE);

                Answer rightAnswer = null;
                List<Answer> answers = question.getAnswers();
                for (Answer answer : answers) {
                    System.out.println("Javob: "+answer.getBody());
                    if (answer.getStatus().equals(AnswerStatus.RIGHT))
                        rightAnswer = answer;
                }

                String studentAnswer = BaseUtils.readText("Your answer : ");
                if (studentAnswer.equalsIgnoreCase(rightAnswer.getBody()))
                    numberOfRightAnswers++;

            }

            BaseUtils.println("Your result: " + numberOfRightAnswers);


            variant.setNumberOfRightAnswers(numberOfRightAnswers);
            variant.setCompleted(true);
            variantService.UpdateVariantEntity(variant);

        } catch (Exception ignored) {
        }

    }

    public static void print_response(Response response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

    private Variant createVariant() {
        Response<DataVO<List<SubjectVO>>> subjectListResponse = subjectService.getAll();
        if (subjectListResponse.getStatus() != 200) {
            print_response(subjectListResponse);
            throw new RuntimeException();
        }
        BaseUtils.println(subjectListResponse.getData().getBody());
        String subjectName = BaseUtils.readText("Subject name? ");
        BaseUtils.println("1.EASY \n2.MEDIUM \n3.HARD", Colors.PURPLE);
        String choice = BaseUtils.readText("choice ? ");
        QuestionStatus level = null;
        switch (choice) {
            case "1" -> level = QuestionStatus.EASY;
            case "2" -> level = QuestionStatus.MEDIUM;
            case "3" -> level = QuestionStatus.HARD;
            default -> {
                BaseUtils.println("Invalid choice", Colors.RED);
                throw new RuntimeException();
            }
        }
        Integer numberOfQuestions;
        try {
            numberOfQuestions = Integer.valueOf(BaseUtils.readText("number of question ? "));
        } catch (NumberFormatException e) {
            BaseUtils.println("Invalid input: Number should be input", Colors.RED);
            throw new RuntimeException();

        }


        VariantCreateVO variantCreateVO = VariantCreateVO.builder()
                .status(level)
                .subjectName(subjectName)
                .numberOfQuestions(numberOfQuestions)
                .userId(Session.sessionUser.getId())
                .build();

        Response<DataVO<Variant>> variantResponse = variantService.createAndGet(variantCreateVO);
        if (variantResponse.getStatus() != 200) {
            print_response(variantResponse);
            throw new RuntimeException();
        }
        return variantResponse.getData().getBody();

    }

    private void deletedStudent() {
        Response<DataVO<Void>> response = authUserService.deleteUser(
                Session.sessionUser.getId(),
                BaseUtils.readText("password ? ")
        );
        if (response.getStatus() != 200)
            print_response(response);
    }

}

package org.example.dao.qa;

import org.example.dao.GenericDAO;
import org.example.domains.QA.Question;
import org.example.enums.QuestionStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 23:34 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
public class QuestionDAO extends GenericDAO<Question, Long> {

    private static QuestionDAO instance;

    private static Random random = new Random();

    public static QuestionDAO getInstance() {

        if (Objects.isNull(instance)) {
            instance = new QuestionDAO();
        }
        return instance;
    }


    public List<Question> findAllBySubjectId(Long subjectId) {
        Session session = getSession();
        session.beginTransaction();
        Query<Question> query = session.createQuery("select t from Question t where t.subject.id=:id and " +
                "t.deleted=false and t.subject.deleted=false ", Question.class);
        query.setParameter("id", subjectId);
        List<Question> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    public List<Question> findAllBySubjectIdAndLevel(Long subjectId, QuestionStatus status) {

        Session session = getSession();
        session.beginTransaction();
        Query<Question> query = session.createQuery("select t from Question t where t.subject.id=:subjectId " +
                "and t.deleted=false and t.status=:status", Question.class);
        query.setParameter("subjectId", subjectId);
        query.setParameter("status", status);
        List<Question> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    public Set<Question> getFixedNumber(Long subjectId, QuestionStatus status, Integer limit) {
//        Session session = getSession();
//        session.beginTransaction();
//        Query<Question> query = session.createQuery("select t from Question t where t.subject.id=:subjectId " +
//                "and t.deleted=false and t.status=:status  ", Question.class);

        List<Question> all = findAllBySubjectIdAndLevel(subjectId, status);

        Set<Question> questionSet = new HashSet<>();

        while (questionSet.size() != limit) {
            Question question = all.get(random.nextInt(all.size()));
            questionSet.add(question);
        }
        return questionSet;
    }
}

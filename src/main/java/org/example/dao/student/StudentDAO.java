package org.example.dao.student;

import org.example.dao.GenericDAO;
import org.example.domains.QA.Question;
import org.example.domains.users.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 00:44 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */
public class StudentDAO extends GenericDAO<Student, Long> {

    private static StudentDAO instance;

    public static StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    public List<Student> findAllStudent(){
        Session session = getSession();
        session.beginTransaction();
        Query<Student> query = session.createQuery("select t from Student t", Student.class);
        List<Student> resultList = query.getResultList();
        return resultList;
    }


}

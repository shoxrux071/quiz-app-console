package org.example.dao.subject;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dao.GenericDAO;
import org.example.domains.subject.Subject;
import org.hibernate.Session;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 06/11/22 01:22 (Sunday)
 * quiz-app-console/IntelliJ IDEA
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectDAO extends GenericDAO<Subject, Long> {

    private static SubjectDAO instance;

    public static SubjectDAO getInstance() {
        if (instance == null) {
            instance = new SubjectDAO();
        }
        return instance;
    }

    public Subject findByName(String name) {
        Session session = getSession();
        session.beginTransaction();

        Subject result = session.createQuery("select t from Subject t where  lower(t.title) = lower(:name) and t.deleted=false ", Subject.class)
                .setParameter("name", name)
                .getSingleResultOrNull();

        session.close();
        return result;
    }

}

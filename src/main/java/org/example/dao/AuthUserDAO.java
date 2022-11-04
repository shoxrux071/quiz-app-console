package org.example.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.configs.HibernateConfigurer;
import org.example.domains.auth.AuthUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 01:45 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class AuthUserDAO extends GenericDAO<AuthUser, Long>{


    private static AuthUserDAO instance;

    public static AuthUserDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthUserDAO();
        }
        return instance;
    }

    public Optional<AuthUser> findByUserName(String username){

        Session session = getSession();
        session.beginTransaction();
        Query<AuthUser> query =
                session.createQuery("select t from AuthUser t where lower(t.username) " +
                        "= lower(:username) and t.deleted=false ", AuthUser.class);
        query.setParameter("username", username);
        Optional<AuthUser> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }

    public static void main(String[] args) {

    }
}

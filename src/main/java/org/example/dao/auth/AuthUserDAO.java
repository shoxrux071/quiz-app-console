package org.example.dao.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dao.GenericDAO;
import org.example.domains.auth.AuthUser;
import org.example.enums.AuthRole;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author "Berdimurodov Shoxrux"
 * @since 05/11/22 01:45 (Saturday)
 * quiz-app-console/IntelliJ IDEA
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class AuthUserDAO extends GenericDAO<AuthUser, Long> {


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

    public Optional<AuthUser> findByEmail(String email){
        Session session = getSession();
        session.beginTransaction();
        Query<AuthUser> query = session.createQuery("select t from AuthUser t where lower(t.email)= lower(:email) and t.deleted=false", AuthUser.class);
        query.setParameter("email", email);
        Optional<AuthUser> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }

    public List<AuthUser> findAll(AuthRole role){
        Session session = getSession();
        session.beginTransaction();
        Query<AuthUser> query = session.createQuery("select t from AuthUser t where t.role=:role " +
                "and t.deleted=false ", AuthUser.class);
        query.setParameter("role", role);
        List<AuthUser> userList = query.getResultList();
        session.close();
        return userList;
    }
}

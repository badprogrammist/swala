/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.repository.user;

import io.swala.domain.user.User;
import io.swala.domain.user.UserRepository;
import io.swala.repository.AbstractEntityRepositoryJPA;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository
public class UserRepositoryJPA extends AbstractEntityRepositoryJPA<User> implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryJPA() {
        super(User.class);
    }

    
    @Override
    public User findUserByLogin(String login) {
        List<User> user;
        user = entityManager.createNamedQuery("User.findByLogin", User.class)
                .setParameter("login", login)
                .getResultList();
        if (user != null && user.size() == 1) {
            return user.iterator().next();
        } else {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

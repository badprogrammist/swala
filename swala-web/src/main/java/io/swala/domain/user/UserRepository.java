/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.domain.user;

import io.swala.domain.EntityRepository;

/**
 *
 * @author Ильдар
 */
public interface UserRepository extends EntityRepository<User> {
    public User findUserByLogin(String login);
}

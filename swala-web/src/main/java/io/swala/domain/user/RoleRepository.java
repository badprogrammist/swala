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
public interface RoleRepository extends EntityRepository<Role> {
    public Role getByName(String name);
}

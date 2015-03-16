/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.repository.user;

import io.swala.domain.user.Role;
import io.swala.domain.user.RoleRepository;
import io.swala.repository.AbstractEntityRepositoryJPA;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository
public class RoleRepositoryJPA extends AbstractEntityRepositoryJPA<Role> implements RoleRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryJPA() {
        super(Role.class);
    }
    
    @Override
    public Role getByName(String name) {
        List<Role> roles = entityManager.createNamedQuery("Role.findByName",Role.class)
                .setParameter("name", name)
                .getResultList();
        if (roles == null || roles.isEmpty()) {
            return null;
        } else {
            return roles.iterator().next();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
        

}

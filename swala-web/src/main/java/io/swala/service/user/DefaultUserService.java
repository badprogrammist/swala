/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.service.user;

import io.swala.domain.EntityRepository;
import io.swala.domain.user.Role;
import io.swala.domain.user.RoleRepository;
import io.swala.domain.user.User;
import io.swala.domain.user.UserCredential;
import io.swala.domain.user.UserRepository;
import io.swala.domain.user.UserRoleRelation;
import io.swala.service.AbstractService;
import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ильдар
 */
@Service(value = "userService")
@Transactional
public class DefaultUserService extends AbstractService<User> implements UserService {
    
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private RoleRepository roleRepository;
    
    @Override
    public Long registerNewUser(UserCredential credential, Role role) {
        User user = new User(credential);
        UserRoleRelation roleRelation = new UserRoleRelation(user, role);
        user.getRoles().add(roleRelation);
        userRepository.store(user);
        return user.getId();
    }
    
    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getByName(name);
    }
    
    @Override
    public Role createRole(String name) {
        Role role = roleRepository.getByName(name);
        if(role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.store(role);
        }
        return role;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findUserByLogin(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException("Could not found user by username "+username);
        } else {
            return userDetails;
        }
    }

    @Override
    protected EntityRepository getRepository() {
        return userRepository;
    }

    @Override
    public User createEmptyEntity() {
        return new User();
    }
    
}

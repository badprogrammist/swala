/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.service.user;

import io.swala.domain.user.Role;
import io.swala.domain.user.User;
import io.swala.domain.user.UserCredential;
import io.swala.service.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Ильдар
 */
public interface UserService extends Service<User>,UserDetailsService {
    public void registerNewUser(UserCredential credential, Role role);
    public Role getRoleByName(String name);
    public Role createRole(String name);
}

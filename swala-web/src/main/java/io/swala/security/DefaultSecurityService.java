/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.security;

import io.swala.domain.user.Role;
import io.swala.domain.user.Roles;
import io.swala.domain.user.UserCredential;
import io.swala.service.user.DefaultUserService;
import io.swala.service.user.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ильдар
 */
@Service
public class DefaultSecurityService implements SecurityService {

    @Inject
    private UserService userService;

    @Inject
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean register(UserRegistrationData registrationData) {
        if (registrationData.isValid()) {
            UserDetails userDetails = this.userService.loadUserByUsername(registrationData.getLogin());
            if (userDetails == null) {
                String encodedPassword = passwordEncoder.encode(registrationData.getPassword());
                Role userRole = userService.createRole(Roles.USER.name());
                UserCredential credential = new UserCredential(registrationData.getLogin(), encodedPassword);
                if (userRole != null) {
                    userService.registerNewUser(credential, userRole);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public UserTransfer authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, Boolean> roles = new HashMap<>();
        UserDetails userDetails = this.userService.loadUserByUsername(username);
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.toString(), Boolean.TRUE);
        }
        return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
    }
}

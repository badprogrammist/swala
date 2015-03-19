/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.security;

import io.swala.security.exception.SecurityException;
import io.swala.domain.user.Role;
import io.swala.domain.user.Roles;
import io.swala.domain.user.User;
import io.swala.domain.user.UserCredential;
import io.swala.service.user.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultSecurityService implements SecurityService {

    public static final String CREDENTIALS_IS_NOT_VALID = "Credentials is not valid";
    
    @Inject
    private UserService userService;

    @Inject
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean register(Credentials credentials) {
        if (credentials.isValid()) {
            UserDetails userDetails = this.userService.loadUserByUsername(credentials.getLogin());
            if (userDetails == null) {
                String encodedPassword = passwordEncoder.encode(credentials.getPassword());
                Role userRole = userService.createRole(Roles.USER.name());
                UserCredential credential = new UserCredential(credentials.getLogin(), encodedPassword);
                if (userRole != null) {
                    userService.registerNewUser(credential, userRole);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public UserTransfer authenticate(Credentials credentials) throws SecurityException {
        if (credentials.isValid()) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword());
            Authentication authentication = this.authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Map<String, Boolean> roles = new HashMap<>();
            UserDetails userDetails = this.userService.loadUserByUsername(credentials.getLogin());
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                roles.put(authority.toString(), Boolean.TRUE);
            }
            return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
        } else {
            throw new SecurityException(CREDENTIALS_IS_NOT_VALID);
        }
    }
    
    
    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser";
    }
    
    @Override
    public User getPrincipal() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;
        } catch (Exception ex) {
            return User.NULL;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.security;

import io.swala.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Ильдар
 */
public class SecurityContext {
    public static User getPrincipal() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

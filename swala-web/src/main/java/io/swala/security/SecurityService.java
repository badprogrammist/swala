/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.security;

import io.swala.domain.user.User;
import io.swala.security.exception.SecurityException;

/**
 *
 * @author Ильдар
 */
public interface SecurityService {
    public boolean register(Credentials credentials);
    
    public UserTransfer authenticate(Credentials credentials) throws SecurityException;
    
    public boolean isAuthenticated();
    
    public User getPrincipal();
}

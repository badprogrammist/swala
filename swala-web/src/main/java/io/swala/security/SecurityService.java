/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.security;

/**
 *
 * @author Ильдар
 */
public interface SecurityService {
    public boolean register(UserRegistrationData registrationData);
    public UserTransfer authenticate(String username, String password);
}

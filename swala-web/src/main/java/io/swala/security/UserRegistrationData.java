/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.security;

import org.ocpsoft.common.util.Strings;


/**
 *
 * @author Ильдар
 */
public class UserRegistrationData {
    private String login;
    private String password;

    public UserRegistrationData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean isValid() {
        return !Strings.isNullOrEmpty(login) && !Strings.isNullOrEmpty(password);
    }
    
}

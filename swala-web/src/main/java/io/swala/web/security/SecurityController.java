/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.web.security;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import io.swala.domain.user.User;
import io.swala.security.Credentials;
import io.swala.security.exception.SecurityException;
import io.swala.security.SecurityService;
import io.swala.web.Navigation;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.faces.security.FaceletsAuthorizeTagUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Ильдар
 */
@Named("security")
@Scope("request")
@URLBeanName("security")
@URLMappings(mappings = {
    @URLMapping(id = "LOGIN", pattern = "/login", viewId = "/pages/security/login.xhtml"),
    @URLMapping(id = "ACCESS_DENIED", pattern = "/access_denied", viewId = "/pages/security/access-denied.xhtml"),
    @URLMapping(id = "REGISTRATION", pattern = "/registration", viewId = "/pages/security/registration.xhtml")
})
public class SecurityController implements Serializable {

    @Inject
    private SecurityService securityService;

    private String login;
    private String password;
    
    public String gotoRegistration() {
        return Navigation.getURL("REGISTRATION");
    }
    
    public String gotoLogin() {
        return Navigation.getURL("LOGIN");
    }

    public String gotoSignOut() {
        return "/swala/signout";
    }
    
    public String signIn() {
        Credentials credentials = new Credentials(login, password);
        try {
            securityService.authenticate(credentials);
        } catch (SecurityException ex) {

        }
        return Navigation.getURL("INDEX");
    }

    public String signUp() {
        Credentials credentials = new Credentials(login, password);
        securityService.register(credentials);
        return Navigation.getURL("INDEX");
    }

    public boolean isAllowed(String mappingId) {
        try {
            return FaceletsAuthorizeTagUtils.isAllowed(Navigation.getPatternByMappingId(mappingId), "GET");
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean isAuthenticated() {
        return securityService.isAuthenticated();
    }
    
    public User getPrincipal() {
        return securityService.getPrincipal();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

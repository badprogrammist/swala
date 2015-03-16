/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.web.security;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import io.swala.security.SecurityService;
import io.swala.web.Navigation;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Ильдар
 */
@Named("security")
@Scope("request")
@URLBeanName("security")
@URLMappings(mappings = {
    @URLMapping(id = "SIGN_IN", pattern = "/signin", viewId = "/pages/security/signin.xhtml"),
    @URLMapping(id = "SIGN_UP", pattern = "/signup", viewId = "/pages/security/signup.xhtml")
})
public class SecurityController implements Serializable {

    @Inject
    private SecurityService securityService;

    public String signIn() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extenalContext = facesContext.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) extenalContext.getRequest()).getRequestDispatcher("/j_spring_security_check");
        try {
            dispatcher.forward((ServletRequest) extenalContext.getRequest(), (ServletResponse) extenalContext.getResponse());
        } catch (ServletException | IOException ex) {
        }
        facesContext.responseComplete();
        return Navigation.getURL("index");
    }

    @URLAction(mappingId = "SIGN_IN")
    public void prepareSignIn() {

    }

    @URLAction(mappingId = "SIGN_UP")
    public void prepareSignUp() {

    }

}

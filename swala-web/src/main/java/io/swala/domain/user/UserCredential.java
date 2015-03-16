/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.domain.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class UserCredential implements Serializable {

    @Column(name = "login",unique = true)
    private String login;
    
    @Column(name="encoded_password")
    private String encodedPassword;
    
    public UserCredential(String login, String password) {
        this.login = login;
        this.encodedPassword = password;
    }
    
    public UserCredential() {
    }
    
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

}

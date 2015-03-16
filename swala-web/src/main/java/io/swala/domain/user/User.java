/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.domain.user;

import io.swala.domain.AbstractEntity;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findAll",
            query = "Select c from User c"),
    @NamedQuery(name = "User.findByLogin",
            query = "Select c from User c where c.credential.login = :login")})
public class User extends AbstractEntity implements UserDetails {

    @Embedded
    private UserCredential credential;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserRoleRelation> roles = new HashSet<>();

    public User() {
    }
    
    public User(UserCredential credential) {
        this.credential = credential;
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptyList();
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UserRoleRelation roleRelation : roles) {
            authorities.add(new SimpleGrantedAuthority(roleRelation.getRole().getName()));
        }
        return authorities;
    }
    
    @Override
    public String getUsername() {
        return credential.getLogin();
    }
    
    @Override
    public String getPassword() {
        return credential.getEncodedPassword();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
     public Set<UserRoleRelation> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleRelation> roles) {
        this.roles = roles;
    }
    
}

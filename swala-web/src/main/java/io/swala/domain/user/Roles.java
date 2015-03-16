/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.domain.user;

import io.swala.domain.EnumType;


/**
 *
 * @author Ильдар
 */
public enum Roles implements EnumType {
    
    USER("Пользователь");
    
    Roles(String title) {
        this.title = title;
    }
    
    private final String title;

    @Override
    public String getTitle() {
        return title;
    }
    
    
    
}

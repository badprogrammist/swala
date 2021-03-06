/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.swala.web;

import com.ocpsoft.pretty.PrettyContext;

/**
 * @organization ITS360
 * @author Ildar Gafarov badprogrammist@gmail.com
 */
public class Navigation {
    
    public static final String getURL(String mappingId) {
        return "pretty:"+mappingId;
    }
    
    public static final String getPatternByMappingId(String mappingId) {
        return PrettyContext.getCurrentInstance().getConfig().getMappingById(mappingId).getPattern();
    }
    
}

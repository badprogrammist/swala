/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.web;

import io.swala.domain.AbstractEntity;
import io.swala.service.Service;
import java.io.Serializable;
import java.util.List;
import org.apache.myfaces.orchestra.conversation.Conversation;

/**
 *
 * @author Ильдар
 * @param <E>
 */
public abstract class AbstractController<E extends AbstractEntity> implements Serializable {

    public static final String LIST_MAPPING_URL_PART = "LIST";
    public static final String CREATE_MAPPING_URL_PART = "CREATE";
    public static final String EDIT_MAPPING_URL_PART = "EDIT";
    public static final String VIEW_MAPPING_URL_PART = "VIEW";
    
    protected abstract Service<E> getService();
    protected abstract String getMappingUrlKey();
    private E current;
    private String id;

    
    public AbstractController() {
    }

    
    
    public String gotoList() {
        return Navigation.getURL(LIST_MAPPING_URL_PART +"_"+ getMappingUrlKey());
    }
    
    public String gotoCreate() {
        return Navigation.getURL(CREATE_MAPPING_URL_PART +"_"+ getMappingUrlKey());
    }
    
    public String gotoEdit() {
        return Navigation.getURL(EDIT_MAPPING_URL_PART +"_"+ getMappingUrlKey());
    }
    
    public String gotoView() {
        return Navigation.getURL(VIEW_MAPPING_URL_PART +"_"+ getMappingUrlKey());
    }
    
    public void prepareCreate() {
        beginConversation();
        setCurrent(getService().createEmptyEntity());
    }
    
    public void prepareView() {
        initCurrentFromId();
    }
    
    public void prepareEdit() {
        beginConversation();
        initCurrentFromId();
    }
    
    public void prepareList() {
    }
    
    public String store() {
        if (current != null) {
            getService().store(current);
        }
        endConversation();
        return gotoList();
    }
    
    public String update() {
        if (current != null) {
            getService().update(current);
        }
        endConversation();
        return gotoList();
    }
    
    public void remove() {
        if(current != null) {
            getService().remove(current);
        }
    }
    
    public List<E> getAll() {
        return getService().getAll();
    }
    
    public E getCurrent() {
        return current;
    }

    public void setCurrent(E current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected boolean initCurrentFromId() {
        if (getId() != null && !getId().isEmpty()) {
            try {
                Long id = Long.valueOf(getId());
                setCurrent(getService().get(id));
                return true;
            } catch (Exception ex) {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void beginConversation() {
    }
    
    public void endConversation() {
        getConversation().invalidate();
    }
    
    protected Conversation getConversation() {
        return Conversation.getCurrentInstance();
    }
}

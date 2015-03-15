/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.service;

import io.swala.domain.AbstractEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ильдар
 * @param <E>
 */
public interface Service<E extends AbstractEntity> extends Serializable {
    
    public E createEmptyEntity();
    
    public void store(E entity);
    
    public void update(E entity);
    
    public List<E> getAll();
    
    public E get(Long id);
    
    public void remove(Long id);
    
    public void remove(E entity);
}

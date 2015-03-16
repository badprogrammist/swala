/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.service;

import io.swala.domain.AbstractEntity;
import io.swala.domain.EntityRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ильдар
 * @param <E>
 */
@Transactional
public abstract class AbstractService<E extends AbstractEntity> implements Service<E> {
    
    protected abstract EntityRepository getRepository();
    
    @Override
    public void store(E entity) {
        getRepository().store(entity);
    }
    
    @Override
    public void update(E entity) {
        getRepository().update(entity);
    }
    
    @Override
    public List<E> getAll() {
        return getRepository().getAll();
    }
    
    @Override
    public E get(Long id) {
        return (E)getRepository().get(id);
    }
    
    @Override
    public void remove(Long id) {
        E entity = get(id);
        if(entity != null) {
            remove(entity);
        }
    }
    
    @Override
    public void remove(E entity) {
        getRepository().remove(entity);
    }
}

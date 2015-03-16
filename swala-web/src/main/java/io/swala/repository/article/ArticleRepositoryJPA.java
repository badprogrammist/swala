/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.repository.article;

import io.swala.domain.article.Article;
import io.swala.domain.article.ArticleRepository;
import io.swala.repository.AbstractEntityRepositoryJPA;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ильдар
 */
@Repository
public class ArticleRepositoryJPA extends AbstractEntityRepositoryJPA<Article> implements ArticleRepository {

   
    private EntityManager entityManager;

    public ArticleRepositoryJPA() {
        super(Article.class);
    }
    
     @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
}

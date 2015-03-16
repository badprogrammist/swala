/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.service.article;

import io.swala.domain.EntityRepository;
import io.swala.domain.article.Article;
import io.swala.domain.article.ArticleRepository;
import io.swala.service.AbstractService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultArticleService extends AbstractService<Article> implements ArticleService {

    @Inject
    private ArticleRepository articleRepository;
    
    
    @Override
    protected EntityRepository getRepository() {
        return articleRepository;
    }

    @Override
    public Article createEmptyEntity() {
        return new Article();
    }
    
}

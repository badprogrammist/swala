/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swala.web.article;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import io.swala.domain.article.Article;
import io.swala.service.Service;
import io.swala.service.article.ArticleService;
import io.swala.web.AbstractController;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Ильдар
 */
@Named("article")
@URLBeanName("article")
@Scope("conversation")
//@Scope("session")
@URLMappings(mappings = {
    @URLMapping(id = "LIST_ARTICLE", pattern = "/articles", viewId = "/pages/article/list.xhtml"),
    @URLMapping(id = "CREATE_ARTICLE", pattern = "/article/new", viewId = "/pages/article/create.xhtml"),
    @URLMapping(id = "EDIT_ARTICLE", pattern = "/article/edit/#{id: article.id}", viewId = "/pages/article/edit.xhtml"),
    @URLMapping(id = "VIEW_ARTICLE", pattern = "/article/#{id: article.id}", viewId = "/pages/article/view.xhtml"),
})
public class ArticleController extends AbstractController<Article> {
    
    @Inject
    private ArticleService service;

    public ArticleController() {
    }
    
    @Override
    protected Service<Article> getService() {
        return service;
    }

    @Override
    protected String getMappingUrlKey() {
        return "ARTICLE";
    }

    @URLAction(mappingId = "LIST_ARTICLE",onPostback = false)
    @Override
    public void prepareList() {
        super.prepareList();
    }

    @URLAction(mappingId = "EDIT_ARTICLE",onPostback = false)
    @Override
    public void prepareEdit() {
        super.prepareEdit();
    }

    @URLAction(mappingId = "VIEW_ARTICLE",onPostback = false)
    @Override
    public void prepareView() {
        super.prepareView();
    }

    @URLAction(mappingId = "CREATE_ARTICLE",onPostback = false)
    @Override
    public void prepareCreate() {
        super.prepareCreate();
    }
    
    
    
}

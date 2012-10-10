package controller.service;

import java.util.Collection;

import javax.persistence.EntityManager;


import model.Article;


public class ArticleService {

	private EntityManager em;
	
	public ArticleService(EntityManager pem) {
		em = pem;
	}
	
	public Collection<Article> getListArticleOnBD(){

		return em.createQuery("SELECT a FROM "+ Article.class.getName()+" a").getResultList();
	}
}

package controller.service;

import java.util.Collection;

import javax.persistence.EntityManager;


import model.Article;
import model.exception.ArticleNotFoundException;


public class ArticleService {

	private EntityManager em;
	
	public ArticleService(EntityManager pem) {
		em = pem;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Article> getListArticleOnBD(){

		return em.createQuery("SELECT a FROM "+ Article.class.getName()+" a").getResultList();
	}
	
	public Article getArticle(int code) throws ArticleNotFoundException{
		//TODO exception
		return em.find(Article.class, code);
	}
}

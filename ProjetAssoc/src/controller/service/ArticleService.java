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
		Article art = em.find(Article.class, code);
		if(art == null){
			throw new ArticleNotFoundException(code);
		}
		return art;
	}

	public void updateQteArticle(Article art,int quantite){
		em.getTransaction().begin();
		art.setStock(quantite);
		em.getTransaction().commit();
	}
	
	public void addQteArticle(Article art, int quantite){
		Article article = em.find(Article.class, art.getCode());
		em.getTransaction().begin();
		article.setStock(article.getStock()+quantite);
		em.getTransaction().commit();
	}

}

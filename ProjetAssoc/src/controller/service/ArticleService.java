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

	/**
	 * Méthode qui retourne l'article en fonction de son id(code)
	 * @param code
	 * @return article
	 * @throws ArticleNotFoundException
	 */
	public Article getArticle(int code) throws ArticleNotFoundException{
		Article art = em.find(Article.class, code);
		if(art == null){
			throw new ArticleNotFoundException(code);
		}
		return art;
	}

	/**
	 * Met a jour la quantite du stock de l'article en base
	 * @param art
	 * @param quantite
	 * @return
	 */
	public boolean updateQteArticle(Article art,int quantite){
		try{
			em.getTransaction().begin();
			art.setStock(quantite);
			em.getTransaction().commit();
			return true;
		}catch (Exception r){
			return false;
		}
	}

	/**
	 * Ajout des stocks à l'article en base
	 * @param art
	 * @param quantite
	 * @return
	 */
	public boolean addQteArticle(Article art, int quantite){
		try{
			Article article = em.find(Article.class, art.getCode());
			em.getTransaction().begin();
			article.setStock(article.getStock()+quantite);
			em.getTransaction().commit();
			return true;
		}catch(Exception r){
			return false;
		}
	}

}

package model;

public class LignePanier implements Comparable<LignePanier>{

	private Article article;
	private int quantite;

	public LignePanier(Article art, int qte) {
		this.article = art;
		this.quantite = qte;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof LignePanier){
			if(this.article.equals(((LignePanier) arg0).article) && this.quantite ==((LignePanier) arg0).quantite)
				return true;
			else 
				return false;
		}else
			return false;
	}

	@Override
	public int hashCode() {
		return article.hashCode();
	}

	@Override
	public String toString() {
		return "LigneCommande : "+article.toString()+" | "+this.quantite;
	}

	@Override
	public int compareTo(LignePanier o) {
		int comp =  this.article.compareTo(o.article);
		
		if(comp != 0)
			return comp;
		
		comp = ((Integer)this.quantite).compareTo(o.quantite);
		
		return comp;
	}


}

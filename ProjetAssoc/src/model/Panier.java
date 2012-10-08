package model;

import java.util.ArrayList;
import java.util.Collections;

import model.comparator.CompareLignePanierOnArticle;

public class Panier {

	ArrayList<LignePanier> lignesPanier;
	
	public Panier() {
		lignesPanier = new ArrayList<LignePanier>();
	}
	
	@Override
	public String toString() {
		String s =  "Panier : \n";
		for(LignePanier l : lignesPanier){
			s+=l.toString()+"\n";
		}
		
		return s;
		
	}

	/**
	 * Méthode addArticle
	 * Permet d'ajouter un article au panier.
	 * Utilise la recherche binaire (dichotomie) afin de placer l'article dans le panier.
	 * Si ce dernier existe déja, il incremente la quantité.
	 * Utilise la classe comparator CompareLignePanierOnArticle
	 * @param art
	 */
	public void addArticle(Article art){
		LignePanier nvlle_ligne = new LignePanier(art, 1);
		int index = Collections.binarySearch(lignesPanier,nvlle_ligne, new CompareLignePanierOnArticle());
		if(index < 0){
			lignesPanier.add((-index)-1, nvlle_ligne);
		}
		else{
			lignesPanier.get(index).setQuantite(lignesPanier.get(index).getQuantite()+1);
		}
	}
}

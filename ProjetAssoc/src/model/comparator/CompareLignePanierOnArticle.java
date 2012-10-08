package model.comparator;

import java.util.Comparator;

import model.LignePanier;


public class CompareLignePanierOnArticle implements Comparator<LignePanier> {

	@Override
	public int compare(LignePanier arg0, LignePanier arg1) {
		return arg0.getArticle().compareTo(arg1.getArticle());
	}

}

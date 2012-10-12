package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: LigneCommande
 *
 */
@Entity
@Table(name="ligne_commande", schema="zakaria")
public class LigneCommande implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_lc")
	private int id;
	private static final long serialVersionUID = 1L;

    @Column(name = "id_commande")
	private int idCommande;

    @Column(name = "id_article")
	private int idArticle;
	
	
	@ManyToOne
    @JoinColumn(name = "id_commande", insertable=false,updatable=false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "id_article", insertable=false,updatable=false)
    private Article article;

    @Column(name = "quantite")
    private int quantite;
	
	public LigneCommande() {
		super();
	}
	public LigneCommande(int cmd,int art_code,int qte) {
		super();
		this.idArticle = art_code;
		this.quantite = qte;
		this.idCommande = cmd;
	}

	@Override
	public String toString() {
		return article+" | "+quantite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
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
   
}

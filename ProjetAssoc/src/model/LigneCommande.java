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

	@Override
	public String toString() {
		return article+" | "+quantite;
	}
   
}

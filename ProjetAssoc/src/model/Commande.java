package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COMMANDE", schema="zakaria")

public class Commande implements Comparable<Commande>{
	
	public Integer getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMMANDE", nullable=false)
	private Integer identifiant;
	
	@Column(name="ID_CLIENT", nullable=false)
	private String client;
	
	@Column(name="date_commande", nullable=false)
	private Date date_commande;
	
	@OneToMany(mappedBy = "commande")
	private Set<LigneCommande> ligneCommandes;
	
	@Override
	public int compareTo(Commande com) {
		return this.identifiant.compareTo(com.identifiant);
		
	}

	@Override
	public int hashCode() {
		return identifiant.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (identifiant == null) {
			if (other.identifiant != null)
				return false;
		} else if (!identifiant.equals(other.identifiant))
			return false;
		return true;
	}

	public Set<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}

	public Date getDate_commande() {
		return date_commande;
	}

	public void setDate_commande(Date date_commande) {
		this.date_commande = date_commande;
	}

}

package model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="EST_COMPOSE", schema="zakaria")

public class EstCompose  {

	
	public Integer getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	
	@Id
	@Column(name="ID_COMMANDE", nullable=false)
	private Integer identifiant;
	
	@Column(name="CODE", nullable=false)
	private Integer code;
	
	@Column(name="QUANTITE", nullable=false)
	private Integer quantite;
	
	
	

}

package model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
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
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
	
	@Id
	@Column(name="ID_COMMANDE", nullable=false)
	private Integer identifiant;
	
	@Column(name="ID_CLIENT", nullable=false)
	private String client;
	
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

}

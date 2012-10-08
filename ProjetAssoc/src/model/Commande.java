package model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Commande implements Comparable<Commande>{
	
	public Integer getIdentifiant() {
		return identifiant;
	}

	public void setCode(Integer identifiant) {
		this.identifiant = identifiant;
	}
	
	@Id
	@Column(name="ID_COMMANDE", nullable=false)
	private Integer identifiant;
	
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

package com.revature.dtos;

import com.revature.beans.Activation;
import com.revature.beans.Priv;

public class UserDto {
	private Integer id;
	private String username;
	private String fName;
	private String lName;
	private Priv priv;
	private Activation acvtivation;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(Integer id, String username, String fName, String lName, Priv priv, Activation acvtivation) {
		this.id = id;
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.priv = priv;
		this.acvtivation = acvtivation;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", fName=" + fName + ", lName=" + lName + ", priv="
				+ priv + ", acvtivation=" + acvtivation + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acvtivation == null) ? 0 : acvtivation.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((priv == null) ? 0 : priv.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (acvtivation == null) {
			if (other.acvtivation != null)
				return false;
		} else if (!acvtivation.equals(other.acvtivation))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (priv == null) {
			if (other.priv != null)
				return false;
		} else if (!priv.equals(other.priv))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public Priv getPriv() {
		return priv;
	}
	public void setPriv(Priv priv) {
		this.priv = priv;
	}
	public Activation getAcvtivation() {
		return acvtivation;
	}
	public void setAcvtivation(Activation acvtivation) {
		this.acvtivation = acvtivation;
	}
	
	
}

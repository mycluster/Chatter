package com.revature.dtos;

import com.revature.beans.Activation;
import com.revature.beans.Priv;

public class UserDto {
	private Integer id;
	private String username;
	private String fName;
	private String lName;
	private Priv priv;
	private Activation activation;
	private String email;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(Integer id, String username, String fName, String lName, Priv priv, Activation activation,
			String email) {
		this.id = id;
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.priv = priv;
		this.activation = activation;
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", fName=" + fName + ", lName=" + lName + ", priv="
				+ priv + ", activation=" + activation + ", email=" + email + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activation == null) ? 0 : activation.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (activation == null) {
			if (other.activation != null)
				return false;
		} else if (!activation.equals(other.activation))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
	public Activation getActivation() {
		return activation;
	}
	public void setActivation(Activation activation) {
		this.activation = activation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

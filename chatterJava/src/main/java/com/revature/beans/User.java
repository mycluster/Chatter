package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="usrs")
public class User {
	@Id
	@Column(name="u_id")
	@SequenceGenerator(sequenceName="user_seq", name="user_seq")
	@GeneratedValue(generator="user_seq", strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="f_name")
	private String fName;
	
	@Column(name="l_name")
	private String lName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="priv")
	private Priv priv;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String username, String fName, String lName, Priv priv) {
		this.id = id;
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.priv = priv;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fName=" + fName + ", lName=" + lName + ", priv=" + priv
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		User other = (User) obj;
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
}

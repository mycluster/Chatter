package com.revature.beans;

public class ClassMembership {
	private Integer id;
	private User user;
	private Class cls;
	private ClassRole role;
	public ClassMembership() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassMembership(Integer id, User user, Class cls, ClassRole role) {
		this.id = id;
		this.user = user;
		this.cls = cls;
		this.role = role;
	}
	@Override
	public String toString() {
		return "ClassMembership [id=" + id + ", user=" + user + ", cls=" + cls + ", role=" + role + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cls == null) ? 0 : cls.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ClassMembership other = (ClassMembership) obj;
		if (cls == null) {
			if (other.cls != null)
				return false;
		} else if (!cls.equals(other.cls))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Class getCls() {
		return cls;
	}
	public void setCls(Class cls) {
		this.cls = cls;
	}
	public ClassRole getRole() {
		return role;
	}
	public void setRole(ClassRole role) {
		this.role = role;
	}
	
}

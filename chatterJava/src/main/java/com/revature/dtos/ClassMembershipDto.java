package com.revature.dtos;

import com.revature.beans.ClassRole;
import com.revature.beans.Cls;

public class ClassMembershipDto {
	private Integer id;
	private Cls cls;
	private ClassRole role;
	private UserDto user;
	public ClassMembershipDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassMembershipDto(Integer id, Cls cls, ClassRole role, UserDto user) {
		this.id = id;
		this.cls = cls;
		this.role = role;
		this.user = user;
	}
	@Override
	public String toString() {
		return "ClassMembershipDto [id=" + id + ", cls=" + cls + ", role=" + role + ", user=" + user + "]";
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
		ClassMembershipDto other = (ClassMembershipDto) obj;
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
	public Cls getCls() {
		return cls;
	}
	public void setCls(Cls cls) {
		this.cls = cls;
	}
	public ClassRole getRole() {
		return role;
	}
	public void setRole(ClassRole role) {
		this.role = role;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}

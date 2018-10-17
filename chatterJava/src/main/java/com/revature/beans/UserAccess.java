package com.revature.beans;

public class UserAccess {
	private Integer id;
	private User user;
	private Note note;
	private AccessLevel access;
	public UserAccess() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAccess(Integer id, User user, Note note, AccessLevel access) {
		this.id = id;
		this.user = user;
		this.note = note;
		this.access = access;
	}
	@Override
	public String toString() {
		return "UserAccess [id=" + id + ", user=" + user + ", note=" + note + ", access=" + access + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
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
		UserAccess other = (UserAccess) obj;
		if (access == null) {
			if (other.access != null)
				return false;
		} else if (!access.equals(other.access))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
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
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public AccessLevel getAccess() {
		return access;
	}
	public void setAccess(AccessLevel access) {
		this.access = access;
	}
	
	
}

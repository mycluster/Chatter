package com.revature.dtos;

import com.revature.beans.AccessLevel;

public class UserAccessDto {
	private Integer id;
	private AccessLevel access;
	private NoteDto note;
	private UserDto user;
	public UserAccessDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAccessDto(Integer id, AccessLevel access, NoteDto note, UserDto user) {
		this.id = id;
		this.access = access;
		this.note = note;
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserAccessDto [id=" + id + ", access=" + access + ", note=" + note + ", user=" + user + "]";
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
		UserAccessDto other = (UserAccessDto) obj;
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
	public AccessLevel getAccess() {
		return access;
	}
	public void setAccess(AccessLevel access) {
		this.access = access;
	}
	public NoteDto getNote() {
		return note;
	}
	public void setNote(NoteDto note) {
		this.note = note;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}

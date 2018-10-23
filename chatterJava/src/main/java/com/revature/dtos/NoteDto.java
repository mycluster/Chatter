package com.revature.dtos;

import java.sql.Timestamp;

import com.revature.beans.NoteType;

public class NoteDto {
	private Integer id;
	private Timestamp lastEdited;
	private String location;
	private UserDto owner;
	private NoteType type;
	public NoteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoteDto(Integer id, Timestamp lastEdited, String location, UserDto owner, NoteType type) {
		this.id = id;
		this.lastEdited = lastEdited;
		this.location = location;
		this.owner = owner;
		this.type = type;
	}
	@Override
	public String toString() {
		return "NoteDto [id=" + id + ", lastEdited=" + lastEdited + ", location=" + location + ", owner=" + owner
				+ ", type=" + type + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastEdited == null) ? 0 : lastEdited.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		NoteDto other = (NoteDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastEdited == null) {
			if (other.lastEdited != null)
				return false;
		} else if (!lastEdited.equals(other.lastEdited))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getLastEdited() {
		return lastEdited;
	}
	public void setLastEdited(Timestamp lastEdited) {
		this.lastEdited = lastEdited;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public UserDto getOwner() {
		return owner;
	}
	public void setOwner(UserDto owner) {
		this.owner = owner;
	}
	public NoteType getType() {
		return type;
	}
	public void setType(NoteType type) {
		this.type = type;
	}
}

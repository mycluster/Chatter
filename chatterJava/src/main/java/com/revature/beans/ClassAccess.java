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
@Table(name="class_accesses")
public class ClassAccess {
	
	@Id
	@Column(name="a_id")
	@SequenceGenerator(sequenceName="class_access_seq", name="class_access_seq")
	@GeneratedValue(generator="class_access_seq", strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cls")
	private Class cls;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="note")
	private Note note;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="a_level")
	private AccessLevel access;
	public ClassAccess() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassAccess(Integer id, Class cls, Note note, AccessLevel access) {
		this.id = id;
		this.cls = cls;
		this.note = note;
		this.access = access;
	}
	@Override
	public String toString() {
		return "ClassAccess [id=" + id + ", cls=" + cls + ", note=" + note + ", access=" + access + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + ((cls == null) ? 0 : cls.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
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
		ClassAccess other = (ClassAccess) obj;
		if (access == null) {
			if (other.access != null)
				return false;
		} else if (!access.equals(other.access))
			return false;
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
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Class getCls() {
		return cls;
	}
	public void setCls(Class cls) {
		this.cls = cls;
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

package com.revature.beans;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notes")
public class Note {
	@Id
	@Column(name = "n_id")
	@SequenceGenerator(sequenceName = "note_seq", name = "note_seq")
	@GeneratedValue(generator = "note_seq", strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ownr")
	private User owner;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ty")
	private NoteType type;

	@Column(name = "loc")
	private String location;

	@Column(name = "last_edited", columnDefinition = "TIMESTAMP")
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp lastEdited;
	
	@Column(name="n_name")
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "editing")
	private User editor;

	protected void onUpdate() {
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		// a java current time (now) instance

		lastEdited = new Timestamp(now.getTime());
	}

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Note(Integer id, User owner, NoteType type, String location, String name, User editor) {
		this.id = id;
		this.owner = owner;
		this.type = type;
		this.location = location;
		this.name = name;
		this.editor = editor;
		this.onUpdate();
	}
	
	

	public Note(Integer id, User owner, NoteType type, String location, String name) {
		this.id = id;
		this.owner = owner;
		this.type = type;
		this.location = location;
		this.name = name;
		this.editor = null;
		this.onUpdate();
	}

	public Note(Integer id, User owner, NoteType type, String location, Timestamp lastEdited, String name, User editor) {
		this.id = id;
		this.owner = owner;
		this.type = type;
		this.location = location;
		this.lastEdited = lastEdited;
		this.editor = editor;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", owner=" + owner + ", type=" + type + ", location=" + location + ", lastEdited="
				+ lastEdited + ", name=" + name + ", editor=" + editor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((editor == null) ? 0 : editor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastEdited == null) ? 0 : lastEdited.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Note other = (Note) obj;
		if (editor == null) {
			if (other.editor != null)
				return false;
		} else if (!editor.equals(other.editor))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public NoteType getType() {
		return type;
	}

	public void setType(NoteType type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(Timestamp lastEdited) {
		this.lastEdited = lastEdited;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}

}

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
@Table(name="classes")
public class Cls {
	@Id
	@Column(name="c_id")
	@SequenceGenerator(sequenceName="CLASS_SEQ", name="class_seq")
	@GeneratedValue(generator="class_seq", strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="c_name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="c_category")
	private ClassCategory category;

	public Cls() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cls(Integer id, String name, ClassCategory category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Cls [id=" + id + ", name=" + name + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Cls other = (Cls) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassCategory getCategory() {
		return category;
	}

	public void setCategory(ClassCategory category) {
		this.category = category;
	}
	
	
}

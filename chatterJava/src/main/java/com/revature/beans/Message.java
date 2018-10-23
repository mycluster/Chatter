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
@Table(name="message")
public class Message {
	
	@Id
	@Column(name="m_id")
	@SequenceGenerator(sequenceName="message_seq", name="message_seq")
	@GeneratedValue(generator="message_seq", strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ownr")
	private User owner;
	
	@Column(name="message")
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", owner=" + owner + ", message=" + message + "]";
	}

	public Message(Integer id, User owner, String message) {
		super();
		this.id = id;
		this.owner = owner;
		this.message = message;
	}

	public Message() {
		super();
	}

	public Message(User owner, String message) {
		super();
		this.owner = owner;
		this.message = message;
	}
	
	
	
}

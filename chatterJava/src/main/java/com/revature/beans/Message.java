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
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@Column(name = "m_id")
	@SequenceGenerator(sequenceName = "message_seq", name = "message_seq")
	@GeneratedValue(generator = "message_seq", strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender")
	private User sender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver")
	private User receiver;

	@Column(name = "message")
	private String message;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "edited")
	private Edit edited;

	@Column(name = "sent_at", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp sentAt;

	// exciting method to make sure that the sent_at timestamp is accurate
	// we went to be able to tell when a message is sent, so  we're going to 
	// automatically generate the sentAt time when a message is created without a
	// specified sentAt value
	@PrePersist
	protected void onCreate() {
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		// a java current time (now) instance
		sentAt = new Timestamp(now.getTime());
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Message(Integer id, User sender, User receiver, String message, Edit edited) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.edited = edited;
		// auto generate the sentAt timestamp
		this.onCreate();
	}

	public Message(Integer id, User sender, User receiver, String message, Edit edited, Timestamp sentAt) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.edited = edited;
		this.sentAt = sentAt;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", message=" + message
				+ ", edited=" + edited + ", sentAt=" + sentAt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edited == null) ? 0 : edited.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((sentAt == null) ? 0 : sentAt.hashCode());
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
		Message other = (Message) obj;
		if (edited == null) {
			if (other.edited != null)
				return false;
		} else if (!edited.equals(other.edited))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (sentAt == null) {
			if (other.sentAt != null)
				return false;
		} else if (!sentAt.equals(other.sentAt))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Edit getEdited() {
		return edited;
	}

	public void setEdited(Edit edited) {
		this.edited = edited;
	}

	public Timestamp getSentAt() {
		return sentAt;
	}

	public void setSentAt(Timestamp sentAt) {
		this.sentAt = sentAt;
	}

}

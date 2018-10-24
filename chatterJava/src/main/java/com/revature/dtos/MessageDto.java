package com.revature.dtos;

import java.sql.Timestamp;

import com.revature.beans.Edit;


public class MessageDto {
	private Integer id;
	private UserDto sender;
	private UserDto receiver;
	private String message;
	private Edit edited;
	private Timestamp sentAt;
	public MessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageDto(Integer id, UserDto sender, UserDto receiver, String message, Edit edited, Timestamp sentAt) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.edited = edited;
		this.sentAt = sentAt;
	}
	@Override
	public String toString() {
		return "MessageDto [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", message=" + message
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
		MessageDto other = (MessageDto) obj;
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
	public UserDto getSender() {
		return sender;
	}
	public void setSender(UserDto sender) {
		this.sender = sender;
	}
	public UserDto getReceiver() {
		return receiver;
	}
	public void setReceiver(UserDto receiver) {
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

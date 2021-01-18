package com.dannychung.TodoCRUD;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="todo_item")
@Table(name="todo_item")
public class TodoItem {
	@Column
	private String title;
	@Column
	private byte done;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	protected TodoItem() {
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public byte getDone() {
		return done;
	}
	public void setDone(byte done) {
		this.done = done;
	}
	
	public long getId() {
		return (id);
	}
	public void setId(long id) {
		this.id = id;
	}
	
}

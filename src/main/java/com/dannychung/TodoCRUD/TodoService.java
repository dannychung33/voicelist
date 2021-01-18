package com.dannychung.TodoCRUD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	@Autowired
	private TodoRepository repo;
	
	public List<TodoItem> listAll(){
		return repo.findAll();
	}
	
	public List<String> getLastTitle(){
		return repo.getLastTitle();
	}
	
	public void save(TodoItem item) {
		repo.save(item);
	}
	
	public TodoItem get(Long id) {
		return repo.findById(id).get();
	}
	
	
	public void delete(Long id) {
		repo.deleteById(id);
		repo.resequence();
		
	}
	
	
}

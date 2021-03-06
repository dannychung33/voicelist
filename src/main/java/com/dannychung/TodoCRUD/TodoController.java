package com.dannychung.TodoCRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	//Rest API end point that returns all to-do items in database
	@GetMapping("/items")
	public List<TodoItem> list() {
	    return service.listAll();
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<TodoItem> get(@PathVariable long id) {
	    try {
	        TodoItem item = service.get(id);
	        return new ResponseEntity<TodoItem>(item, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<TodoItem>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	ArrayList<Long> idArray =  new ArrayList<Long>();
	@PostMapping("/items")
	public void add(@RequestBody TodoItem item) {
	    service.save(item);
	}
	
	@GetMapping("/items/last")
	public List<String> getLastTitle(){
		return service.getLastTitle();
	}

	@PutMapping("/items/{id}")
	public void update(@RequestBody TodoItem item, @PathVariable(value="id") long id) {
	    Optional<TodoItem> existingItem = Optional.ofNullable(service.get(id));
	    TodoItem newItem = existingItem.get();
	    newItem.setTitle(item.getTitle());
	    newItem.setDone(item.getDone());

	    service.save(newItem); 
	}
	
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<TodoItem> delete(@PathVariable(value="id") long id) {
		Optional<TodoItem> itemToBeDeleted = Optional.ofNullable(service.get(id));
		
		if(itemToBeDeleted != null) {
			
			service.delete(id);
			return new ResponseEntity<TodoItem>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<TodoItem>(HttpStatus.NOT_FOUND);
		}
	}
}

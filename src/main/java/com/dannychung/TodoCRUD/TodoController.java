package com.dannychung.TodoCRUD;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/items")
	public void add(@RequestBody TodoItem item) {
	    service.save(item);
	}

	@PutMapping("/items/{id}")
	public ResponseEntity<?> update(@RequestBody TodoItem item, @PathVariable long id) {
	    try {
	        TodoItem existingItem = service.get(id);
	        service.save(item);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/items/{id}")
	public void delete(@PathVariable long id) {
	    service.delete(id);
	}
}

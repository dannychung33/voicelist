package com.dannychung.TodoCRUD;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface TodoRepository extends JpaRepository<TodoItem, Long>, CustomTodoRepository{
	
	
}

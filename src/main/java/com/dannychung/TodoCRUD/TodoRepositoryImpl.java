package com.dannychung.TodoCRUD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TodoRepositoryImpl implements CustomTodoRepository{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public void resequence() {
		String sqlDropId = "ALTER TABLE todo_item DROP id;";
		jdbcTemplate.execute(sqlDropId);
		String sqlIncrement = "ALTER TABLE todo_item AUTO_INCREMENT = 1;";
		jdbcTemplate.execute(sqlIncrement);
		String sqlAddId = "ALTER TABLE todo_item ADD id int UNSIGNED NOT NULL AUTO_INCREMENT, ADD primary key (id);";
		jdbcTemplate.execute(sqlAddId);
	
	}
	
	public List<String> getLastTitle() {
		String sqlGetLast = "SELECT title FROM todo_item ORDER BY id DESC LIMIT 1";
		List<String> lastTitle = jdbcTemplate.queryForList(sqlGetLast, String.class);
		return lastTitle;
	}

}

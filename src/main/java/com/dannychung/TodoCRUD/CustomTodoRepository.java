package com.dannychung.TodoCRUD;

import java.util.List;

public interface CustomTodoRepository {
	void resequence();
	List<String> getLastTitle();
}

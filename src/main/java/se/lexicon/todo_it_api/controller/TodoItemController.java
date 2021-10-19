package se.lexicon.todo_it_api.controller;

import org.springframework.http.ResponseEntity;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.form.TodoItemFormDto;

import java.util.Collection;

public interface TodoItemController {

    ResponseEntity<TodoItemDto> create(TodoItemFormDto form);
    ResponseEntity<String> delete(Integer todoId);
    ResponseEntity<Collection<TodoItemDto>> find(String string, String[] strings);
    ResponseEntity<TodoItemDto> findById(Integer todoId);
    ResponseEntity<TodoItemDto> update(Integer todoId, TodoItemFormDto form);
}

package se.lexicon.todo_it_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.form.TodoItemFormDto;
import se.lexicon.todo_it_api.service.TodoItemService;

import java.util.Collection;

@RestController
public class TodoItemControllerImpl implements TodoItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemControllerImpl(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @Override
    public ResponseEntity<TodoItemDto> create(TodoItemFormDto form) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Integer todoId) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<TodoItemDto>> find(String string, String[] strings) {
        return null;
    }

    @Override
    public ResponseEntity<TodoItemDto> findById(Integer todoId) {
        return null;
    }

    @Override
    public ResponseEntity<TodoItemDto> update(Integer todoId, TodoItemFormDto form) {
        return null;
    }
}

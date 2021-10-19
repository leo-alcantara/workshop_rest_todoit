package se.lexicon.todo_it_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.form.PersonFormDto;
import se.lexicon.todo_it_api.service.PersonServiceImpl;
import se.lexicon.todo_it_api.service.TodoItemService;

import java.util.Collection;

@RestController
public class PersonControllerImpl implements PersonController {

    private final PersonServiceImpl personService;
    private final TodoItemService todoItemService;

    @Autowired
    public PersonControllerImpl(PersonServiceImpl personService, TodoItemService todoItemService) {
        this.personService = personService;
        this.todoItemService = todoItemService;
    }

    @Override
    public ResponseEntity<PersonDto> assignTodoItem(Integer personId, Integer todoId) {
        return null;
    }

    @Override
    @PostMapping("/person/api/v1/")
    public ResponseEntity<PersonDto> create(@RequestBody PersonFormDto form) {
        PersonDto saved = personService.create(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    @DeleteMapping("/todo/api/v1/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Integer personId) {
        boolean deleted = personService.delete(personId);
        return ResponseEntity.ok(deleted);
    }

    @Override
    public ResponseEntity<?> find(String string) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<PersonDto>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<PersonDto> findById(Integer personId) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<PersonDto>> findIdlePeople() {
        return null;
    }

    @Override
    public ResponseEntity<Collection<TodoItemDto>> getTodoItems(Integer personId) {
        return null;
    }

    @Override
    public ResponseEntity<PersonDto> removeTodoItem(Integer personId, Integer todoId) {
        return null;
    }

    @Override
    public ResponseEntity<PersonDto> update(Integer personId, PersonFormDto form) {
        return null;
    }
}

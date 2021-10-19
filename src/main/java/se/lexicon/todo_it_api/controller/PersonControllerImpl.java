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
    @GetMapping("/todo/api/v1/people/{id}/add")
    public ResponseEntity<PersonDto> assignTodoItem(@PathVariable("id") Integer personId, @RequestParam("todoId") Integer todoId) {
        return ResponseEntity.ok(personService.addTodoItem(personId, todoId));
    }

    @Override
    @PostMapping("/todo/api/v1/people")
    public ResponseEntity<PersonDto> create(@RequestBody PersonFormDto form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(form));
    }

    @Override
    @DeleteMapping("/todo/api/v1/people/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Integer personId) {
        boolean deleted = personService.delete(personId);
        return ResponseEntity.ok(deleted ? "Person was deleted" : "Person not deleted");
    }

    @Override
    @GetMapping("/todo/api/v1/people")
    public ResponseEntity<?> find(@RequestParam(value = "search", defaultValue = "all") String search) {

        switch (search.toLowerCase()){
            case "idle":
                return findIdlePeople();
            case"all":
                return findAll();
            default: throw new IllegalArgumentException("Invalid search Param: Valid Params are, all and idle.");
        }
    }

    @Override
    public ResponseEntity<Collection<PersonDto>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @Override
    @GetMapping("/todo/api/v1/people/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable("id") Integer personId) {
        return ResponseEntity.ok(personService.findById(personId));
    }

    @Override
    public ResponseEntity<Collection<PersonDto>> findIdlePeople() {
        return ResponseEntity.ok(personService.findIdlePeople());
    }

    @Override
    @GetMapping("/todo/api/v1/people/{id}/todos")
    public ResponseEntity<Collection<TodoItemDto>> getTodoItems(@PathVariable("id") Integer personId) {
        return ResponseEntity.ok(todoItemService.findAllByPersonId(personId));
    }

    @Override
    @GetMapping("/todo/api/v1/people/{id}/remove")
    public ResponseEntity<PersonDto> removeTodoItem(@PathVariable("id") Integer personId,
                                                    @RequestParam("todoId") Integer todoId) {
        return ResponseEntity.ok(personService.removeTodoItem(personId, todoId));
    }

    @Override
    @PutMapping("/todo/api/v1/people/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable("id") Integer personId,
                                            @RequestBody PersonFormDto form) {
        return ResponseEntity.ok(personService.update(personId, form));
    }
}

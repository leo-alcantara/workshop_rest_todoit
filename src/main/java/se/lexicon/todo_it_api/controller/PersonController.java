package se.lexicon.todo_it_api.controller;

import org.springframework.http.ResponseEntity;
import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.form.PersonFormDto;

import java.util.Collection;

public interface PersonController {

    ResponseEntity<PersonDto> assignTodoItem(Integer personId, Integer todoId);
    ResponseEntity<PersonDto> create(PersonFormDto form);
    ResponseEntity<String> deletePerson(Integer personId);
    ResponseEntity<?> find(String string);
    ResponseEntity<Collection<PersonDto>> findAll();
    ResponseEntity<PersonDto> findById(Integer personId);
    ResponseEntity<Collection<PersonDto>> findIdlePeople();
    ResponseEntity<Collection<TodoItemDto>> getTodoItems(Integer personId);
    ResponseEntity<PersonDto> removeTodoItem(Integer personId, Integer todoId);
    ResponseEntity<PersonDto> update(Integer personId, PersonFormDto form);
}

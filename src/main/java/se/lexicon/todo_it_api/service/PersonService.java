package se.lexicon.todo_it_api.service;

import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.form.PersonFormDto;

import java.util.List;

public interface PersonService {

    PersonDto create(PersonFormDto form);
    boolean delete(Integer personId);
    List<PersonDto> findAll();
    PersonDto findById(Integer integer);
    List<PersonDto> findIdlePeople();
    PersonDto addTodoItem(Integer personId, Integer todoItemId);
    PersonDto removeTodoItem(Integer personId, Integer todoItemId);
    PersonDto update(Integer personId, PersonFormDto formDto);
}

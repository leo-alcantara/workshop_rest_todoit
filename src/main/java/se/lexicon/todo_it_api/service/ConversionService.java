package se.lexicon.todo_it_api.service;


import org.springframework.stereotype.Component;
import se.lexicon.todo_it_api.form.PersonFormDTO;
import se.lexicon.todo_it_api.form.TodoItemFormDTO;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.util.ArrayList;

@Component
public class ConversionService {

    public Person toPerson (PersonFormDTO form){
        return new Person(0, form.getFirstName(), form.getLastName(), form.getBirthDate(), new ArrayList<>());
    }

    public PersonFormDTO toPersonFormDto(Person person){
        return new PersonFormDTO(person.getFirstName(), person.getLastName(),
                person.getBirthDate());
    }

    public TodoItem toTodoItem(TodoItemFormDTO form){
        return new TodoItem(0, form.getTitle(), form.getDescription(), form.getDeadLine(), false, new Person());
    }

    public TodoItemFormDTO toTodoItemForm(TodoItem todoItem){
        return new TodoItemFormDTO(todoItem.getTitle(),
                todoItem.getDescription(), todoItem.getDeadLine(), todoItem.isDone());
    }


}

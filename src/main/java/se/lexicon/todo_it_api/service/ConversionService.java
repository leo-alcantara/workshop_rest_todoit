package se.lexicon.todo_it_api.service;


import org.springframework.stereotype.Component;
import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.dto.PersonDtoSmall;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.dto.TodoItemDtoSmall;
import se.lexicon.todo_it_api.form.PersonFormDto;
import se.lexicon.todo_it_api.form.TodoItemFormDto;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConversionService {

    public Person toPerson (PersonFormDto form){
        return new Person(0, form.getFirstName(), form.getLastName(), form.getBirthDate(), new ArrayList<>());
    }

    public PersonDto toPersonDto(Person person){

        List<TodoItemDtoSmall> list = new ArrayList<>();
        for (TodoItem item: person.getTodoItems()){
            list.add(new TodoItemDtoSmall(item.getTodoId(), item.getTitle(), item.getDescription(), item.getDeadLine() ,item.isDone()));
        }

        PersonDto personDto= new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName(),
                person.getBirthDate(), list);

        return personDto;
    }

    public TodoItem toTodoItem(TodoItemFormDto form){
        return new TodoItem(0, form.getTitle(), form.getDescription(), form.getDeadLine(), false, null);
    }

    public TodoItemDto toTodoItemDto(TodoItem todoItem){
        Person person = todoItem.getAssignee();
        PersonDtoSmall personDtoSmall = new PersonDtoSmall(person.getPersonId(), person.getFirstName(), person.getLastName(), person.getBirthDate());
        return new TodoItemDto(todoItem.getTodoId(), todoItem.getTitle(),
                todoItem.getDescription(), todoItem.getDeadLine(), todoItem.isDone(), personDtoSmall);
    }


}

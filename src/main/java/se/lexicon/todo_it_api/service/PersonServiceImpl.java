package se.lexicon.todo_it_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todo_it_api.data.PersonDAO;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.form.PersonFormDto;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDAO personDAO;
    private final ConversionService conversionService;
    private final TodoItemDAO todoItemDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO, ConversionService conversionService, TodoItemDAO todoItemDAO) {
        this.personDAO = personDAO;
        this.conversionService = conversionService;
        this.todoItemDAO = todoItemDAO;
    }


    @Override
    public PersonDto create(PersonFormDto form) {
        Person person = conversionService.toPerson(form);
        Person saved = personDAO.save(person);
        return conversionService.toPersonDto(saved);
    }

    @Override
    public boolean delete(Integer personId) {
        Person person = personDAO.findById(personId).get();
        personDAO.delete(person);
        return true;
    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> personList = new ArrayList<>();
        List<PersonDto> personDtoList = new ArrayList<>();
        personList = personDAO.findAll();
        for (Person person:personList) {
            PersonDto personDto = conversionService.toPersonDto(person);
            personDtoList.add(personDto);
        }
        return personDtoList;
    }

    @Override
    public PersonDto findById(Integer integer) {
        Optional<Person> foundPerson = personDAO.findById(integer);
        return conversionService.toPersonDto(foundPerson.get());
    }

    @Override
    public List<PersonDto> findIdlePeople() {
        List<Person> personList = new ArrayList<>();
        List<PersonDto> personDtoList = new ArrayList<>();
        personList = personDAO.findIdlePeople();
        for (Person person:personList) {
            PersonDto personDto = conversionService.toPersonDto(person);
            personDtoList.add(personDto);
        }
        return personDtoList;
    }

    @Override
    public PersonDto addTodoItem(Integer personId, Integer todoItemId) {
        Person foundPerson = personDAO.findById(personId).get();
        TodoItem todoItem = todoItemDAO.findById(todoItemId).get();
        List<TodoItem> todoItems = new ArrayList<>();
        todoItems.add(todoItem);
        foundPerson.setTodoItems(todoItems);
        PersonDto personDto = conversionService.toPersonDto(foundPerson);
        return personDto;
    }

    @Override
    public PersonDto removeTodoItem(Integer personId, Integer todoItemId) {
        Person foundPerson = personDAO.findById(personId).get();
        TodoItem todoItem = todoItemDAO.findById(todoItemId).get();
        List<TodoItem> todoItems = new ArrayList<>();
        todoItems.remove(todoItem);
        foundPerson.setTodoItems(todoItems);
        PersonDto personDto = conversionService.toPersonDto(foundPerson);
        return personDto;
    }

    @Override
    public PersonDto update(Integer personId, PersonFormDto formDto) {
        Optional<Person> original = personDAO.findById(personId);

        original.ifPresent((p)->{
            p.setFirstName(formDto.getFirstName());
            p.setLastName(formDto.getLastName());
            p.setBirthDate(formDto.getBirthDate());
        });
        return conversionService.toPersonDto(original.get());
    }
}

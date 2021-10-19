package se.lexicon.todo_it_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.todo_it_api.data.PersonDAO;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.exception.AppResourceNotFoundException;
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
    @Transactional
    public PersonDto create(PersonFormDto form) {
        Person saved = personDAO.save(conversionService.toPerson(form));
        return conversionService.toPersonDto(saved);
    }

    @Override
    @Transactional
    public boolean delete(Integer personId) {
        personDAO.deleteById(personId);
        return !personDAO.existsById(personId);
    }

    @Override
    @Transactional
    public List<PersonDto> findAll() {
        List<Person> personList = personDAO.findAll();
        List<PersonDto> personDtoList = new ArrayList<>();
        personList.forEach((p) -> personDtoList.add(conversionService.toPersonDto(p)));
        return personDtoList;
    }

    @Override
    @Transactional
    public PersonDto findById(Integer integer) {
        Optional<Person> foundPerson = personDAO.findById(integer);
        return conversionService.toPersonDto(foundPerson.orElseThrow(() -> new AppResourceNotFoundException("Could not find")));
    }

    @Override
    @Transactional
    public List<PersonDto> findIdlePeople() {
        List<Person> personList = personDAO.findIdlePeople();
        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person person : personList) {
            PersonDto personDto = conversionService.toPersonDto(person);
            personDtoList.add(personDto);
        }
        return personDtoList;
    }

   /* @Override
    public PersonDto addTodoItem(Integer personId, Integer todoItemId) {
        Person foundPerson = personDAO.findById(personId).get();
        TodoItem todoItem = todoItemDAO.findById(todoItemId).get();
        List<TodoItem> todoItems = new ArrayList<>();
        todoItems.add(todoItem);
        foundPerson.setTodoItems(todoItems);
        PersonDto personDto = conversionService.toPersonDto(foundPerson);
        return personDto;
    }*/

    @Override
    @Transactional
    public PersonDto addTodoItem(Integer personId, Integer todoItemId) {
        Optional<Person> person = personDAO.findById(personId);
        Optional<TodoItem> todoItem = todoItemDAO.findById(todoItemId);

        if (todoItem.isPresent() && person.isPresent()) {
            person.get().addTodoItem(todoItem.get());
        }
        return conversionService.toPersonDto(person.get());
    }

    /*@Override
    public PersonDto removeTodoItem(Integer personId, Integer todoItemId) {
        Person foundPerson = personDAO.findById(personId).get();
        TodoItem todoItem = todoItemDAO.findById(todoItemId).get();
        List<TodoItem> todoItems = new ArrayList<>();
        todoItems.remove(todoItem);
        foundPerson.setTodoItems(todoItems);
        PersonDto personDto = conversionService.toPersonDto(foundPerson);
        return personDto;
    }*/

    @Override
    @Transactional
    public PersonDto removeTodoItem(Integer personId, Integer todoItemId) {
        Optional<Person> person = personDAO.findById(personId);
        Optional<TodoItem> todoItem = todoItemDAO.findById(todoItemId);

        if (todoItem.isPresent() && person.isPresent()) {
            person.get().removeTodoItem(todoItem.get());
        }
        return conversionService.toPersonDto(person.get());
    }

    @Override
    @Transactional
    public PersonDto update(Integer personId, PersonFormDto formDto) {
        Optional<Person> original = personDAO.findById(personId);

        if (original.isPresent()) {
            original.get().setFirstName(formDto.getFirstName());
            original.get().setLastName(formDto.getLastName());
            original.get().setBirthDate(formDto.getBirthDate());
            return conversionService.toPersonDto(original.get());
        } else {
            throw new AppResourceNotFoundException("Not found");
        }
    }

    /*@Override
    public PersonDto update(Integer personId, PersonFormDto formDto) {
        Optional<Person> original = personDAO.findById(personId);

        original.ifPresent((p)->{
            p.setFirstName(formDto.getFirstName());
            p.setLastName(formDto.getLastName());
            p.setBirthDate(formDto.getBirthDate());
        });
        return conversionService.toPersonDto(original.get());
    }*/
}

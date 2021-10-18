package se.lexicon.todo_it_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.todo_it_api.dto.PersonDto;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.form.PersonFormDto;
import se.lexicon.todo_it_api.form.TodoItemFormDto;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ConversionServiceTest {

    @Autowired
    private ConversionService conversionService;

    private PersonFormDto personFormDTO;
    private Person person;
    private TodoItemFormDto todoItemFormDTO;
    private TodoItem todoItem;

    @BeforeEach
    void setUp() {

    }

    @Test
    void toPerson() {
        //Arrange
        personFormDTO = new PersonFormDto();
        //Act
        Person convertedPerson= conversionService.toPerson(personFormDTO);

        //Assert
        assertNotNull(convertedPerson);

    }

    @Test
    void toPersonDto() {
        //Arrange
        person = new Person();
        //Act
        PersonDto convertedPersonDto= conversionService.toPersonDto(person);

        //Assert
        assertNotNull(convertedPersonDto);


    }

    @Test
    void toTodoItem() {
        //Arrange
        todoItemFormDTO = new TodoItemFormDto();
        //Act
        TodoItem convertedTodoItem = conversionService.toTodoItem(todoItemFormDTO);

        //Assert
        assertNotNull(convertedTodoItem);
    }

    @Test
    void toTodoItemDto() {
        //Arrange
        todoItem = new TodoItem();
        //Act
        TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);

        //Assert
        assertNotNull(todoItemDto);
    }
}
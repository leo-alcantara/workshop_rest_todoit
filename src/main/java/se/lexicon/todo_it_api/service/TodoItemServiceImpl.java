package se.lexicon.todo_it_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.exception.AppResourceNotFoundException;
import se.lexicon.todo_it_api.form.TodoItemFormDto;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    private final TodoItemDAO todoItemDAO;
    private final ConversionService conversionService;

    @Autowired
    public TodoItemServiceImpl(TodoItemDAO todoItemDAO, ConversionService conversionService) {
        this.todoItemDAO = todoItemDAO;
        this.conversionService = conversionService;
    }

    @Override
    public TodoItemDto create(TodoItemFormDto form) {
        TodoItem saved = todoItemDAO.save(conversionService.toTodoItem(form));
        return conversionService.toTodoItemDto(saved);
    }

    @Override
    public boolean delete(Integer todoId) {
        todoItemDAO.deleteById(todoId);
        return !todoItemDAO.existsById(todoId);
    }

    @Override
    public List<TodoItemDto> findAll() {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findAll();
        for (TodoItem todoItem:todoList) {
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public List<TodoItemDto> findAllByPersonId(Integer personId) {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findByPersonId(personId);
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public List<TodoItemDto> findAllUnassigned() {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findUnassignedTodoItems();
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public List<TodoItemDto> findAllUnfinishedAndOverdue() {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findAllUnfinishedAndOverdue();
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public List<TodoItemDto> findByDeadlineAfter(LocalDate date) {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findByDeadlineAfter(date);
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public List<TodoItemDto> findByDeadlineBefore(LocalDate date) {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findByDeadLineBefore(date);
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public List<TodoItemDto> findByDeadlineBetween(LocalDate start, LocalDate end) {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findByDeadlineBetween(start, end);
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public List<TodoItemDto> findByDoneStatus(boolean done) {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findByDoneStatus(done);
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    public TodoItemDto findById(Integer todoId) {
        Optional<TodoItem> foundTodo = todoItemDAO.findById(todoId);
        return conversionService.toTodoItemDto(foundTodo.get());
    }

    @Override
    public List<TodoItemDto> findByTitle(String title) {
        List<TodoItem> todoList = new ArrayList<>();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList = todoItemDAO.findByTitleContains(title);
        for (TodoItem todoItem: todoList){
            TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
            todoItemDtoList.add(todoItemDto);
        }
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public TodoItemDto update(Integer todoId, TodoItemFormDto form) {
        Optional<TodoItem> original = todoItemDAO.findById(todoId);

        if(original.isPresent()){
            original.get().setTitle(form.getTitle());
            original.get().setDescription(form.getDescription());
            original.get().setDeadLine(form.getDeadLine());
            original.get().setDone(form.isDone());
            return conversionService.toTodoItemDto(original.get());
        } else {
            throw new AppResourceNotFoundException("Not found");
        }
    }
}

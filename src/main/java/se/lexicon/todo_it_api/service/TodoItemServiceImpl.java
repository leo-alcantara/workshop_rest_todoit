package se.lexicon.todo_it_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.exception.AppResourceNotFoundException;
import se.lexicon.todo_it_api.form.TodoItemFormDto;
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
    @Transactional
    public TodoItemDto create(TodoItemFormDto form) {
        TodoItem saved = todoItemDAO.save(conversionService.toTodoItem(form));
        return conversionService.toTodoItemDto(saved);
    }

    @Override
    @Transactional
    public boolean delete(Integer todoId) {
        todoItemDAO.deleteById(todoId);
        return !todoItemDAO.existsById(todoId);
    }

    @Override
    @Transactional
    public List<TodoItemDto> findAll() {
        List<TodoItem> todoList = todoItemDAO.findAll();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        //       for (TodoItem todoItem:todoList) {
        //         TodoItemDto todoItemDto = conversionService.toTodoItemDto(todoItem);
        //       todoItemDtoList.add(todoItemDto);
        // }
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public List<TodoItemDto> findAllByPersonId(Integer personId) {
        List<TodoItem> todoList = todoItemDAO.findByPersonId(personId);
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public List<TodoItemDto> findAllUnassigned() {
        List<TodoItem> todoList = todoItemDAO.findUnassignedTodoItems();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public List<TodoItemDto> findAllUnfinishedAndOverdue() {
        List<TodoItem> todoList = todoItemDAO.findAllUnfinishedAndOverdue();
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public List<TodoItemDto> findByDeadlineAfter(LocalDate date) {
        List<TodoItem> todoList = todoItemDAO.findByDeadlineAfter(date);
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public List<TodoItemDto> findByDeadlineBefore(LocalDate date) {
        List<TodoItem> todoList = todoItemDAO.findByDeadLineBefore(date);
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public List<TodoItemDto> findByDeadlineBetween(LocalDate start, LocalDate end) {
        List<TodoItem> todoList = todoItemDAO.findByDeadlineBetween(start, end);
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public List<TodoItemDto> findByDoneStatus(boolean done) {
        List<TodoItem> todoList = todoItemDAO.findByDoneStatus(done);
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
        return todoItemDtoList;
    }

    @Override
    @Transactional
    public TodoItemDto findById(Integer todoId) {
        Optional<TodoItem> foundTodo = todoItemDAO.findById(todoId);
        return conversionService.toTodoItemDto(foundTodo.orElseThrow(() ->
                new AppResourceNotFoundException("Can not find todo item with the ID.")));
    }

    @Override
    @Transactional
    public List<TodoItemDto> findByTitle(String title) {
        List<TodoItem> todoList = todoItemDAO.findByTitleContains(title);
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();
        todoList.forEach((todo) -> todoItemDtoList.add(conversionService.toTodoItemDto(todo)));
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

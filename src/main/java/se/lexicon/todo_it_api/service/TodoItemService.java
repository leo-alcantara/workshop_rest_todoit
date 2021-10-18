package se.lexicon.todo_it_api.service;

import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.form.TodoItemFormDto;

import java.time.LocalDate;
import java.util.List;

public interface TodoItemService {

    TodoItemDto create(TodoItemFormDto form);
    boolean delete(Integer todoId);
    List<TodoItemDto> findAll();
    List<TodoItemDto> findAllByPersonId(Integer personId);
    List<TodoItemDto> findAllUnassigned();
    List<TodoItemDto> findAllUnfinishedAndOverdue();
    List<TodoItemDto> findByDeadlineAfter(LocalDate date);
    List<TodoItemDto> findByDeadlineBefore(LocalDate date);
    List<TodoItemDto> findByDeadlineBetween(LocalDate start, LocalDate end);
    List<TodoItemDto> findByDoneStatus (boolean done);
    TodoItemDto findById(Integer todoId);
    List<TodoItemDto> findByTitle(String title);
    TodoItemDto update(Integer todoId, TodoItemFormDto form);

}

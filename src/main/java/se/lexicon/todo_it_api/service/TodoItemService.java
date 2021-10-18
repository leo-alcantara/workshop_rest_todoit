package se.lexicon.todo_it_api.service;

import se.lexicon.todo_it_api.dto.TodoItemDto;
import se.lexicon.todo_it_api.form.TodoItemFormDto;

import java.time.LocalDate;
import java.util.List;

public interface TodoItemService {

    TodoItemDto create(TodoItemFormDto);
    boolean delete(Integer todoId);
    List<TodoItemDto> findAll();
    List<TodoItemDto> findAllByPersonId(Integer personId);
    List<TodoItemDto> findAllUnassigned();
    List<TodoItemDto> findAllUnfinishedAndOverdue();
    List<TodoItemDto> findByDeadlineAfter(LocalDate date);
    List<TodoItemDto> findByDeadlineBefore(LocalDate date);
    List<TodoItemDto> findByDeadlineBetween(LocalDate date);
    List<TodoItemDto> findByDoneStatus (boolean done);

}

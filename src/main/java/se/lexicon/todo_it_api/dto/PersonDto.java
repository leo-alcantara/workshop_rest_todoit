package se.lexicon.todo_it_api.dto;

import lombok.*;
import se.lexicon.todo_it_api.model.entity.TodoItem;


import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(exclude = {"todoItems"})
@ToString(exclude = "todoItems")
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private Integer personId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<TodoItem> todoItems;

}

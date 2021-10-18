package se.lexicon.todo_it_api.form;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItemFormDto {

    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;

    public TodoItemFormDto() {
    }

    public TodoItemFormDto(String title, String description, LocalDate deadLine, boolean done) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemFormDto that = (TodoItemFormDto) o;
        return isDone() == that.isDone() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDeadLine(), that.getDeadLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getDeadLine(), isDone());
    }

    @Override
    public String toString() {
        return "TodoItemFormDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }
}

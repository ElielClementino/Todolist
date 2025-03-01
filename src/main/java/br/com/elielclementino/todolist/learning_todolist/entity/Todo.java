package br.com.elielclementino.todolist.learning_todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todos")
@Getter
@Setter
@NoArgsConstructor
public class Todo {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Boolean accomplished;
    @NotNull
    private int priority;

    public Todo(String name, String description, Boolean accomplished, int priority) {
        this.name = name;
        this.description = description;
        this.accomplished = accomplished;
        this.priority = priority;
    }
}

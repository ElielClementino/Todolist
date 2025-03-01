package br.com.elielclementino.todolist.learning_todolist.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoRecordDto (@NotBlank String name, String description, @NotNull Boolean accomplished, @NotNull int priority){}

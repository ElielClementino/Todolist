package br.com.elielclementino.todolist.learning_todolist.service;

import org.springframework.stereotype.Service;

import br.com.elielclementino.todolist.learning_todolist.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
}

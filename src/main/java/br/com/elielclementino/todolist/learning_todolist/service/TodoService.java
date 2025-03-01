package br.com.elielclementino.todolist.learning_todolist.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.elielclementino.todolist.learning_todolist.entity.Todo;
import br.com.elielclementino.todolist.learning_todolist.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> listTodo() {
        Sort sort = Sort.by("priority").ascending().and(
            Sort.by("name").ascending()
        );

        return todoRepository.findAll(sort);
    }
}

package br.com.elielclementino.todolist.learning_todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.elielclementino.todolist.learning_todolist.dtos.TodoRecordDto;
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

    public List<Todo> createTodo(TodoRecordDto todoRecordDto) {
        Todo todo = new Todo(todoRecordDto.name(), todoRecordDto.description(), todoRecordDto.accomplished(), todoRecordDto.priority());
        todoRepository.save(todo);

        return listTodo();
    }

    public List<Todo> updateTodo(Long id, TodoRecordDto todoRecordDto) {
        Optional<Todo> todoToUpdate = todoRepository.findById(id);

        if(todoToUpdate.isEmpty()) {
            throw new NullPointerException("Todo not found");
        }

        var todo = todoToUpdate.get();
        todo.setName(todoRecordDto.name());
        todo.setDescription(todoRecordDto.description());
        todo.setAccomplished(todoRecordDto.accomplished());
        todo.setPriority(todoRecordDto.priority());

        todoRepository.save(todo);

        return listTodo();
    }
}

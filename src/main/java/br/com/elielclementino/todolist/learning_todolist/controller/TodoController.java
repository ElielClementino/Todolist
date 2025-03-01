package br.com.elielclementino.todolist.learning_todolist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elielclementino.todolist.learning_todolist.dtos.ErrorResponseDto;
import br.com.elielclementino.todolist.learning_todolist.entity.Todo;
import br.com.elielclementino.todolist.learning_todolist.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping
    public ResponseEntity<Object> listTodo() {
        try {
            List<Todo> todos = todoService.listTodo();

            return ResponseEntity.status(HttpStatus.OK).body(todos);
        } catch(Exception e) {
            e.printStackTrace();
            var errResponse = new ErrorResponseDto("An error occurred while processing your solicitation: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResponse);
        }
    }
}

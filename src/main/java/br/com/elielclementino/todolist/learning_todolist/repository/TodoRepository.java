package br.com.elielclementino.todolist.learning_todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elielclementino.todolist.learning_todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {}

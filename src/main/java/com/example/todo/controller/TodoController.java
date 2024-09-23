package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.addTodo(todo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody String description) {
        return new ResponseEntity<>(todoService.updateTodo(id, description), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> toggleCompletion(@PathVariable Long id) {
        Todo updatedTodo = todoService.toggleCompletion(id);
        return ResponseEntity.ok(updatedTodo);
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadTodos(@RequestBody List<Todo> todos) {
        todoService.uploadTodos(todos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
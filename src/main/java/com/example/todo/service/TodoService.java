package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo updateTodo(Long id, String description) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setDescription(description);
        return todoRepository.save(todo);
    }

    public Todo toggleCompletion(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Дело с ID " + id + " не найдено."));
        todo.setCompleted(!todo.isCompleted());

        return todoRepository.save(todo);
    }

    public void uploadTodos(List<Todo> todos) {
        todoRepository.deleteAll();
        todoRepository.saveAll(todos);
    }
}
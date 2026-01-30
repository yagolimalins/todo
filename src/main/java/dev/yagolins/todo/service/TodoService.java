package dev.yagolins.todo.service;

import dev.yagolins.todo.entity.Todo;
import dev.yagolins.todo.exception.TodoNotFoundException;
import dev.yagolins.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo create(Todo todo) {
        return repository.save(todo);
    }

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Todo findById(UUID id) {
        Todo todo = repository.findById(id);
        if (todo == null) {
            throw new TodoNotFoundException("Tarefa não encontrada");
        }
        return todo;
    }

    public Todo updateTitle(UUID id, String newTitle) {
        Todo todo = findById(id);

        todo.setTitle(newTitle);

        return repository.save(todo);
    }

    public Todo toggleCompleted(UUID id) {
        Todo todo = findById(id);
        if (todo == null) {
            throw new TodoNotFoundException("Tarefa não encontrada");
        }
        todo.setCompleted(!todo.isCompleted());
        return repository.save(todo);
    }

    public void delete(UUID id) {
        Todo todo = findById(id);
        if (todo == null) {
            throw new TodoNotFoundException("Tarefa não encontrada");
        }
        repository.delete(todo);
    }
}

package dev.yagolins.todo.controller;

import dev.yagolins.todo.docs.TodoControllerDocs;
import dev.yagolins.todo.dto.TodoRequest;
import dev.yagolins.todo.entity.Todo;
import dev.yagolins.todo.service.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/todos")
@Tag(name = "Tarefas", description = "API para gerenciamento de tarefas (Todo)")
public class TodoController implements TodoControllerDocs {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody TodoRequest request) {
        Todo todo = new Todo(request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(todo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Todo> toggleStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(service.toggleCompleted(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

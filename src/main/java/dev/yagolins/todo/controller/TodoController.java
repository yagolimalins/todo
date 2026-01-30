package dev.yagolins.todo.controller;

import dev.yagolins.todo.docs.TodoControllerDocs;
import dev.yagolins.todo.dto.request.TodoRequest;
import dev.yagolins.todo.dto.request.UpdateTodoTitleRequest;
import dev.yagolins.todo.entity.Todo;
import dev.yagolins.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/todos")
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

    @PatchMapping("/{id}/title")
    public ResponseEntity<Todo> updateTitle(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTodoTitleRequest request
    ) {
        return ResponseEntity.ok(service.updateTitle(id, request.getTitle()));
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

package dev.yagolins.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue
    private UUID id;

    public Todo(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    private String title;

    private boolean completed = false;

}

package dev.yagolins.todo.repository;

import dev.yagolins.todo.entity.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TodoRepository {

    @PersistenceContext
    private EntityManager em;

    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            em.persist(todo);
            return todo;
        }
        return em.merge(todo);
    }

    public Todo findById(UUID id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return em.createQuery("from Todo", Todo.class).getResultList();
    }

    public void delete(Todo todo) {
        em.remove(todo);
    }
}

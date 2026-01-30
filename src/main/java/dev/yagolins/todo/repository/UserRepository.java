package dev.yagolins.todo.repository;

import dev.yagolins.todo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<User> findByUsername(String username) {
        List<User> users = em.createQuery(
                        "SELECT u FROM User u WHERE u.username = :username",
                        User.class
                ).setParameter("username", username)
                .getResultList();

        return users.stream().findFirst();
    }

    public boolean existsByUsername(String username) {
        Long count = em.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.username = :username",
                        Long.class
                ).setParameter("username", username)
                .getSingleResult();

        return count > 0;
    }

    public void save(User user) {
        em.persist(user);
    }
}


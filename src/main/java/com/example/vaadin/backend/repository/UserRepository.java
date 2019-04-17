package com.example.vaadin.backend.repository;

import com.example.vaadin.backend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository {

    private static Set<User> users;

    static {
        users = new HashSet<>();
        users.add(new User(1, "Bruce", 40));
        users.add(new User(2, "Artur", 35));
        users.add(new User(3, "Diana", 200));
        users.add(new User(4, "Clark", 33));
        users.add(new User(5, "Barry", 25));
        users.add(new User(6, "Victor", 27));
        users.add(new User(7, "Alfred", 60));
        users.add(new User(8, "Jim", 60));
    }

    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Set<User> findAll() {
        return new HashSet<>(users);
    }

    public User save(User user) {
        User newUser;

        if (user.getId() == 0) {
            int id = users.stream().map(User::getId).max(Integer::compareTo).get();
            newUser = new User(++id, user.getName(), user.getAge());
        } else {
            newUser = new User(user.getName(), user.getAge());
        }

        users.add(newUser);
        return newUser;
    }
}

package ru.netology.autorizservice.repository;
import org.springframework.stereotype.Repository;
import ru.netology.autorizservice.model.Authorities;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, String> users = new ConcurrentHashMap<>();
    private final Map<String, List<Authorities>> authorities = new ConcurrentHashMap<>();

    public UserRepository() {
        // Инициализация тестовых пользователей
        users.put("admin", "admin123");
        users.put("user", "user123");

        authorities.put("admin", Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE));
        authorities.put("user", Arrays.asList(Authorities.READ));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (users.containsKey(user)) {
            if (users.get(user).equals(password)) {
                return authorities.get(user);
            }
        }
        return new ArrayList<>();
    }
}
package service.user;

import model.Role;
import model.User;
import model.validation.Notification;

import java.util.List;

public interface UserService {

    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password);

    Notification<Boolean> remove(String username);

    boolean update(String username, String password);

    Notification<User> findById(Long id);

    Notification<User> findByName(String username);

    List<User> findAll();

    Role findRoleByTitle(String role);

    boolean logout(User user);

}

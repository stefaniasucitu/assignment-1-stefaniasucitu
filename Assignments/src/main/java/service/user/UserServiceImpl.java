package service.user;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.EMPLOYEE;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;


    public UserServiceImpl(UserRepository repository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = repository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Notification<Boolean> register(String username, String password) {
        Role employeeRole = rightsRolesRepository.findRolebyTitle(EMPLOYEE);
        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();

        UserValidator userValidator = new UserValidator(user);
        boolean userValid = userValidator.validate();
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!userValid) {
            userValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
            return userRegisterNotification;
        } else {
            user.setPassword(encodePassword(password));
            userRegisterNotification.setResult(userRepository.save(user));
            return userRegisterNotification;
        }
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Notification<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, encodePassword(password));
    }

    @Override
    public Notification<Boolean> remove(String username) {
        User user = userRepository.findByName(username).getResult();
        Notification<Boolean> removeUser = new Notification<>();
        removeUser.setResult(userRepository.remove(user));
        return removeUser;
    }

    @Override
    public boolean update(String username, String password) {
        User user = userRepository.findByName(username).getResult();
        user.setPassword(encodePassword(password));
        return userRepository.update(user);
    }

    @Override
    public Notification<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Notification<User> findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Role findRoleByTitle(String Title) {
        if (Title.equals("Employee")) {
            return rightsRolesRepository.findRolebyTitle(EMPLOYEE);
        } else return rightsRolesRepository.findRolebyTitle(ADMINISTRATOR);
    }

    @Override
    public boolean logout(User user) {
        return false;
    }

}

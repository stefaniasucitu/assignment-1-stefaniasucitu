package repository;

/**
 * Created by Stefi on 30-Mar-17.
 */


import database.DBConnectionFactory;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.UserValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySql;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySql;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;

public class UserRepositoryTest {
    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;

    @Before
    public void setUp() throws Exception {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        rightsRolesRepository = new RightsRolesRepositoryMySql(connection);
        userRepository = new UserRepositoryMySql(connection, rightsRolesRepository);

    }


    @Test
    public void findAll() throws Exception {
        List<User> users = userRepository.findAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void findById() throws Exception {
        String username = "FindById@Username.com";
        String password = "Pass!";
        Role employeeRole = rightsRolesRepository.findRolebyTitle("Employee");
        User userTest = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();
        userRepository.save(userTest);
        Long idToFind = userTest.getId();

        User user = userRepository.findById(idToFind).getResult();
        Assert.assertNotNull(user);
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {
        String username = "FindByPass@Username.com";
        String password = "Pass!";
        Role employeeRole = rightsRolesRepository.findRolebyTitle("Employee");
        User userTest = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();
        userRepository.save(userTest);

        User user = userRepository.findByUsernameAndPassword(username, password).getResult();
        Assert.assertNotNull(user);
    }

    @Test
    public void findByIdAndPassword() throws Exception {
        String username = "Test@Username.com";
        String password = "TestPassword1!";
        Role employeeRole = rightsRolesRepository.findRolebyTitle("Employee");
        User userTest = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();
        userRepository.save(userTest);

        User user = userRepository.findByUsernameAndPassword(username, password).getResult();

        Assert.assertNotNull(user);
    }

    @Test
    public void save() throws Exception {

        String username = "TestSAve@Username.com";
        String password = "TestPassword1!";
        Role employeeRole = rightsRolesRepository.findRolebyTitle(EMPLOYEE);

        User userTest = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();

        UserValidator userValidator = new UserValidator(userTest);
        boolean userValid = userValidator.validate();
        if (userValid) {
            Assert.assertTrue(userRepository.save(userTest));
        }

    }

    @Test
    public void update() throws Exception {

        String username = "TestSAve265@Username.com";
        String password = "TestPassword1264!";
        Role employeeRole = rightsRolesRepository.findRolebyTitle(EMPLOYEE);
        User userTest = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();

        userRepository.save(userTest);

        String newPassword = "newPassword1@";
        userTest.setPassword(newPassword);

        Assert.assertTrue(userRepository.update(userTest));

    }

    @Test
    public void remove() throws Exception {

        String username = "ToDelete@Username.com";
        String password = "TestPassword1264!";
        Role employeeRole = rightsRolesRepository.findRolebyTitle(EMPLOYEE);
        User userTest = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();

        userRepository.save(userTest);

        Assert.assertTrue(userRepository.remove(userTest));

    }

    @Test
    public void removeAll() throws Exception {

        userRepository.removeAll();
        List<User> user = userRepository.findAll();
        Assert.assertTrue(user.isEmpty());
    }

}



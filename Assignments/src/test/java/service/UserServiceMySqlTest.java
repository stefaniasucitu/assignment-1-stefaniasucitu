package service;

import database.DBConnectionFactory;
import model.Role;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySql;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySql;
import service.user.UserService;
import service.user.UserServiceImpl;

import java.sql.Connection;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;

public class UserServiceMySqlTest {

    private static UserService userService;
    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;

    @Before
    public void setUp() throws Exception {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        rightsRolesRepository = new RightsRolesRepositoryMySql(connection);
        userRepository = new UserRepositoryMySql(connection, rightsRolesRepository);
        userService = new UserServiceImpl(userRepository, rightsRolesRepository);
    }

    /*@BeforeClass
    public static void tearDown() throws Exception {
		userRepository.removeAll();
	}*/

    @Test
    public void register() throws Exception {
        Assert.assertTrue(
                userService.register("Test2656@Username.com", "TestPassword1!").getResult()
        );

    }

    @Test
    public void login() throws Exception {
        String username = "Test@Username.com";
        String password = "TestPassword1!";
        userService.register(username, password);

        User user = userService.login(username, password).getResult();

        Assert.assertNotNull(user);
    }

    @Test
    public void remove() throws Exception {
        Assert.assertTrue(
                userService.remove("Test@Username.com").getResult()
        );
    }

    @Test
    public void update() throws Exception {
        Assert.assertTrue(
                userService.update("Test@Username.com", "TestPassword258!")
        );
    }

    @Test
    public void findById() throws Exception {
        User user = userService.findById(Long.valueOf(20)).getResult();

        Assert.assertNotNull(user);

    }

    @Test
    public void findByName() throws Exception {
        User users = userService.findByName("Test@Username.com").getResult();

        Assert.assertNotNull(users);

    }

    @Test
    public void findAll() throws Exception {
        List<User> users = userService.findAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void findRoleByTitle() throws Exception {
        Role role = userService.findRoleByTitle("Employee");
        boolean match = role.getRole().equals(EMPLOYEE);
        Assert.assertTrue(match);
    }

    @Test
    public void logout() throws Exception {

    }


}

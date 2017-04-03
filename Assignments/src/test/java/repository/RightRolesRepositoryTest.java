package repository;

import database.DBConnectionFactory;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySql;

import java.sql.Connection;
import java.util.Collections;

/**
 * Created by Stefi on 03-Apr-17.
 */
public class RightRolesRepositoryTest {
    private RightsRolesRepository rightsRolesRepository;

    public void setUp() throws Exception {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        rightsRolesRepository = new RightsRolesRepositoryMySql(connection);
    }

    @Test
    public void addRole() throws Exception {
        Assert.assertTrue(rightsRolesRepository.addRole("EmployeeOfTheYear"));
    }

    @Test
    public void addRight() throws Exception {
        Assert.assertTrue(rightsRolesRepository.addRight("Delete_Right"));
    }

    @Test
    public void findRoleByTitle() throws Exception {
        Assert.assertNotNull(rightsRolesRepository.findRolebyTitle("employee"));
    }

    @Test
    public void findRoleById() throws Exception {
        Assert.assertNotNull(rightsRolesRepository.findRolebyId(1L));
    }

    @Test
    public void findRightByTitle() throws Exception {
        Assert.assertNotNull(rightsRolesRepository.findRightByTitle("delete_user"));
    }

    @Test
    public void addRolesToUser() throws Exception {
        String username = "Test@Username.com";
        String password = "TestPassword1!";
        Role employeeRole = rightsRolesRepository.findRolebyTitle("Employee");
        User userTest = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();
        rightsRolesRepository.addRolesToUser(userTest, Collections.singletonList(employeeRole));
    }

    @Test
    public void findRolesForUser() throws Exception {

        Assert.assertNotNull(rightsRolesRepository.findRolesForUser(1L));
    }

    @Test
    public void addRoleRight() throws Exception {
        rightsRolesRepository.addRoleRight(1L, 3L);
    }


}




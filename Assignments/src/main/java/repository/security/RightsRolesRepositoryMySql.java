package repository.security;

import model.Right;
import model.Role;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.*;

public class RightsRolesRepositoryMySql implements RightsRolesRepository {

    private final Connection connection;

    public RightsRolesRepositoryMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addRole(String role) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO" + ROLE + " values (null,?");
            insertStatement.setString(1, role);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addRight(String right) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO" + RIGHT + " values (null,?");
            insertStatement.setString(1, right);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Role findRolebyTitle(String role) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String fetchRole = " SELECT * FROM bank.role WHERE " + ROLE + "= '" + role + "\'";
            ResultSet roleResultSet = statement.executeQuery(fetchRole);
            roleResultSet.next();
            Long roleId = roleResultSet.getLong("id");
            String roleTile = roleResultSet.getString("role");
            return new Role(roleId, roleTile, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role findRolebyId(Long roleId) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String fetchRole = "Select * from " + ROLE + " where `id`=\'" + roleId + "\'";
            ResultSet roleResultSet = statement.executeQuery(fetchRole);
            roleResultSet.next();
            String roleTile = roleResultSet.getString("role");
            return new Role(roleId, roleTile, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Right findRightByTitle(String right) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String fetchRight = "Select * from " + RIGHT + " where `right`=\'" + right + "\'";
            ResultSet rightResultSet = statement.executeQuery(fetchRight);
            rightResultSet.next();
            Long rightId = rightResultSet.getLong("id");
            String rightTile = rightResultSet.getString("role");
            return new Right(rightId, rightTile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addRolesToUser(User user, List<Role> roles) {
        try {
            for (Role role : roles) {
                PreparedStatement insertUserRoleStatement = connection
                        .prepareStatement("INSERT INTO `user_role` values (null,?,?)");
                insertUserRoleStatement.setLong(1, user.getId());
                insertUserRoleStatement.setLong(2, role.getId());
                insertUserRoleStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Role> findRolesForUser(Long userId) {
        try {
            List<Role> roles = new ArrayList<>();
            Statement statement = connection.createStatement();
            String fetchRole = "SELECT * FROM bank.user_role where user_id=" + userId;
            ResultSet userRoleResultSet = statement.executeQuery(fetchRole);
            while (userRoleResultSet.next()) {
                long roleId = userRoleResultSet.getLong("role_id");
                roles.add(findRolebyId(roleId));
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addRoleRight(Long roleId, Long rightId) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO" + ROLE_RIGHT + " values (null,?,?)");
            insertStatement.setLong(1, roleId);
            insertStatement.setLong(2, rightId);
            insertStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

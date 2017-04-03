package repository.user;

import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

public class UserRepositoryMySql implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;

    public UserRepositoryMySql(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                users.add(new UserBuilder().setUsername(rs.getString("username")).setPassword(rs.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(rs.getLong("id"))).build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    @Override
    public Notification<User> findById(Long id) {
        Notification<User> findByIdNotification = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetchUser = "Select * from `" + USER + "` where `id`=\'" + id + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUser);
            userResultSet.next();

            User user = new UserBuilder().setUsername(userResultSet.getString("username"))
                    .setPassword(userResultSet.getString("password"))
                    .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id"))).build();

            findByIdNotification.setResult(user);
            return findByIdNotification;
        } catch (SQLException e) {
            e.printStackTrace();
            findByIdNotification.addError("Invalid email or password!");
            return findByIdNotification;
        }
    }

    @Override
    public Notification<User> findByName(String name) {
        Notification<User> findByUsername = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetchUser = "Select * from `" + USER + "` where `username`=\'" + name + "\'";


            ResultSet userResultSet = statement.executeQuery(fetchUser);
            userResultSet.next();

            User user = new UserBuilder()
                    .setUsername(userResultSet.getString("username"))
                    .setPassword(userResultSet.getString("password"))
                    .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id"))).build();
            user.setId((long) userResultSet.getFloat("id"));
            findByUsername.setResult(user);
            return findByUsername;
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsername.addError("Not Found");
            return findByUsername;
        }
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetchUser = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'"
                    + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUser);
            userResultSet.next();

            User user = new UserBuilder().setUsername(userResultSet.getString("username"))
                    .setPassword(userResultSet.getString("password"))
                    .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id"))).build();

            findByUsernameAndPasswordNotification.setResult(user);
            return findByUsernameAndPasswordNotification;
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Not Found!");
            return findByUsernameAndPasswordNotification;
        }

    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection.prepareStatement("INSERT INTO user values (null,?,?)");
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet result = insertUserStatement.getGeneratedKeys();
            result.next();
            long userId = result.getLong(1);
            user.setId(userId);
            rightsRolesRepository.addRolesToUser(user, user.getRoles());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id>=0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public boolean update(User user) {
        try {
            PreparedStatement updateUserStatement = connection
                    .prepareStatement("UPDATE user SET password=? WHERE id=? ");
            updateUserStatement.setString(1, user.getPassword());
            updateUserStatement.setFloat(2, user.getId());
            updateUserStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(User user) {
        try {
            Statement statement = connection.createStatement();
            String sql = "delete FROM bank.user where id=" + user.getId();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


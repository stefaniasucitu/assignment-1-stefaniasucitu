package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySql implements AccountRepository {
    private final Connection connection;

    public AccountRepositoryMySql(Connection connection) {
        this.connection = connection;

    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from bank.account";
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                accounts.add(new AccountBuilder().setType(result.getString("type")).setMoney(result.getFloat("money"))
                        .setDateOfCreation(new DateTime(result.getDate("dateOfCreation"))).build());
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO bank.account values (null,?,?,?)");
            insertStatement.setString(1, account.getType());
            insertStatement.setFloat(2, account.getMoney());
            insertStatement.setDate(3, new java.sql.Date(account.getDateOfCreation().toDate().getTime()));
            insertStatement.executeUpdate();

            ResultSet result = insertStatement.getGeneratedKeys();
            result.next();
            long accountId = result.getLong(1);
            account.setId(accountId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        try {
            PreparedStatement updateAccountStatement = connection
                    .prepareStatement("UPDATE bank.account SET money=? WHERE id=? ");
            updateAccountStatement.setFloat(1, account.getMoney());
            updateAccountStatement.setLong(2, account.getId());
            updateAccountStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeById(Account account) {
        try {
            PreparedStatement removeStatement = connection.prepareStatement("Delete FROM bank.account where id=?");
            removeStatement.setLong(1, account.getId());
            removeStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id>=0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public Notification<Account> findById(Long id) {
        Notification<Account> findByid = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetch = "Select * from bank.account where `id`=\'" + id + "\'";
            ResultSet ResultSet = statement.executeQuery(fetch);
            ResultSet.next();

            Account account = new AccountBuilder()
                    .setId(ResultSet.getLong("id"))
                    .setType(ResultSet.getString("type"))
                    .setMoney(ResultSet.getFloat("money"))
                    .setDateOfCreation(new DateTime(ResultSet.getDate("dateOfCreation"))).build();

            findByid.setResult(account);
            return findByid;
        } catch (SQLException e) {
            e.printStackTrace();
            findByid.addError("Entity not found");
            return findByid;
        }
    }


}

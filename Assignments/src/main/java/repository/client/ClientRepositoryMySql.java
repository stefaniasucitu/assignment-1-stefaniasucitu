package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.accountsManagement.ClientAccountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;

public class ClientRepositoryMySql implements ClientRepository {
    private final Connection connection;
    private final ClientAccountRepository clientAccountRepository;

    public ClientRepositoryMySql(Connection connection, ClientAccountRepository clientAccountRepository) {
        this.connection = connection;
        this.clientAccountRepository = clientAccountRepository;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                clients.add(new ClientBuilder().setFirstName(result.getString("firstName"))
                        .setLastName(result.getString("lastName")).setIdCard(result.getString("idCard"))
                        .setCNP(result.getString("cnp")).setAddress(result.getString("address"))
                        .setID(result.getLong("id"))
                        .setAccounts(clientAccountRepository.findAccountsforClient(result.getLong("id")))
                        .build());
                return clients;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null,?,?,?,?,?)");
            insertStatement.setString(1, client.getFirstName());
            insertStatement.setString(2, client.getLastName());
            insertStatement.setString(3, client.getIdCard());
            insertStatement.setString(4, client.getCNP());
            insertStatement.setString(5, client.getAddress());
            insertStatement.executeUpdate();

            ResultSet result = insertStatement.getGeneratedKeys();
            result.next();
            long clientID = result.getLong(1);
            client.setId(clientID);
            clientAccountRepository.addAccountToClient(client, client.getAccounts());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        try {
            PreparedStatement updateClientStatement = connection
                    .prepareStatement("UPDATE bank.client SET address=? WHERE id=? ");
            updateClientStatement.setString(1, client.getAddress());
            updateClientStatement.setLong(2, client.getId());
            updateClientStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeById(Client client) {
        try {
            PreparedStatement removeStatement = connection.prepareStatement("DELETE from client where id=?");
            removeStatement.setLong(1, client.getId());
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
            String sql = "DELETE from client where id>=0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public Notification<Client> findById(Long id) {
        Notification<Client> findByid = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetch = "Select * from `" + CLIENT + "` where `id`=\'" + id + "\'";
            ResultSet ResultSet = statement.executeQuery(fetch);
            ResultSet.next();

            Client client = new ClientBuilder().setFirstName(ResultSet.getString("firstName"))
                    .setLastName(ResultSet.getString("lastName")).setIdCard(ResultSet.getString("idCard"))
                    .setCNP(ResultSet.getString("cnp")).setAddress(ResultSet.getString("address"))
                    .setID(ResultSet.getLong("id"))
                    .setAccounts(clientAccountRepository.findAccountsforClient(ResultSet.getLong("id")))
                    .build();

            findByid.setResult(client);
            return findByid;
        } catch (SQLException e) {
            e.printStackTrace();
            findByid.addError("Entity not found");
            return findByid;
        }
    }

    @Override
    public Notification<Client> findByName(String first, String last) {
        Notification<Client> findByName = new Notification<>();

        try {
            Statement statement = connection.createStatement();

            String fetch = "Select * from `" + CLIENT + "` where `firstName`=\'" + first + "\' and `lastName`=\'"
                    + last + "\'";
            ResultSet ResultSet = statement.executeQuery(fetch);
            ResultSet.next();

            Client client = new ClientBuilder().setFirstName(ResultSet.getString("firstName"))
                    .setLastName(ResultSet.getString("lastName")).setIdCard(ResultSet.getString("idCard"))
                    .setCNP(ResultSet.getString("cnp")).setAddress(ResultSet.getString("address"))
                    .setID(ResultSet.getLong("id"))
                    .setAccounts(clientAccountRepository.findAccountsforClient(ResultSet.getLong("id")))
                    .build();

            findByName.setResult(client);
            return findByName;
        } catch (SQLException e) {
            e.printStackTrace();
            findByName.addError("Entity not found");
            return findByName;
        }
    }

}

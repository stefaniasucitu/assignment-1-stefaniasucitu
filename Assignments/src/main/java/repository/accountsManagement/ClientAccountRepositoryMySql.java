package repository.accountsManagement;


import model.Account;
import model.Client;
import repository.account.AccountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientAccountRepositoryMySql implements ClientAccountRepository {
    private final Connection connection;
    private final AccountRepository accountRepository;

    public ClientAccountRepositoryMySql(Connection connection, AccountRepository accountRepository) {

        this.connection = connection;
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean addAccountToClient(Client client, List<Account> accounts) {
        try {
            for (Account account : accounts) {
                PreparedStatement insertClientAccount = connection.prepareStatement(
                        "INSERT INTO `bank`.`client_account` VALUES (null, ?, ?);");
                insertClientAccount.setLong(1, client.getId());
                insertClientAccount.setLong(2, account.getId());
                insertClientAccount.executeUpdate();
                return true;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeAccountForClient(Account account) {

        try {
            PreparedStatement deleteClientAccount = connection.prepareStatement("DELETE FROM `bank`.`client_account` where idaccount=?;");
            deleteClientAccount.setLong(1, account.getId());
            deleteClientAccount.executeUpdate();
            return true;
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return false;
    }

    @Override
    public List<Account> findAccountsforClient(Long clientID) {
        try {
            List<Account> accounts = new ArrayList<>();
            Statement statement = connection.createStatement();
            String getAccounts = "Select * from`bank`.`client_account` where idclient=" + clientID;
            ResultSet result = statement.executeQuery(getAccounts);
            while (result.next()) {
                long accountId = result.getLong("idaccount");
                accounts.add(accountRepository.findById(accountId).getResult());
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

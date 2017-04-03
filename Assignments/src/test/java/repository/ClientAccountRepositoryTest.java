package repository;

import database.DBConnectionFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySql;
import repository.accountsManagement.ClientAccountRepository;
import repository.accountsManagement.ClientAccountRepositoryMySql;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySql;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stefi on 03-Apr-17.
 */
public class ClientAccountRepositoryTest {
    private static ClientRepository clientRepository;
    private static ClientAccountRepository clientAccountRepository;
    private static AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository = new ClientRepositoryMySql(connection, clientAccountRepository);
        accountRepository = new AccountRepositoryMySql(connection);
        clientAccountRepository = new ClientAccountRepositoryMySql(connection, accountRepository);
    }

    @Test
    public void addAccountToClient() throws Exception {
        String firstName = "Preume";
        String lastName = "Nume";
        String CNP = "2950726314003";
        String Card = "1234569874";
        String address = "addrblabla";
        Client clientTest = new ClientBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setCNP(CNP)
                .setAddress(address)
                .setIdCard(Card)
                .build();
        clientRepository.save(clientTest);

        String type = "saving";
        DateTime date = new DateTime();

        Account account = new AccountBuilder()
                .setDateOfCreation(date)
                .setMoney(0)
                .setType(type)
                .build();
        accountRepository.save(account);

        Assert.assertTrue(clientAccountRepository.addAccountToClient(clientTest, Collections.singletonList(account)));

    }

    @Test
    public void removeAccountForClient() throws Exception {
        String type = "saving";
        DateTime date = new DateTime();
        Account account = new AccountBuilder()
                .setDateOfCreation(date)
                .setMoney(0)
                .setType(type)
                .build();
        accountRepository.save(account);

        Assert.assertTrue(clientAccountRepository.removeAccountForClient(account));
    }

    @Test
    public void findAccountsForClient() throws Exception {
        String firstName = "Preume";
        String lastName = "Nume";
        String CNP = "2950726314003";
        String Card = "1234569874";
        String address = "addrblabla";
        Client clientTest = new ClientBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setCNP(CNP)
                .setAddress(address)
                .setIdCard(Card)
                .build();
        clientRepository.save(clientTest);

        List<Account> accounts = clientAccountRepository.findAccountsforClient(clientTest.getId());
        Assert.assertNotNull(accounts);
    }


}

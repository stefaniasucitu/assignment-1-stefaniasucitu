package service;

import database.DBConnectionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySql;
import repository.accountsManagement.ClientAccountRepository;
import repository.accountsManagement.ClientAccountRepositoryMySql;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySql;
import service.client.ClientService;
import service.client.ClientServiceImpl;

import java.sql.Connection;

/**
 * Created by Stefi on 03-Apr-17.
 */
public class ClientServiceMySqlTest {
    private static ClientService clientService;
    private static ClientRepository clientRepository;
    private static ClientAccountRepository clientAccountRepository;
    private static AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository = new ClientRepositoryMySql(connection, clientAccountRepository);
        accountRepository = new AccountRepositoryMySql(connection);
        clientService = new ClientServiceImpl(clientRepository, clientAccountRepository, accountRepository);
        clientAccountRepository = new ClientAccountRepositoryMySql(connection, accountRepository);
    }

    @Test
    public void addClient() throws Exception {
        Assert.assertTrue(clientService.addNewClient(Long.valueOf(1), "Panda", "Pops", "2950726314003", "1234569874", "adresa bla bla").getResult());

    }

    @Test
    public void findAll() throws Exception {
        Assert.assertNotNull(clientService.findAll());
    }

    @Test
    public void findById() throws Exception {
        Assert.assertNotNull(clientService.findById(2L));
    }

    @Test
    public void findByName() throws Exception {
        Assert.assertNotNull(clientService.findByName("Panda", "Pops"));
    }

    @Test
    public void updateClient() throws Exception {
        Assert.assertTrue(clientService.updateClient(1L, "Panda", "Pops", "2950726314003", "1234569877", "my new home").getResult());

    }

    @Test
    public void deleteClient() throws Exception {
        Assert.assertTrue(clientService.deleteClient(2L, "Panda", "Pops", "2950726314003", "1234569877", "my new home").getResult());

    }

    @Test
    public void addAccountForClient() throws Exception {
        Assert.assertTrue(clientService.addAccountForClient(1L, 3L).getResult());
    }
}

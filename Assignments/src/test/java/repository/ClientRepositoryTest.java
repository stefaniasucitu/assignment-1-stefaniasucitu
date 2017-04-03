package repository;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
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
import java.util.List;


/**
 * Created by Stefi on 03-Apr-17.
 */
public class ClientRepositoryTest {
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
    public void findAll() throws Exception {
        List<Client> clients = clientRepository.findAll();
        Assert.assertNotNull(clients);
    }

    @Test
    public void findById() throws Exception {
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


        Client client = clientRepository.findById(clientTest.getId()).getResult();
        Assert.assertNotNull(client);

    }

    @Test
    public void findByName() throws Exception {
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
        Assert.assertNotNull(clientRepository.findByName(firstName, lastName).getResult());

    }

    @Test
    public void save() throws Exception {

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

        ClientValidator clientValidator = new ClientValidator(clientTest);
        boolean clientValid = clientValidator.validate();
        if (clientValid) {
            Assert.assertTrue(clientRepository.save(clientTest));
        }

    }

    @Test
    public void update() throws Exception {

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

        String newAddress = "new home";
        clientTest.setAddress(newAddress);

        Assert.assertTrue(clientRepository.update(clientTest));

    }

    @Test
    public void remove() throws Exception {

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

        Assert.assertTrue(clientRepository.removeById(clientTest));

    }

    @Test
    public void removeAll() throws Exception {
        clientRepository.removeAll();
        List<Client> clients = clientRepository.findAll();
        Assert.assertTrue(clients.isEmpty());
    }

}




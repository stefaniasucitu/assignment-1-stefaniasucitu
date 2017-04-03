package repository;

import database.DBConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySql;
import repository.accountsManagement.ClientAccountRepository;
import repository.accountsManagement.ClientAccountRepositoryMySql;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Stefi on 03-Apr-17.
 */
public class AccountRepositoryTest {
    private static AccountRepository accountRepository;
    private static ClientAccountRepository clientAccountRepository;

    @Before
    public void setUp() throws Exception {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        accountRepository = new AccountRepositoryMySql(connection);
        clientAccountRepository = new ClientAccountRepositoryMySql(connection, accountRepository);
    }

    @Test
    public void findAll() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        Assert.assertNotNull(accounts);
    }

    @Test
    public void findById() throws Exception {
        String type = "saving";
        DateTime date = new DateTime();

        Account account = new AccountBuilder()
                .setDateOfCreation(date)
                .setMoney(0)
                .setType(type)
                .build();

        accountRepository.save(account);

        Assert.assertNotNull(accountRepository.findById(account.getId()).getResult());
    }


    @Test
    public void save() throws Exception {

        String type = "saving";
        DateTime date = new DateTime();

        Account account = new AccountBuilder()
                .setDateOfCreation(date)
                .setMoney(0)
                .setType(type)
                .build();
        Assert.assertTrue(accountRepository.save(account));
    }

    @Test
    public void update() throws Exception {

        String type = "saving";
        DateTime date = new DateTime();

        Account account = new AccountBuilder()
                .setDateOfCreation(date)
                .setMoney(0)
                .setType(type)
                .build();
        accountRepository.save(account);

        account.setMoney(1500);
        Assert.assertTrue(accountRepository.update(account));
    }

    @Test
    public void removeById() throws Exception {

        String type = "saving";
        DateTime date = new DateTime();

        Account account = new AccountBuilder()
                .setDateOfCreation(date)
                .setMoney(0)
                .setType(type)
                .build();
        accountRepository.save(account);

        Assert.assertTrue(accountRepository.removeById(account));
    }

    @Test
    public void removeAll() throws Exception {
        accountRepository.removeAll();
        List<Account> accounts = accountRepository.findAll();
        Assert.assertTrue(accounts.isEmpty());
    }
}

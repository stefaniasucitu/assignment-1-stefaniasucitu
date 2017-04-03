package service;

import database.DBConnectionFactory;
import model.Account;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySql;
import repository.accountsManagement.ClientAccountRepository;
import repository.accountsManagement.ClientAccountRepositoryMySql;
import service.account.AccountService;
import service.account.AccountServiceImpl;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Stefi on 02-Apr-17.
 */
public class AccountServiceMySqlTest {
    private static AccountService accountService;
    private static AccountRepository accountRepository;
    private static ClientAccountRepository clientAccountRepository;

    @Before
    public void setUp() throws Exception {

        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();

        accountRepository = new AccountRepositoryMySql(connection);
        clientAccountRepository = new ClientAccountRepositoryMySql(connection, accountRepository);
        accountService = new AccountServiceImpl(accountRepository, clientAccountRepository);
    }

    @Test
    public void addAccount() throws Exception {
        DateTime currentDate = new DateTime();
        Assert.assertNotNull(accountService.addNewAccount("saving", currentDate).getResult());

    }

    @Test
    public void findAll() throws Exception {
        List<Account> accounts = accountService.findAll();
        Assert.assertNotNull(accounts);
    }

    @Test
    public void findById() throws Exception {
        Account account = accountService.findById(Long.valueOf(3)).getResult();
        Assert.assertNotNull(account);
    }

    @Test
    public void removeAccount() throws Exception {
        Account account = accountService.findById(Long.valueOf(2)).getResult();
        Assert.assertTrue(accountService.removeAccount(account.getId()).getResult());
    }

    @Test
    public void transferMoney() throws Exception {
        Assert.assertTrue(accountService.transferMoney(Long.valueOf(3), Long.valueOf(4), Float.valueOf(200)).getResult());

    }

    @Test
    public void updateAccount() throws Exception {
        Assert.assertTrue(accountService.updateAccount(Long.valueOf(3), Float.valueOf(200), "add").getResult());
    }

    @Test
    public void removeAll() throws Exception {
        accountService.removeAll();
    }
}




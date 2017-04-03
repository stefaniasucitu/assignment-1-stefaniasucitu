import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySql;
import repository.accountsManagement.ClientAccountRepository;
import repository.accountsManagement.ClientAccountRepositoryMySql;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySql;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySql;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySql;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

import java.sql.Connection;

public class ComponentFactory {

    private static ComponentFactory instance;
    private final UserService userService;
    private final AccountService accountService;
    private final ClientService clientService;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final ClientAccountRepository clientAccountRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public ComponentFactory() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySql(connection);
        this.userRepository = new UserRepositoryMySql(connection, rightsRolesRepository);
        this.userService = new UserServiceImpl(this.userRepository, this.rightsRolesRepository);


        this.accountRepository = new AccountRepositoryMySql(connection);
        this.clientAccountRepository = new ClientAccountRepositoryMySql(connection, this.accountRepository);
        this.clientRepository = new ClientRepositoryMySql(connection, clientAccountRepository);

        this.accountService = new AccountServiceImpl(this.accountRepository, this.clientAccountRepository);
        this.clientService = new ClientServiceImpl(this.clientRepository, this.clientAccountRepository, this.accountRepository);

    }

    public static ComponentFactory getInstance() {
        if (instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public ClientAccountRepository getClientAccountRepository() {
        return clientAccountRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }


}

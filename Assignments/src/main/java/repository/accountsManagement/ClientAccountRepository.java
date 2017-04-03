package repository.accountsManagement;

import model.Account;
import model.Client;

import java.util.List;

public interface ClientAccountRepository {
    boolean addAccountToClient(Client client, List<Account> accounts);

    boolean removeAccountForClient(Account account);

    List<Account> findAccountsforClient(Long clientID);

}

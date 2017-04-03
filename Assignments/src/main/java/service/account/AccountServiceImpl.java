package service.account;


import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import org.joda.time.DateTime;
import repository.account.AccountRepository;
import repository.accountsManagement.ClientAccountRepository;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ClientAccountRepository clientAccountRepository;

    public AccountServiceImpl(AccountRepository repository, ClientAccountRepository clientAccountRepository) {
        this.repository = repository;
        this.clientAccountRepository = null;
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Notification<Account> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public void removeAll() {
        repository.removeAll();

    }

    @Override
    public Notification<Account> addNewAccount(String type, DateTime date) {

        Account account = new AccountBuilder()
                .setMoney(0)
                .setType(type)
                .setDateOfCreation(date)
                .build();

        Notification<Account> addAccountNotification = new Notification<>();
        repository.save(account);
        addAccountNotification.setResult(account);
        return addAccountNotification;
    }


    @Override
    public Notification<Boolean> transferMoney(Long fromId, Long toId, Float sum) {
        Notification<Boolean> success = new Notification<>();
        Notification<Boolean> updateAccountToNotification = new Notification<>();
        Notification<Boolean> updateAccountFromNotification = new Notification<>();
        Account accountFrom = repository.findById(fromId).getResult();
        Account accountTO = repository.findById(toId).getResult();
        if (accountFrom.getMoney() > sum) {
            accountFrom.setMoney(accountFrom.getMoney() - sum);
            accountTO.setMoney(accountTO.getMoney() + sum);

            updateAccountFromNotification.setResult(repository.update(accountFrom));
            updateAccountToNotification.setResult(repository.update(accountTO));

            if ((updateAccountFromNotification.getResult() == Boolean.TRUE) && (updateAccountToNotification.getResult() == Boolean.TRUE))
                success.setResult(Boolean.TRUE);
        } else {
            success.setResult(Boolean.FALSE);
        }
        return success;
    }

    @Override
    public Notification<Boolean> updateAccount(Long ID, Float sum, String operation) {
        Notification<Boolean> success = new Notification<>();
        Notification<Boolean> updateAccountNotification = new Notification<>();

        Account account = repository.findById(ID).getResult();
        if (operation.equals("add"))
            account.setMoney(account.getMoney() + sum);
        else if (operation.equals("withdraw")) {
            if (account.getMoney() > sum) {
                account.setMoney(account.getMoney() - sum);
            }
        }
        updateAccountNotification.setResult(repository.update(account));

        if (updateAccountNotification.getResult() == Boolean.TRUE)
            success.setResult(Boolean.TRUE);
        else {
            success.setResult(Boolean.FALSE);
        }
        return success;
    }


    @Override
    public Notification<Boolean> removeAccount(Long id) {
        Notification<Boolean> deleteAccountNotification = new Notification<>();
        Notification<Boolean> deleteClientAccountNotification = new Notification<>();
        Notification<Boolean> success = new Notification<>();

        Account account = repository.findById(id).getResult();
        deleteAccountNotification.setResult(repository.removeById(account));

        deleteClientAccountNotification.setResult(clientAccountRepository.removeAccountForClient(account));

        if ((deleteAccountNotification.getResult() == Boolean.TRUE) && (deleteClientAccountNotification).getResult() == Boolean.TRUE) {
            success.setResult(Boolean.TRUE);
        } else
            success.setResult(Boolean.FALSE);

        return success;
    }

}

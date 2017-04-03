package repository.account;

import model.Account;
import model.validation.Notification;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    Notification<Account> findById(Long id);

    boolean save(Account account);

    boolean update(Account account);

    boolean removeById(Account account);

    void removeAll();
}

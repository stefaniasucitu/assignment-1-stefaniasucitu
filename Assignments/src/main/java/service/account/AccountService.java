package service.account;

import model.Account;
import model.validation.Notification;
import org.joda.time.DateTime;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Notification<Account> findById(Long id);

    Notification<Account> addNewAccount(String type, DateTime date);

    Notification<Boolean> transferMoney(Long formID, Long toID, Float sum);

    Notification<Boolean> updateAccount(Long ID, Float sum, String operation);

    Notification<Boolean> removeAccount(Long id);

    void removeAll();

}

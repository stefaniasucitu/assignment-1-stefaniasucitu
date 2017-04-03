package model.builder;

import model.Account;
import org.joda.time.DateTime;

public class AccountBuilder {
    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setMoney(float money) {
        account.setMoney(money);
        return this;
    }

    public AccountBuilder setDateOfCreation(DateTime creationDate) {
        account.setDateOfCreation(creationDate);
        return this;
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public Account build() {
        return account;
    }

}

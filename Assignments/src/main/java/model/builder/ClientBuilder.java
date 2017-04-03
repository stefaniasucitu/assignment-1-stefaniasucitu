package model.builder;

import model.Account;
import model.Client;

import java.util.List;

public class ClientBuilder {
    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setFirstName(String firstName) {
        client.setFirstName(firstName);
        return this;
    }

    public ClientBuilder setLastName(String lastName) {
        client.setLastName(lastName);
        return this;
    }

    public ClientBuilder setIdCard(String idCard) {
        client.setIdCard(idCard);
        return this;
    }

    public ClientBuilder setCNP(String cnp) {
        client.setCNP(cnp);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setID(Long id) {
        client.setId(id);
        return this;
    }

    public ClientBuilder setAccounts(List<Account> accounts) {
        client.setAccounts(accounts);
        return this;
    }

    public Client build() {
        return client;
    }
}

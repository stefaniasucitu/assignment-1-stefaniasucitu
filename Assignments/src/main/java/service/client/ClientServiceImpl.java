package service.client;

import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.accountsManagement.ClientAccountRepository;
import repository.client.ClientRepository;

import java.util.Collections;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientAccountRepository clientAccountRepository;
    private final AccountRepository accountRepository;


    public ClientServiceImpl(ClientRepository repository, ClientAccountRepository clientAccountRepository, AccountRepository accountRepository) {
        this.repository = repository;
        this.clientAccountRepository = clientAccountRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Notification<Client> findById(Long id) throws EntityNotFoundException {
        return repository.findById(id);
    }

    @Override
    public Notification<Boolean> addNewClient(Long id, String first, String last, String CNP, String card, String address) {
        Client client = new ClientBuilder()
                .setFirstName(first)
                .setLastName(last)
                .setCNP(CNP)
                .setIdCard(card)
                .setAddress(address)
                .build();
        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();
        //List<Account> accounts = clientAccountRepository.findAccountsforClient(client.getId());
        //client.setAccounts(accounts);
        Notification<Boolean> saveClientNotification = new Notification<>();

        if (!clientValid) {
            clientValidator.getErrors().forEach(saveClientNotification::addError);
            saveClientNotification.setResult(Boolean.FALSE);
            return saveClientNotification;
        } else {
            saveClientNotification.setResult(repository.save(client));
            return saveClientNotification;
        }
    }

    @Override
    public Notification<Boolean> updateClient(Long id, String first, String last, String CNP, String card, String address) {

        Client client = new ClientBuilder()
                .setFirstName(first)
                .setLastName(last)
                .setCNP(CNP)
                .setIdCard(card)
                .setAddress(address)
                .build();
        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();

        Notification<Boolean> updateClientNotification = new Notification<>();
        client.setId(id);
        if (!clientValid) {
            clientValidator.getErrors().forEach(updateClientNotification::addError);
            updateClientNotification.setResult(Boolean.FALSE);
            return updateClientNotification;
        } else {
            updateClientNotification.setResult(repository.update(client));
            return updateClientNotification;
        }

    }

    @Override
    public Notification<Boolean> deleteClient(Long id, String first, String last, String CNP, String card, String address) {
        Client client = new ClientBuilder()
                .setFirstName(first)
                .setLastName(last)
                .setCNP(CNP)
                .setIdCard(card)
                .setAddress(address)
                .build();
        Notification<Boolean> deleteClientNotification = new Notification<>();
        client.setId(id);
        deleteClientNotification.setResult(repository.removeById(client));
        return deleteClientNotification;
    }


    @Override
    public Notification<Client> findByName(String first, String last) {// throws EntityNotFoundException {
        return repository.findByName(first, last);
    }

    @Override
    public Notification<Boolean> addAccountForClient(Long idClient, Long idAccount) {
        Client client = repository.findById(idClient).getResult();
        Account account = accountRepository.findById(idAccount).getResult();
        Notification<Boolean> addAccountForClientNotification = new Notification<>();
        addAccountForClientNotification.setResult(clientAccountRepository.addAccountToClient(client, Collections.singletonList(account)));
        return addAccountForClientNotification;

    }

}

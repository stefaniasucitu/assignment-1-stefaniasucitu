package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Notification<Client> findById(Long id) throws EntityNotFoundException;

    Notification<Client> findByName(String first, String last);//throws EntityNotFoundException;

    Notification<Boolean> addNewClient(Long id, String first, String last, String CNP, String Card, String address);

    Notification<Boolean> updateClient(Long id, String first, String last, String CNP, String card, String address);

    Notification<Boolean> deleteClient(Long id, String first, String last, String CNP, String card, String address);

    Notification<Boolean> addAccountForClient(Long idClient, Long idAccount);
}

package repository.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();

    Notification<Client> findById(Long id);

    Notification<Client> findByName(String first, String last);

    boolean save(Client client);

    boolean update(Client client);

    boolean removeById(Client client);

    void removeAll();


}

package repository.dao;

import bean.Client;

import java.sql.SQLException;
import java.util.List;

public abstract class ClientDao {
    public abstract List<Client> getAllClients() ;
    public abstract Client getClientById(int clientId);
    public abstract Client saveClient(Client client);
    public abstract void updateClient(Client client);
    public abstract void deleteClient(int clientId) ;
}

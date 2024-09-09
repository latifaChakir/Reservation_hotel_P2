package repository.dao;

import bean.Client;

import java.sql.SQLException;
import java.util.List;

public abstract class ClientDao {
    public abstract List<Client> getAllClients() throws SQLException;
    public abstract Client getClientById(int clientId) throws SQLException;
    public abstract void saveClient(Client client) throws SQLException;
    public abstract void updateClient(Client client) throws SQLException;
    public abstract void deleteClient(int clientId) throws SQLException;
}

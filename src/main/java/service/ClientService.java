package service;


import bean.Client;
import repository.impl.ClientDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private ClientDaoImpl ClientDao;

    public ClientService() throws SQLException {
        this.ClientDao = new ClientDaoImpl();
    }

    public List<Client> getAllClients() throws SQLException {
        return ClientDao.getAllClients();
    }

    public Client getClientById(int id) throws SQLException {
        return ClientDao.getClientById(id);
    }

    public void saveClient(Client Client) throws SQLException {
        ClientDao.saveClient(Client);
    }

    public void updateClient(Client Client) throws SQLException {
        ClientDao.updateClient(Client);
    }

    public void deleteClient(int id) throws SQLException {
        ClientDao.deleteClient(id);
    }
}

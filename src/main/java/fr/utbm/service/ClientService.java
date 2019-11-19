/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;

import fr.utbm.entity.Client;
import fr.utbm.repository.ClientDaoImp;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author wuying
 */
public class ClientService {
    public void save(Client client){
        ClientDaoImp clientDao = new ClientDaoImp();
        clientDao.save(client);
    }
    
    public void update (Client client){
        ClientDaoImp clientDao = new ClientDaoImp();
        clientDao.update(client);
    }
    
    public void delete(Serializable id){
        ClientDaoImp clientDao = new ClientDaoImp();
        clientDao.delete(id);
    }
    
    public Client findById(Serializable id){
        ClientDaoImp clientDao = new ClientDaoImp();
        return clientDao.findById(id);       
    }
    public List<Client> findAll(){
      ClientDaoImp clientDao = new ClientDaoImp();
      return clientDao.findAll();
    }
    
    
}

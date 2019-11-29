/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;

import fr.utbm.entity.Client;
import fr.utbm.repository.ClientDao;
import fr.utbm.repository.ClientDaoImp;
import fr.utbm.repository.CourseSessionDaoImp;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author wuying
 */
@Service
public class ClientService {
//    @Resource(name="clientDao")
//    public ClientDao clientDao;
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

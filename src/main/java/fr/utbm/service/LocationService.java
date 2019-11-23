/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;

import fr.utbm.entity.Location;
import fr.utbm.repository.LocationDaoImp;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author wuying
 */
@Service
public class LocationService {
     public void save(Location location){
        LocationDaoImp locationDao = new LocationDaoImp();
        locationDao.save(location);
    }
    
    public void update (Location location){
        LocationDaoImp locationDao = new LocationDaoImp();
        locationDao.update(location);
    }
    
    public void delete(Serializable id){
        LocationDaoImp locationDao = new LocationDaoImp();
        locationDao.delete(id);
    }
    
    public Location findById(Serializable id){
        LocationDaoImp locationDao = new LocationDaoImp();
        return locationDao.findById(id);       
    }
    public List<Location> findAll(){
      LocationDaoImp locationDao = new LocationDaoImp();
      return locationDao.findAll();
    }
}

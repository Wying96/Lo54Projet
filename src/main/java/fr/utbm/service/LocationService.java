/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;

import fr.utbm.entity.Location;
import fr.utbm.repository.LocationDao;
import fr.utbm.repository.LocationDaoImp;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author wuying
 */
@Service
public class LocationService {
    
    @Resource(name = "locationDao")
    public LocationDao locationDao;
    
     public void save(Location location){
        locationDao.save(location);
    }
    
    public void update (Location location){
        locationDao.update(location);
    }
    
    public void delete(Serializable id){
        locationDao.delete(id);
    }
    
    public Location findById(Serializable id){
        return locationDao.findById(id);       
    }
    public List<Location> findAll(){
      return locationDao.findAll();
    }
}

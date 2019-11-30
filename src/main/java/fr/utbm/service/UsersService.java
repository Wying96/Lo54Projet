/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;

import fr.utbm.entity.Users;
import fr.utbm.entity.Location;
import fr.utbm.repository.UsersDao;
import fr.utbm.repository.UsersDaoImp;
import fr.utbm.repository.LocationDaoImp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author wuying
 */
@Service
public class UsersService {
    @Resource(name="usersDao")
    public UsersDao usersDao;
    public Users findById(int id){
//        UsersDaoImp usersDao = new UsersDaoImp();
        return usersDao.findById(id);
    }
    
    public void save(Users u){
       
        usersDao.save(u);
    }
    
//    public List<Users> findBy2SessionTime(Date startDate, Date endDate){
////        UsersDaoImp usersDao =new UsersDaoImp();
//        return usersDao.findBy2SessionTime(startDate, endDate);
//    }
//    
//    public List<Users> findBySessionLocation(Location location){
////        UsersDaoImp usersDao=new UsersDaoImp();
//        return usersDao.findBySessionLocation(location);
//    }
//    
//    public void save(Users course){
////        UsersDaoImp usersDao = new UsersDaoImp();
//        usersDao.save(course);
//    }
//    
//    public void update (Users course){
////        UsersDaoImp usersDao = new UsersDaoImp();
//        usersDao.update(course);
//    }
//    
//    public void delete(Serializable id){
////        UsersDaoImp usersDao = new UsersDaoImp();
//        usersDao.delete(id);
//    }
//    
//    public Users findById(Serializable id){
////        UsersDaoImp usersDao = new UsersDaoImp();
//        return usersDao.findById(id);       
//    }
}

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
//       UsersDaoImp usersDao = new UsersDaoImp();
        return usersDao.findById(id);
    }
    
    public void save(Users user){
//       UsersDaoImp userDap=new UsersDaoImp();
        usersDao.save(user);
    }
    
   public boolean checkEmailAvalible(String inEamil){
       // true--> can register
       //false --> can't create new user
       return usersDao.findByEmail(inEamil) == null;
   }
   
   public boolean checkLogin(String inEmail, String inPwd){
       Users u = usersDao.findByEmail(inEmail);
       if(u!=null){
            if(u.getPassword().equals(inPwd))
                return true;
            return false;
       }
       else{
           return false;
       }
   }
   
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;

import fr.utbm.entity.Users;
import fr.utbm.entity.Location;
import fr.utbm.repository.InscrirSessionDao;
import fr.utbm.repository.InscrirSessionDaoImp;
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

    @Resource(name = "usersDao")
    public UsersDao usersDao;

    @Resource(name = "inscrirSessionDao")
    public InscrirSessionDao inscrirSessionDao;

    public Users findById(int id) {
//       UsersDaoImp usersDao = new UsersDaoImp();
        return usersDao.findById(id);
    }

    public void save(Users user) {
//       UsersDaoImp userDap=new UsersDaoImp();
        usersDao.save(user);
    }

    public boolean checkEmailAvalible(String inEamil) {
        // true--> can register
        //false --> can't create new user
        return usersDao.findByEmail(inEamil) == null;
    }

    public boolean checkLogin(String inEmail, String inPwd) {
        UsersDaoImp usersImp = new UsersDaoImp();
        Users u = usersImp.findByEmail(inEmail);
        //TODO:chang boolean to int to identifier error of pwd of compt notExist
        if (u != null) {
            return (u.getPassword().equals(inPwd));
        } else {
            return false;
        }
    }

    public boolean inscrirSession(int uId, int sessionId) {
        InscrirSessionDaoImp inscrir = new InscrirSessionDaoImp();
//       inscrirSessionDao.
        if (inscrir.findByUserAndSession(uId, sessionId) == null) {
            try {
                usersDao.inscrirSession(uId, sessionId);
            } catch (Exception e) {
            } finally {
                return true;
            }
        } else {
            return false;
        }
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.CourseSession;
import fr.utbm.entity.InscrirSession;
import fr.utbm.entity.Users;
import static java.util.Collections.list;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wuying
 */
@Repository(value = "usersDao")
public class UsersDaoImp extends BaseDaoImp<Users> implements UsersDao{
  
   @Override
   public Users findById(int id){
       Session session = this.getSession();
       String hql = "from Users u where u.idUser = :id";
       Query query = this.getSession().createQuery(hql);
       query.setInteger("idUser",id);
       List<Users> u = query.list();
       if(u.isEmpty()){
           session.close();
           return null;
       }
       else{
           session.close();
           return u.get(0);         
       }
   }
    
   @Override
   public Users findByEmail(String inEamil){
       Session session = this.getSession();
       String hql = "from Users u where u.email = :email";
       Query query = this.getSession().createQuery(hql);
       query.setString("email",inEamil);
       List<Users> u = query.list();
       if(u.isEmpty()){
           session.close();
           return null;
       }
       else{
           session.close();
           return u.get(0);         
       }
   }
    
//    @Override
//   public boolean checkEmailExistence(String inEamil){
//       return this.findByEmail(inEamil) != null;
//   }
    
//   @Override
//   public boolean checkLogin(String inEmail, String inPwd){
//        if(this.findByEmail(inEmail)==null){
//            
//        }
//        else{
//            return false;
//        }
//   }
   
//   @Override
//   public void inscrirSession(int uId,int sessionId){
//       InscrirSession inscrir = new InscrirSession();
//       inscrir.setUserId( uId);
//       inscrir.setCourseSessionId(sessionId);
//       InscrirSessionDaoImp inscrirDao = new InscrirSessionDaoImp();
//       inscrirDao.save(inscrir);
//   }
    
    
}

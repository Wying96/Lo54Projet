/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Users;
import java.io.Serializable;
import static java.util.Collections.list;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wuying
 */
@Repository(value = "usersDao")
public class UsersDaoImp extends BaseDaoImp<Users> implements UsersDao {

    @Override
    public void save(Users u){
    
    }
    
    public void update(Users u){
    
    }
    
    public void delete(Users u){
    
    }
    
    @Override
    public Users findById(Integer id) {
        Session session = this.getSession();
        String hql = "from Users u where u.idUser = :id";
        Query query = this.getSession().createQuery(hql);
        query.setInteger("id", id);
        List<Users> u = query.list();
        if (u.isEmpty()) {
            session.close();
            return null;
        } else {
            session.close();
            return u.get(0);
        }
    }

    @Override
    public Users findByEmail(String inEamil) {
        Session session = this.getSession();
        String hql = "from Users u where u.email = :email";
        Query query = this.getSession().createQuery(hql);
        query.setString("email", inEamil);
        List<Users> u = query.list();
        if (u.isEmpty()) {
            session.close();
            return null;
        } else {
            session.close();
            return u.get(0);
        }
    }

    @Override
    public void inscrirSession(int uId, CourseSession cs) {
        
    }

}

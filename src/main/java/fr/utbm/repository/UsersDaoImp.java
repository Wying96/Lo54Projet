/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import exceptions.IllegalOrphanException;
import exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wuying
 */
@Repository(value = "usersDao")
public class UsersDaoImp extends BaseDaoImp<Users> implements UsersDao {

//    @Override
//    public void save(Users u){
//    
//    }
//    
    @Override
    public void update(Users users) {
        Session session = null;
        try {
            session = this.getSession();
            session.getTransaction().begin();
            Users persistentUsers = (Users) session.get(Users.class, (Serializable)users.getIdUser());
            Collection<CourseSession> courseSessionCollectionOld = persistentUsers.getCourseSessionCollection();
            Collection<CourseSession> courseSessionCollectionNew = users.getCourseSessionCollection();
            Collection<CourseSession> attachedCourseSessionCollectionNew = new ArrayList<CourseSession>();
            for (CourseSession courseSessionCollectionNewCourseSessionToAttach : courseSessionCollectionNew) {
                courseSessionCollectionNewCourseSessionToAttach = (CourseSession) session.load(courseSessionCollectionNewCourseSessionToAttach.getClass(),(Serializable) courseSessionCollectionNewCourseSessionToAttach.getId());
                attachedCourseSessionCollectionNew.add(courseSessionCollectionNewCourseSessionToAttach);
            }
            courseSessionCollectionNew = attachedCourseSessionCollectionNew;
            users.setCourseSessionCollection(courseSessionCollectionNew);
            users = (Users) session.merge(users);
            for (CourseSession courseSessionCollectionOldCourseSession : courseSessionCollectionOld) {
                if (!courseSessionCollectionNew.contains(courseSessionCollectionOldCourseSession)) {
                    courseSessionCollectionOldCourseSession.getUsersCollection().remove(users);
                    courseSessionCollectionOldCourseSession = (CourseSession) session.merge(courseSessionCollectionOldCourseSession);
                }
            }
            for (CourseSession courseSessionCollectionNewCourseSession : courseSessionCollectionNew) {
                if (!courseSessionCollectionOld.contains(courseSessionCollectionNewCourseSession)) {
                    courseSessionCollectionNewCourseSession.getUsersCollection().add(users);
                    courseSessionCollectionNewCourseSession = (CourseSession) session.merge(courseSessionCollectionNewCourseSession);
                }
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            session.getTransaction().rollback();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getIdUser();
                if (this.findById((Serializable)id) == null) {
                    try {
                        throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                    } catch (NonexistentEntityException ex1) {
                        Logger.getLogger(UsersDaoImp.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
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

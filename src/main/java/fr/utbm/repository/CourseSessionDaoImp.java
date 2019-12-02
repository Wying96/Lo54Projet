/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import exceptions.IllegalOrphanException;
import exceptions.NonexistentEntityException;
import fr.utbm.entity.CourseSession;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import fr.utbm.entity.Users;
import fr.utbm.entity.Client;
import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import fr.utbm.entity.Users;
import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import static org.hibernate.type.TypeFactory.serializable;
import fr.utbm.repository.CourseSessionDao;


/**
 *
 * @author wuying
 */
@Repository(value = "courseSessionDao") 
public class CourseSessionDaoImp extends BaseDaoImp<CourseSession> 
        implements CourseSessionDao {
    @Override
    public void save(CourseSession courseSession){
         if (courseSession.getUsersCollection()== null) {
            courseSession.setUsersCollection(new ArrayList<Users>());
        }
        if (courseSession.getClientCollection() == null) {
            courseSession.setClientCollection(new ArrayList<Client>());
        }
        Session session = null;
        try {
            session = this.getSession();
            session.getTransaction().begin();
            Location locationId = courseSession.getLocationId();
            if (locationId != null) {
                locationId = (Location) session.load(locationId.getClass(), (Serializable)locationId.getId());
                courseSession.setLocationId(locationId);
            }
            Course courseId = courseSession.getCourseId();
            if (courseId != null) {
                courseId = (Course) session.load(courseId.getClass(), (Serializable)courseId.getId());
                courseSession.setCourseId(courseId);
            }
            
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : courseSession.getUsersCollection()) {
                usersCollectionUsersToAttach = (Users) session.load(usersCollectionUsersToAttach.getClass(), (Serializable)usersCollectionUsersToAttach.getIdUser());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            courseSession.setUsersCollection(attachedUsersCollection);
            Collection<Client> attachedClientCollection = new ArrayList<Client>();
            for (Client clientCollectionClientToAttach : courseSession.getClientCollection()) {
                clientCollectionClientToAttach = (Client) session.load(clientCollectionClientToAttach.getClass(), (Serializable)clientCollectionClientToAttach.getId());
                attachedClientCollection.add(clientCollectionClientToAttach);
            }
            courseSession.setClientCollection(attachedClientCollection);
            session.persist(courseSession);
            if (locationId != null) {
                locationId.getCourseSessionCollection().add(courseSession);
                locationId = (Location) session.merge(locationId);
            }
            if (courseId != null) {
                courseId.getCourseSessionCollection().add(courseSession);
                courseId = (Course) session.merge(courseId);
            }
            //添加新的users
            for (Users usersCollectionUsers : courseSession.getUsersCollection()) {
                usersCollectionUsers.getCourseSessionCollection().add(courseSession);
                usersCollectionUsers = (Users) session.merge(usersCollectionUsers);
            }
            for (Client clientCollectionClient : courseSession.getClientCollection()) {
                CourseSession oldCourseSessionIdOfClientCollectionClient = clientCollectionClient.getCourseSessionId();
                clientCollectionClient.setCourseSessionId(courseSession);
                clientCollectionClient = (Client) session.merge(clientCollectionClient);
                if (oldCourseSessionIdOfClientCollectionClient != null) {
                    oldCourseSessionIdOfClientCollectionClient.getClientCollection().remove(clientCollectionClient);
                    oldCourseSessionIdOfClientCollectionClient = (CourseSession) session.merge(oldCourseSessionIdOfClientCollectionClient);
                }
            }
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public void update(CourseSession courseSession){
        Session session = null;
        try {
            session = this.getSession();
            session.getTransaction().begin();
            CourseSession persistentCourseSession = (CourseSession) session.get(CourseSession.class, (Serializable)courseSession.getId());
            Location locationIdOld = persistentCourseSession.getLocationId();
            Location locationIdNew = courseSession.getLocationId();
            Course courseIdOld = persistentCourseSession.getCourseId();
            Course courseIdNew = courseSession.getCourseId();
            Collection<Users> usersCollectionOld = persistentCourseSession.getUsersCollection();
            Collection<Users> usersCollectionNew = courseSession.getUsersCollection();
            Collection<Client> clientCollectionOld = persistentCourseSession.getClientCollection();
            Collection<Client> clientCollectionNew = courseSession.getClientCollection();
            List<String> illegalOrphanMessages = null;
            for (Client clientCollectionOldClient : clientCollectionOld) {
                if (!clientCollectionNew.contains(clientCollectionOldClient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Client " + clientCollectionOldClient + " since its courseSessionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (locationIdNew != null) {
                locationIdNew = (Location) session.load(locationIdNew.getClass(), (Serializable)locationIdNew.getId());
                courseSession.setLocationId(locationIdNew);
            }
            if (courseIdNew != null) {
                courseIdNew = (Course) session.load(courseIdNew.getClass(), (Serializable)courseIdNew.getId());
                courseSession.setCourseId(courseIdNew);
            }
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = (Users) session.load(usersCollectionNewUsersToAttach.getClass(), (Serializable) usersCollectionNewUsersToAttach.getIdUser());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            courseSession.setUsersCollection(usersCollectionNew);
            Collection<Client> attachedClientCollectionNew = new ArrayList<Client>();
            for (Client clientCollectionNewClientToAttach : clientCollectionNew) {
                clientCollectionNewClientToAttach = (Client) session.load(clientCollectionNewClientToAttach.getClass(),(Serializable) clientCollectionNewClientToAttach.getId());
                attachedClientCollectionNew.add(clientCollectionNewClientToAttach);
            }
            clientCollectionNew = attachedClientCollectionNew;
            courseSession.setClientCollection(clientCollectionNew);
            courseSession = (CourseSession) session.merge(courseSession);
            if (locationIdOld != null && !locationIdOld.equals(locationIdNew)) {
                locationIdOld.getCourseSessionCollection().remove(courseSession);
                locationIdOld = (Location) session.merge(locationIdOld);
            }
            if (locationIdNew != null && !locationIdNew.equals(locationIdOld)) {
                locationIdNew.getCourseSessionCollection().add(courseSession);
                locationIdNew = (Location) session.merge(locationIdNew);
            }
            if (courseIdOld != null && !courseIdOld.equals(courseIdNew)) {
                courseIdOld.getCourseSessionCollection().remove(courseSession);
                courseIdOld = (Course) session.merge(courseIdOld);
            }
            if (courseIdNew != null && !courseIdNew.equals(courseIdOld)) {
                courseIdNew.getCourseSessionCollection().add(courseSession);
                courseIdNew = (Course) session.merge(courseIdNew);
            }
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    usersCollectionOldUsers.getCourseSessionCollection().remove(courseSession);
                    //这里没准会有问题
                    usersCollectionOldUsers = (Users) session.merge(usersCollectionOldUsers);
                }
            }
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    usersCollectionNewUsers.getCourseSessionCollection().add(courseSession);
                    usersCollectionNewUsers = (Users) session.merge(usersCollectionNewUsers);
                }
            }
            for (Client clientCollectionNewClient : clientCollectionNew) {
                if (!clientCollectionOld.contains(clientCollectionNewClient)) {
                    CourseSession oldCourseSessionIdOfClientCollectionNewClient = clientCollectionNewClient.getCourseSessionId();
                    clientCollectionNewClient.setCourseSessionId(courseSession);
                    clientCollectionNewClient = (Client) session.merge(clientCollectionNewClient);
                    if (oldCourseSessionIdOfClientCollectionNewClient != null && !oldCourseSessionIdOfClientCollectionNewClient.equals(courseSession)) {
                        oldCourseSessionIdOfClientCollectionNewClient.getClientCollection().remove(clientCollectionNewClient);
                        oldCourseSessionIdOfClientCollectionNewClient = (CourseSession) session.merge(oldCourseSessionIdOfClientCollectionNewClient);
                    }
                }
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            session.getTransaction().rollback();
            if (msg == null || msg.length() == 0) {
                Integer id = courseSession.getId();
                if (this.findById((Serializable)id) == null) {
                    try {
                        throw new NonexistentEntityException("The courseSession with id " + id + " no longer exists.");
                    } catch (NonexistentEntityException ex1) {
                        Logger.getLogger(CourseSessionDaoImp.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public void delete(CourseSession cs){
    
    }
    
}

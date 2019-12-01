/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caokao;

import caokao.exceptions.IllegalOrphanException;
import caokao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wuying
 */
public class CourseSessionJpaController implements Serializable {

    public CourseSessionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CourseSession courseSession) {
        if (courseSession.getUsersCollection() == null) {
            courseSession.setUsersCollection(new ArrayList<Users>());
        }
        if (courseSession.getClientCollection() == null) {
            courseSession.setClientCollection(new ArrayList<Client>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Location locationId = courseSession.getLocationId();
            if (locationId != null) {
                locationId = em.getReference(locationId.getClass(), locationId.getId());
                courseSession.setLocationId(locationId);
            }
            Course courseId = courseSession.getCourseId();
            if (courseId != null) {
                courseId = em.getReference(courseId.getClass(), courseId.getId());
                courseSession.setCourseId(courseId);
            }
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : courseSession.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getIdUser());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            courseSession.setUsersCollection(attachedUsersCollection);
            Collection<Client> attachedClientCollection = new ArrayList<Client>();
            for (Client clientCollectionClientToAttach : courseSession.getClientCollection()) {
                clientCollectionClientToAttach = em.getReference(clientCollectionClientToAttach.getClass(), clientCollectionClientToAttach.getId());
                attachedClientCollection.add(clientCollectionClientToAttach);
            }
            courseSession.setClientCollection(attachedClientCollection);
            em.persist(courseSession);
            if (locationId != null) {
                locationId.getCourseSessionCollection().add(courseSession);
                locationId = em.merge(locationId);
            }
            if (courseId != null) {
                courseId.getCourseSessionCollection().add(courseSession);
                courseId = em.merge(courseId);
            }
            for (Users usersCollectionUsers : courseSession.getUsersCollection()) {
                usersCollectionUsers.getCourseSessionCollection().add(courseSession);
                usersCollectionUsers = em.merge(usersCollectionUsers);
            }
            for (Client clientCollectionClient : courseSession.getClientCollection()) {
                CourseSession oldCourseSessionIdOfClientCollectionClient = clientCollectionClient.getCourseSessionId();
                clientCollectionClient.setCourseSessionId(courseSession);
                clientCollectionClient = em.merge(clientCollectionClient);
                if (oldCourseSessionIdOfClientCollectionClient != null) {
                    oldCourseSessionIdOfClientCollectionClient.getClientCollection().remove(clientCollectionClient);
                    oldCourseSessionIdOfClientCollectionClient = em.merge(oldCourseSessionIdOfClientCollectionClient);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CourseSession courseSession) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CourseSession persistentCourseSession = em.find(CourseSession.class, courseSession.getId());
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
                locationIdNew = em.getReference(locationIdNew.getClass(), locationIdNew.getId());
                courseSession.setLocationId(locationIdNew);
            }
            if (courseIdNew != null) {
                courseIdNew = em.getReference(courseIdNew.getClass(), courseIdNew.getId());
                courseSession.setCourseId(courseIdNew);
            }
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getIdUser());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            courseSession.setUsersCollection(usersCollectionNew);
            Collection<Client> attachedClientCollectionNew = new ArrayList<Client>();
            for (Client clientCollectionNewClientToAttach : clientCollectionNew) {
                clientCollectionNewClientToAttach = em.getReference(clientCollectionNewClientToAttach.getClass(), clientCollectionNewClientToAttach.getId());
                attachedClientCollectionNew.add(clientCollectionNewClientToAttach);
            }
            clientCollectionNew = attachedClientCollectionNew;
            courseSession.setClientCollection(clientCollectionNew);
            courseSession = em.merge(courseSession);
            if (locationIdOld != null && !locationIdOld.equals(locationIdNew)) {
                locationIdOld.getCourseSessionCollection().remove(courseSession);
                locationIdOld = em.merge(locationIdOld);
            }
            if (locationIdNew != null && !locationIdNew.equals(locationIdOld)) {
                locationIdNew.getCourseSessionCollection().add(courseSession);
                locationIdNew = em.merge(locationIdNew);
            }
            if (courseIdOld != null && !courseIdOld.equals(courseIdNew)) {
                courseIdOld.getCourseSessionCollection().remove(courseSession);
                courseIdOld = em.merge(courseIdOld);
            }
            if (courseIdNew != null && !courseIdNew.equals(courseIdOld)) {
                courseIdNew.getCourseSessionCollection().add(courseSession);
                courseIdNew = em.merge(courseIdNew);
            }
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    usersCollectionOldUsers.getCourseSessionCollection().remove(courseSession);
                    usersCollectionOldUsers = em.merge(usersCollectionOldUsers);
                }
            }
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    usersCollectionNewUsers.getCourseSessionCollection().add(courseSession);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                }
            }
            for (Client clientCollectionNewClient : clientCollectionNew) {
                if (!clientCollectionOld.contains(clientCollectionNewClient)) {
                    CourseSession oldCourseSessionIdOfClientCollectionNewClient = clientCollectionNewClient.getCourseSessionId();
                    clientCollectionNewClient.setCourseSessionId(courseSession);
                    clientCollectionNewClient = em.merge(clientCollectionNewClient);
                    if (oldCourseSessionIdOfClientCollectionNewClient != null && !oldCourseSessionIdOfClientCollectionNewClient.equals(courseSession)) {
                        oldCourseSessionIdOfClientCollectionNewClient.getClientCollection().remove(clientCollectionNewClient);
                        oldCourseSessionIdOfClientCollectionNewClient = em.merge(oldCourseSessionIdOfClientCollectionNewClient);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = courseSession.getId();
                if (findCourseSession(id) == null) {
                    throw new NonexistentEntityException("The courseSession with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CourseSession courseSession;
            try {
                courseSession = em.getReference(CourseSession.class, id);
                courseSession.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The courseSession with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Client> clientCollectionOrphanCheck = courseSession.getClientCollection();
            for (Client clientCollectionOrphanCheckClient : clientCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CourseSession (" + courseSession + ") cannot be destroyed since the Client " + clientCollectionOrphanCheckClient + " in its clientCollection field has a non-nullable courseSessionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Location locationId = courseSession.getLocationId();
            if (locationId != null) {
                locationId.getCourseSessionCollection().remove(courseSession);
                locationId = em.merge(locationId);
            }
            Course courseId = courseSession.getCourseId();
            if (courseId != null) {
                courseId.getCourseSessionCollection().remove(courseSession);
                courseId = em.merge(courseId);
            }
            Collection<Users> usersCollection = courseSession.getUsersCollection();
            for (Users usersCollectionUsers : usersCollection) {
                usersCollectionUsers.getCourseSessionCollection().remove(courseSession);
                usersCollectionUsers = em.merge(usersCollectionUsers);
            }
            em.remove(courseSession);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CourseSession> findCourseSessionEntities() {
        return findCourseSessionEntities(true, -1, -1);
    }

    public List<CourseSession> findCourseSessionEntities(int maxResults, int firstResult) {
        return findCourseSessionEntities(false, maxResults, firstResult);
    }

    private List<CourseSession> findCourseSessionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CourseSession.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CourseSession findCourseSession(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CourseSession.class, id);
        } finally {
            em.close();
        }
    }

    public int getCourseSessionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CourseSession> rt = cq.from(CourseSession.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

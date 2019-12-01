/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caokao;

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
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getCourseSessionCollection() == null) {
            users.setCourseSessionCollection(new ArrayList<CourseSession>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CourseSession> attachedCourseSessionCollection = new ArrayList<CourseSession>();
            for (CourseSession courseSessionCollectionCourseSessionToAttach : users.getCourseSessionCollection()) {
                courseSessionCollectionCourseSessionToAttach = em.getReference(courseSessionCollectionCourseSessionToAttach.getClass(), courseSessionCollectionCourseSessionToAttach.getId());
                attachedCourseSessionCollection.add(courseSessionCollectionCourseSessionToAttach);
            }
            users.setCourseSessionCollection(attachedCourseSessionCollection);
            em.persist(users);
            for (CourseSession courseSessionCollectionCourseSession : users.getCourseSessionCollection()) {
                courseSessionCollectionCourseSession.getUsersCollection().add(users);
                courseSessionCollectionCourseSession = em.merge(courseSessionCollectionCourseSession);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getIdUser());
            Collection<CourseSession> courseSessionCollectionOld = persistentUsers.getCourseSessionCollection();
            Collection<CourseSession> courseSessionCollectionNew = users.getCourseSessionCollection();
            Collection<CourseSession> attachedCourseSessionCollectionNew = new ArrayList<CourseSession>();
            for (CourseSession courseSessionCollectionNewCourseSessionToAttach : courseSessionCollectionNew) {
                courseSessionCollectionNewCourseSessionToAttach = em.getReference(courseSessionCollectionNewCourseSessionToAttach.getClass(), courseSessionCollectionNewCourseSessionToAttach.getId());
                attachedCourseSessionCollectionNew.add(courseSessionCollectionNewCourseSessionToAttach);
            }
            courseSessionCollectionNew = attachedCourseSessionCollectionNew;
            users.setCourseSessionCollection(courseSessionCollectionNew);
            users = em.merge(users);
            for (CourseSession courseSessionCollectionOldCourseSession : courseSessionCollectionOld) {
                if (!courseSessionCollectionNew.contains(courseSessionCollectionOldCourseSession)) {
                    courseSessionCollectionOldCourseSession.getUsersCollection().remove(users);
                    courseSessionCollectionOldCourseSession = em.merge(courseSessionCollectionOldCourseSession);
                }
            }
            for (CourseSession courseSessionCollectionNewCourseSession : courseSessionCollectionNew) {
                if (!courseSessionCollectionOld.contains(courseSessionCollectionNewCourseSession)) {
                    courseSessionCollectionNewCourseSession.getUsersCollection().add(users);
                    courseSessionCollectionNewCourseSession = em.merge(courseSessionCollectionNewCourseSession);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getIdUser();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            Collection<CourseSession> courseSessionCollection = users.getCourseSessionCollection();
            for (CourseSession courseSessionCollectionCourseSession : courseSessionCollection) {
                courseSessionCollectionCourseSession.getUsersCollection().remove(users);
                courseSessionCollectionCourseSession = em.merge(courseSessionCollectionCourseSession);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

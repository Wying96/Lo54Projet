package fr.utbm.repository;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package fr.utbm.formationecole.repository;
//
//import java.io.Serializable;
//import javax.persistence.Query;
//import javax.persistence.EntityNotFoundException;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import fr.utbm.formationecole.entity.CourseSession;
//import fr.utbm.formationecole.entity.Location;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import fr.utbm.formationecole.repository.exceptions.IllegalOrphanException;
//import fr.utbm.formationecole.repository.exceptions.NonexistentEntityException;
//
///**
// *
// * @author wuying
// */
//public class LocationJpaController implements Serializable {
//
//    public LocationJpaController(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//    private EntityManagerFactory emf = null;
//
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public void create(Location location) {
//        if (location.getCourseSessionSet() == null) {
//            location.setCourseSessionSet(new HashSet<CourseSession>());
//        }
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Set<CourseSession> attachedCourseSessionSet = new HashSet<CourseSession>();
//            for (CourseSession courseSessionSetCourseSessionToAttach : location.getCourseSessionSet()) {
//                courseSessionSetCourseSessionToAttach = em.getReference(courseSessionSetCourseSessionToAttach.getClass(), courseSessionSetCourseSessionToAttach.getId());
//                attachedCourseSessionSet.add(courseSessionSetCourseSessionToAttach);
//            }
//            location.setCourseSessionSet(attachedCourseSessionSet);
//            em.persist(location);
//            for (CourseSession courseSessionSetCourseSession : location.getCourseSessionSet()) {
//                Location oldLocationIdOfCourseSessionSetCourseSession = courseSessionSetCourseSession.getLocationId();
//                courseSessionSetCourseSession.setLocationId(location);
//                courseSessionSetCourseSession = em.merge(courseSessionSetCourseSession);
//                if (oldLocationIdOfCourseSessionSetCourseSession != null) {
//                    oldLocationIdOfCourseSessionSetCourseSession.getCourseSessionSet().remove(courseSessionSetCourseSession);
//                    oldLocationIdOfCourseSessionSetCourseSession = em.merge(oldLocationIdOfCourseSessionSetCourseSession);
//                }
//            }
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void edit(Location location) throws IllegalOrphanException, NonexistentEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Location persistentLocation = em.find(Location.class, location.getId());
//            Set<CourseSession> courseSessionSetOld = persistentLocation.getCourseSessionSet();
//            Set<CourseSession> courseSessionSetNew = location.getCourseSessionSet();
//            List<String> illegalOrphanMessages = null;
//            for (CourseSession courseSessionSetOldCourseSession : courseSessionSetOld) {
//                if (!courseSessionSetNew.contains(courseSessionSetOldCourseSession)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain CourseSession " + courseSessionSetOldCourseSession + " since its locationId field is not nullable.");
//                }
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            Set<CourseSession> attachedCourseSessionSetNew = new HashSet<CourseSession>();
//            for (CourseSession courseSessionSetNewCourseSessionToAttach : courseSessionSetNew) {
//                courseSessionSetNewCourseSessionToAttach = em.getReference(courseSessionSetNewCourseSessionToAttach.getClass(), courseSessionSetNewCourseSessionToAttach.getId());
//                attachedCourseSessionSetNew.add(courseSessionSetNewCourseSessionToAttach);
//            }
//            courseSessionSetNew = attachedCourseSessionSetNew;
//            location.setCourseSessionSet(courseSessionSetNew);
//            location = em.merge(location);
//            for (CourseSession courseSessionSetNewCourseSession : courseSessionSetNew) {
//                if (!courseSessionSetOld.contains(courseSessionSetNewCourseSession)) {
//                    Location oldLocationIdOfCourseSessionSetNewCourseSession = courseSessionSetNewCourseSession.getLocationId();
//                    courseSessionSetNewCourseSession.setLocationId(location);
//                    courseSessionSetNewCourseSession = em.merge(courseSessionSetNewCourseSession);
//                    if (oldLocationIdOfCourseSessionSetNewCourseSession != null && !oldLocationIdOfCourseSessionSetNewCourseSession.equals(location)) {
//                        oldLocationIdOfCourseSessionSetNewCourseSession.getCourseSessionSet().remove(courseSessionSetNewCourseSession);
//                        oldLocationIdOfCourseSessionSetNewCourseSession = em.merge(oldLocationIdOfCourseSessionSetNewCourseSession);
//                    }
//                }
//            }
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                Integer id = location.getId();
//                if (findLocation(id) == null) {
//                    throw new NonexistentEntityException("The location with id " + id + " no longer exists.");
//                }
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Location location;
//            try {
//                location = em.getReference(Location.class, id);
//                location.getId();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The location with id " + id + " no longer exists.", enfe);
//            }
//            List<String> illegalOrphanMessages = null;
//            Set<CourseSession> courseSessionSetOrphanCheck = location.getCourseSessionSet();
//            for (CourseSession courseSessionSetOrphanCheckCourseSession : courseSessionSetOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This Location (" + location + ") cannot be destroyed since the CourseSession " + courseSessionSetOrphanCheckCourseSession + " in its courseSessionSet field has a non-nullable locationId field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            em.remove(location);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public List<Location> findLocationEntities() {
//        return findLocationEntities(true, -1, -1);
//    }
//
//    public List<Location> findLocationEntities(int maxResults, int firstResult) {
//        return findLocationEntities(false, maxResults, firstResult);
//    }
//
//    private List<Location> findLocationEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Location.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }
//
//    public Location findLocation(Integer id) {
//        EntityManager em = getEntityManager();
//        try {
//            return em.find(Location.class, id);
//        } finally {
//            em.close();
//        }
//    }
//
//    public int getLocationCount() {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            Root<Location> rt = cq.from(Location.class);
//            cq.select(em.getCriteriaBuilder().count(rt));
//            Query q = em.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } finally {
//            em.close();
//        }
//    }
//    
//}

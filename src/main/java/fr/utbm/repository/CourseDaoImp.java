/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import fr.utbm.repository.CourseDao;

/**
 *
 * @author c
 */
@Repository(value = "courseDao")
public class CourseDaoImp extends BaseDaoImp<Course> implements CourseDao {


    @Override
    public List<Course> findByTitle(String title) {
        Session session = this.getSession();
        String hql = "from Course c where c.title like :partOfTitle";
        Query query = session.createQuery(hql);
        query.setString("partOfTitle", "%" + title + "%");
        session.close();
        return query.list();
    }

    @Override
    public List<Course> findBy2SessionTime(Date startDate, Date endDate) {//Object... params
        Session session = this.getSession();
       
        String hql = "from Course c Left Join "
                + "fetch c.courseSessionCollection v "
                + "where v.startDate > :startDate and v.endDate < :endDate";
        Query query = this.getSession().createQuery(hql);

        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        session.close();
        return query.list();
    }

    @Override
    public List<Course> findBySessionLocation(Location location) {
        Session session = this.getSession();
        String hql = "from Course c "
                + "Left Join fetch c.courseSessionCollection v "
                + "where v.locationId = :location";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("location", location);
        session.close();
        return query.list();
    }

    @Override
    public List<Course> findByOneDayDispon(Date dateDispon) {
        Session session = this.getSession();
        String hql = "from Course c Left Join "
                + "fetch c.courseSessionCollection v "
                + "where v.startDate < :dateDispon1 and v.endDate > :dateDispon2";
        Query query = this.getSession().createQuery(hql);

        query.setDate("dateDispon1", dateDispon);
        query.setDate("dateDispon2", dateDispon);
        session.close();
        return query.list();
    }

    @Override
    public List<Course> findByMultiCcondition(String title, Date startDate,
            Date endDate, Integer locationId) {
        Session session = this.getSession();
        String hql = "from Course c Left Join "
                + "fetch c.courseSessionCollection v ";
        boolean firstCondition = true;
        if (title != null) {
            hql += "where c.title like :partOfTitle ";
            firstCondition = false;
        }
        if (startDate != null) {
            if (firstCondition) {
                hql += "where v.startDate >= :startDate ";
                firstCondition = false;
            } else {
                hql += "and v.startDate >= :startDate ";
            }
        }
        if (endDate != null) {
            if (firstCondition) {
                hql += "where v.endDate <= :endDate ";
                firstCondition = false;
            } else {
                hql += "and v.endDate <= :endDate ";
            }
        }
        if (locationId != null) {
            if (firstCondition) {
                hql += "where v.locationId.id = :locationId ";
                firstCondition = false;
            } else {
                hql += "and v.locationId.id = :locationId ";
            }
        }
        hql += "order by c.id ";

        Query query = this.getSession().createQuery(hql);
        if (title != null) { query.setString("partOfTitle", "%" + title + "%");}
        if (startDate != null) { query.setDate("startDate", startDate);}
        if (endDate != null) { query.setDate("endDate", endDate);}
        if (locationId != null) {query.setInteger("locationId", locationId);}
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        session.close();
        return query.list();
    }

}

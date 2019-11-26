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
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author c
 */
@Repository(value = "courseDao")
public class CourseDaoImp extends BaseDaoImp<Course> implements CourseDao {
//筛选的解决方案 1.直接写HQL进行删选 或者  2.先选出全部再在service中进行筛选

    @Override
    public List<Course> findByTitle(String title) {
        //这种写法有被sql注入的风险
        //String hql = "from Course c where c.title like"+ '%'+title+'%';
        String hql = "from Course c where c.title like :partOfTitle";
        Query query = this.getSession().createQuery(hql);
        query.setString("partOfTitle", "%" + title + "%");
        return query.list();
    }

    @Override
    public List<Course> findBy2SessionTime(Date startDate, Date endDate) {//Object... params
        //Date startDate, Date endDate
        String hql = "from Course c Left Join "
                + "fetch c.courseSessionCollection v "
                + "where v.startDate > :startDate and v.endDate < :endDate";
        Query query = this.getSession().createQuery(hql);

        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);

        return query.list();
    }

    @Override
    public List<Course> findBySessionLocation(Location location) {
        String hql = "from Course c "
                + "Left Join fetch c.courseSessionCollection v "
                + "where v.locationId = :location";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("location", location);
        return query.list();
    }

    @Override
    public List<Course> findByOneDayDispon(Date dateDispon) {
        String hql = "from Course c Left Join "
                + "fetch c.courseSessionCollection v "
                + "where v.startDate < :dateDispon1 and v.endDate > :dateDispon2";
        Query query = this.getSession().createQuery(hql);

        query.setDate("dateDispon1", dateDispon);
        query.setDate("dateDispon2", dateDispon);

        return query.list();
    }

    @Override
    public List<Course> findByMultiCcondition(String title, Date startDate,
            Date endDate, Integer locationId) {

        String hql = "from Course c Left Join "
                + "fetch c.courseSessionCollection v ";
        boolean firstCondition = true;
        if (title != null) {
            hql += "where c.title like :partOfTitle ";
            firstCondition = false;
        }
        if (startDate != null) {
            if (firstCondition) {
                hql += "where v.startDate <= :startDate ";
                firstCondition = false;
            } else {
                hql += "and v.startDate <= :startDate ";
            }
        }
        if (endDate != null) {
            if (firstCondition) {
                hql += "where v.endDate >= :endDate ";
                firstCondition = false;
            } else {
                hql += "and v.endDate >= :endDate ";
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

        Query query = this.getSession().createQuery(hql);
        if (title != null) { query.setString("partOfTitle", title);}
        if (startDate != null) { query.setDate("startDate", startDate);}
        if (endDate != null) { query.setDate("endDate", endDate);}
        if (locationId != null) {query.setInteger("locationId", locationId);}

        return query.list();
    }

}

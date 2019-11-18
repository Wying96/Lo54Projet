/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.Course;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author c
 */
@Repository(value = "CourseDao")  
public class CourseDaoImp extends BaseDaoImp<Course> implements CourseDao {
//筛选的解决方案 1.直接写HQL进行删选 或者  2.先选出全部再在service中进行筛选
    @Override
    public List<Course> findByTitle(String title) {
        //这种写法有被sql注入的风险
        //String hql = "from Course c where c.title like"+ '%'+title+'%';
        String hql = "from Course c where c.title like :partOfTitle";
        Query query = this.getSession().createQuery(hql);  
        query.setString("partOfTitle", "%"+title+"%");
        return query.list(); 
    }

    public List<Course> findBy2SessionTime(Date startDate, Date endDate) {//Object... params
        //Date startDate, Date endDate
        String hql = "from Course c Left Join fetch c.courseSessionCollection v where v.startDate > :startDate and v.endDate < :endDate";
        Query query = this.getSession().createQuery(hql);  
      
        query.setDate("startDate", startDate);  
        query.setDate("endDate", endDate);
       
        return query.list(); 
    }

    @Override
    public List<Course> findBySessionLocation(Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
}

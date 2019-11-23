/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;


import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import fr.utbm.repository.CourseDaoImp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author wuying
 */

@Service
public class CourseService {
    
    public List<Course> findByTitle(String title){
        CourseDaoImp courseDao = new CourseDaoImp();
        return courseDao.findByTitle(title);
    }
    
    public List<Course> findBy2SessionTime(Date startDate, Date endDate){
        CourseDaoImp courseDao =new CourseDaoImp();
        return courseDao.findBy2SessionTime(startDate, endDate);
    }
    
    public List<Course> findBySessionLocation(Location location){
        CourseDaoImp courseDao=new CourseDaoImp();
        return courseDao.findBySessionLocation(location);
    }
    
    public void save(Course course){
        CourseDaoImp courseDao = new CourseDaoImp();
        courseDao.save(course);
    }
    
    public void update (Course course){
        CourseDaoImp courseDao = new CourseDaoImp();
        courseDao.update(course);
    }
    
    public void delete(Serializable id){
        CourseDaoImp courseDao = new CourseDaoImp();
        courseDao.delete(id);
    }
    
    public Course findById(Serializable id){
        CourseDaoImp courseDao = new CourseDaoImp();
        return courseDao.findById(id);       
    }
    public List<Course> findAll(){
      CourseDaoImp courseDao = new CourseDaoImp();
      return courseDao.findAll();
    }
}

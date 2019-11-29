/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.service;

import fr.utbm.entity.CourseSession;
import fr.utbm.repository.CourseSessionDaoImp;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author wuying
 */
@Service
public class CourseSessionService {
    @Resource(name="courseSessionDao")
    public CourseSessionDaoImp courseSessionDao;
    public void save(CourseSession courseSession){
//        CourseSessionDaoImp courseSessionDao = new CourseSessionDaoImp();
        courseSessionDao.save(courseSession);
    }
    
    public void update (CourseSession courseSession){
//        CourseSessionDaoImp courseSessionDao = new CourseSessionDaoImp();
        courseSessionDao.update(courseSession);
    }
    
    public void delete(Serializable id){
//        CourseSessionDaoImp courseSessionDao = new CourseSessionDaoImp();
        courseSessionDao.delete(id);
    }
    
    public CourseSession findById(Serializable id){
//        CourseSessionDaoImp courseSessionDao = new CourseSessionDaoImp();
        return courseSessionDao.findById(id);       
    }
    public List<CourseSession> findAll(){
//      CourseSessionDaoImp courseSessionDao = new CourseSessionDaoImp();
      return courseSessionDao.findAll();
    }
}

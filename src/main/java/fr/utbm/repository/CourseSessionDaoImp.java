/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.CourseSession;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wuying
 */
@Repository(value = "CourseSession")  
public class CourseSessionDaoImp extends BaseDaoImp<CourseSession> 
        implements CourseSessionDao {
    
    
}

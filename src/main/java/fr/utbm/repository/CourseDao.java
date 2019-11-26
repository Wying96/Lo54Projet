/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import java.util.List;
import java.util.Date;

/**
 *
 * @author wuying
 */
public interface CourseDao  {
    
    public List<Course> findByTitle(String title);
    
    //public List<Course> findBy2SessionTime(Object... params);
    public List<Course> findBy2SessionTime(Date stratDate, Date endDate);
    public List<Course> findBySessionLocation(Location location);
    public List<Course> findByOneDayDispon(Date dateDispon);
    public List<Course> findByMultiCcondition(String title, Date startDate,
            Date endDate, Integer locationId);

}

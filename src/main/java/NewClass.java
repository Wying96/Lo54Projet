
import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import fr.utbm.repository.CourseDaoImp;
import fr.utbm.repository.CourseSessionDaoImp;
import fr.utbm.repository.LocationDaoImp;
import fr.utbm.tools.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wuying
 */
public class NewClass {
    public static void main(String[] args) {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        
//LocationDaoImp lp=new LocationDaoImp();
//    List<Location> ls = lp.findAll();
//    
//    for(Location l: ls){
//    System.out.println(l.toString());
//    
//}
//System.exit(0);
 String str = "2019/11/5 12:12:12";
 String str2="2019/11/18 12:12:12";
  SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
 Date d=new Date();
        try {
            d = sdf.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
 Date e=new Date();
        try {
            e = sdf.parse(str2);
        } catch (ParseException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }

CourseDaoImp lp=new CourseDaoImp();
List<Course> ls=lp.findBy2SessionTime(d, e);
 for(Course l: ls){
    System.out.println(l.getId());
    
}
System.exit(0);

    }
}

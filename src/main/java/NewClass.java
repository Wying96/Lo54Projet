
import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import fr.utbm.repository.BaseDao;
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
//测试搜索一个时间段之内的可用的课程
// String str = "2019/11/5 12:12:12";
// String str2="2019/11/18 12:12:12";
//  SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
// Date d=new Date();
//        try {
//            d = sdf.parse(str);
//        } catch (ParseException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
// Date e=new Date();
//        try {
//            e = sdf.parse(str2);
//        } catch (ParseException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//CourseDaoImp lp=new CourseDaoImp();
//List<Course> ls=lp.findBy2SessionTime(d, e);
// for(Course l: ls){
//    System.out.println(l.getId());
//    
//}
//System.exit(0);
//
//测试按照上课的地址搜索课程
//LocationDaoImp lp=new LocationDaoImp();
//Location lo=lp.findById(2);
//CourseDaoImp lp2=new CourseDaoImp();
//List<Course> ls=lp2.findBySessionLocation(lo);
// for(Course l: ls){
//    System.out.println(l.getId());
//    
//}
//测试按照一个时间搜课程
        CourseDaoImp lp2 = new CourseDaoImp();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDispon = new Date();
        try {
            dateDispon = sdf.parse("2019-11-10"); // 之後要看 controller 層傳入的數據了
        } catch (ParseException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Course> lcs = lp2.findByOneDayDispon(dateDispon);
        for (Course c : lcs) {
            System.out.println(c.toString());

        }

        
        
        System.exit(0);
    }
}

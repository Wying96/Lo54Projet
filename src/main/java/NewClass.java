
import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import fr.utbm.entity.Users;
import fr.utbm.entity.CourseSession;

import fr.utbm.repository.BaseDao;
import fr.utbm.repository.CourseDaoImp;
import fr.utbm.repository.CourseSessionDaoImp;
import fr.utbm.repository.LocationDaoImp;
import fr.utbm.repository.UsersDao;
import fr.utbm.repository.UsersDaoImp;
import fr.utbm.service.LocationService;
import fr.utbm.service.UsersService;
import fr.utbm.tools.HibernateUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//        CourseDaoImp lp2 = new CourseDaoImp();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateDispon = new Date();
//        try {
//            dateDispon = sdf.parse("2019-11-10"); // 之後要看 controller 層傳入的數據了
//        } catch (ParseException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
////        List<Course> lcs = lp2.findByOneDayDispon(dateDispon);
////        for (Course c : lcs) {
////            System.out.println(c.toString());
////
////        }
//        
//        
//        List<Course> lcs2 = lp2.findByMultiCcondition("a", null, null, null);
//        for (Course c : lcs2) {
//            System.out.println(c.toString());
//
//        }
//        UsersDaoImp uDao = new UsersDaoImp();
//        Users u = uDao.findById(2);
//        
//        for (CourseSession c : u.getInscriptions() ) {
//            System.out.println(c.toString());
//
//        }
//        
//        Users u2 = new Users();
//        u2.setFirstname("1111111");
//        u2.setLastname("44sahdgjh");
//        u2.setPassword("44sahdgjh");
//        u2.setAddress("44sdada");
//        u2.setEmail("111111111");
//        u2.setPhone("1111111111");
        
//        u2.setPhone("44ssdaddsds");
//        UsersService user = new UsersService();
////        if (user.checkLogin("eeeee@eeee.fr", "123456")) {
////
////            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
////        } else {
////            System.out.println("shibai l ");
////        };
////        System.out.println("错误的账户： ");
////        if (user.checkLogin("eeeee@eeee.com", "123456")) {
////
////            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
////        } else {
////            System.out.println("搞错了！！！");
////        };
////
//        user.inscrirSession(11, 1);
//
//        user.inscrirSession(11, 2);
//
//        user.inscrirSession(11, 3);
//
//        user.inscrirSession(11, 4);
//        
////        Users u = user.findByEmail("wuyu");
//        
//        Users u = user.findById(11);
//        if(u!=null)
//        {
//            System.out.println(u.toString());
//            
//            System.out.println("这个人的课程有： ");
//        }
//        
//        
//        System.out.println("-----------------------------------------------------------------------------------");
//        Users u2 = user.findByEmail("eeeee@eeee.fr");
//        if(u2!=null)
//        {
//            System.out.println(u2.toString());
//            
//            System.out.println("u2人的课程有： ");
//            for(CourseSession cs: u2.){
//                System.out.println(cs.toString());
//            }
//        }
        
//       CourseSessionDaoImp csDao = new CourseSessionDaoImp();
//       CourseSession cs =(CourseSession) csDao.findById((Serializable)2);
//       System.out.println(cs.toString());


//        
//        UsersDaoImp uDao = new UsersDaoImp();
//        Users u2 = uDao.findById(7);
//        
//        CourseSessionDaoImp csDao = new CourseSessionDaoImp();
//        CourseSession e2 = csDao.findById(2);
//        CourseSession e3 = csDao.findById(3);
//        ArrayList<CourseSession> lcs = new ArrayList<CourseSession>();
//        lcs.add(e2);
//        lcs.add(e3);
//        
//        u2.setCourseSessionCollection(lcs);
//        uDao.update(u2);
//        
//        if(u2!=null)
//        {
//            System.out.println(u2.toString());
//            
//            System.out.println("u2人的课程有： ");
//            for(CourseSession cs: u2.getCourseSessionCollection()){
//                System.out.println(cs.toString());
//            }
//        }

          UsersService us = new UsersService();
          us.inscrirSession(33, 2);
          us.inscrirSession(33, 3);
          us.inscrirSession(33, 5);
          us.inscrirSession(33, 6);
          
          us.inscrirSession(30, 3);
          us.inscrirSession(30, 5);
          


//        
//        for(CourseSession cs: lcs){
//            
//        }

//        Users lcs2 = usersImp.findByEmail("eeeee@eeee.fr");
//            System.out.println(lcs2.toString());
//        Users lcs2 = usersImp.findByEmail("eeeee@eeee.fr");
//            System.out.println(lcs2.toString());
        System.exit(0);
    }
}

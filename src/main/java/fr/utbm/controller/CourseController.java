/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.controller;

import fr.utbm.entity.Course;
import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Location;
import fr.utbm.entity.Users;
import fr.utbm.service.ClientService;
import fr.utbm.service.CourseService;
import fr.utbm.service.CourseSessionService;
import fr.utbm.service.LocationService;
import fr.utbm.service.UsersService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wuying
 */
@Controller
public class CourseController {

    private static Logger logger = Logger.getLogger(CourseController.class);

    @Autowired
    public CourseService courseService;
    @Autowired
    public LocationService locationService;
    @Autowired
    public CourseSessionService courseSessionService;
    @Autowired
    public ClientService clientService;
    @Autowired
    public UsersService usersService;

    //if courseList = null return all courses
    //
    public ModelAndView courseSessionPage(Users u, String searchName, List<Course> courseList,
            String searchBeginDate,String searchEndDate, String searchLocation) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", u);
        if(courseList==null){
            List<Course> coursesList = courseService.findAll();
            modelAndView.addObject("coursesList", coursesList);
        }else{
            modelAndView.addObject("coursesList", courseList);
        }
        List<Location> locations = locationService.findAll();
        modelAndView.addObject("lists", locations);
        modelAndView.setViewName("coursesession");
        
        modelAndView.addObject("searchName", searchName);
        modelAndView.addObject("searchBeginDate", searchBeginDate);
        modelAndView.addObject("searchEndDate", searchEndDate);
        modelAndView.addObject("searchLocation", searchLocation);
        
        return modelAndView;
    }

    @RequestMapping(value = "login")
    public ModelAndView validerCompte(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("accountNo");
        String password = request.getParameter("pwd");

        boolean isValidUser = usersService.checkLogin(userName, password);
        if (!isValidUser) {
            return new ModelAndView("home", "error", "le mot de passe ou identifiant est incorrect!");
        } else {
            Users u = usersService.findByEmail(userName);
            
            return this.courseSessionPage(u, null, null, 
                    null, null, null);
           
        }
    }

    @RequestMapping(value = "/")
    public ModelAndView loginPage() {

        return new ModelAndView("home");
    }

    @RequestMapping(value = "searchMultiCondition")
    public ModelAndView searchMultiCondition(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String parteOfTitle = request.getParameter("title");
        String dateStart = request.getParameter("date1");
        String dateEnd = request.getParameter("date2");
        String locaId = request.getParameter("city");
        Integer locationId = (locaId != "") ? Integer.parseInt(locaId) : null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date endDate = new Date();
        if (dateStart != "") {
            try {
                startDate = sdf.parse(dateStart);
            } catch (ParseException ex) {
            }
        } else {
            startDate = null;
        }
        if (dateEnd != "") {
            try {
                endDate = sdf.parse(dateEnd);
            } catch (ParseException ex) {
            }
        } else {
            endDate = null;
        }
        Users u = usersService.findByEmail(email);

        List<Course> coursesResults = courseService.findByMultiCcondition(
                parteOfTitle, startDate, endDate, locationId);
        return this.courseSessionPage(u, parteOfTitle, coursesResults, 
                dateStart, dateEnd, locaId);
    }

    public String delSpace(String str) throws Exception {
        if (str == null) {
            return null;
        }
        String regStartSpace = "^[　 ]*";
        String regEndSpace = "[　 ]*$";
        String strDelSpace = str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");
        return strDelSpace;
    }

    //Action of POST pre-inscrire
    @RequestMapping(value = "inscrireCourse")
    public ModelAndView inscrireCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String courseId = this.delSpace(request.getParameter("courseSessionId"));
        int sessionId = Integer.parseInt(courseId);
        Users u = usersService.findByEmail(email);
        CourseSession cs = courseSessionService.findById(sessionId);
        if (u.getCourseSessionCollection().contains(cs)) {

             return this.courseSessionPage(u, null, null, 
                    null, null, null);
        }
        usersService.inscrirSession(email, sessionId);
        Users uNew = usersService.findByEmail(email);

         return this.courseSessionPage(uNew, null, null, 
                    null, null, null);
    }

    @RequestMapping(value = "/inscrireclient")
    public ModelAndView inscrireClient(HttpServletRequest request, HttpServletResponse response) {
//      Integer id=Integer.parseInt(request.getParameter("coursesessionid"));
        String pwd = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        boolean result = usersService.checkEmailAvalible(email);
        if (result == true) {
            Users u = new Users();
            u.setPhone(phone);
            u.setAddress(address);
            u.setFirstname(firstname);
            u.setEmail(email);
            u.setLastname(lastname);
            u.setPassword(pwd);
            usersService.save(u);

            return this.courseSessionPage(u, null, null, 
                    null, null, null);

        } else {
            return new ModelAndView("inscrire", "msg", "email déjà existe");
        }

    }

    @RequestMapping(value = "/inscrire")
    public ModelAndView inscrire(HttpServletRequest request, HttpServletResponse response) {
//     
        return new ModelAndView("inscrire");
    }

}

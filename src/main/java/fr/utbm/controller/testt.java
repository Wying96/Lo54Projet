/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.controller;

import fr.utbm.entity.Client;
import fr.utbm.entity.Course;
import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Location;
import fr.utbm.entity.Users;
import fr.utbm.service.ClientService;
import fr.utbm.service.CourseService;
import fr.utbm.service.CourseSessionService;
import fr.utbm.service.LocationService;
import fr.utbm.service.UsersService;
import java.io.IOException;
import java.io.PrintWriter;
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
public class testt {

    private static Logger logger = Logger.getLogger(testt.class);

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

//     @RequestMapping(value="/fuck") //用于配制url路径
//     public String main(HttpServletRequest request,HttpServletResponse response){
//        		
//		 List<Course> cs = courseService.findByTitle("a");
//        request.setAttribute("cours", cs);
//
//        request.setAttribute("message", "Hello WU Ying! \n 测试service返回数据:");
//
//    return "location";}
    @RequestMapping(value = "/")
    public ModelAndView loginPage() {

        return new ModelAndView("home");
    }

    @RequestMapping(value = "/searchMultiCondition")
    public ModelAndView searchMultiCondition(HttpServletRequest request, HttpServletResponse response) {
        String parteOfTitle = request.getParameter("title");
        String dateStart = request.getParameter("date1");
        String dateEnd = request.getParameter("date2");
//        String locaId = request.getParameter("locationId");
        String locaId = request.getParameter("city");
        Integer locationId = (locaId != "") ? Integer.parseInt(locaId) : null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date endDate = new Date();

        if (dateStart != "") {
            try {
                startDate = sdf.parse(dateStart);
            } catch (ParseException ex) {
                //java.util.logging.Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            startDate = null;
        }
        if (dateEnd != "") {
            try {
                endDate = sdf.parse(dateEnd);
            } catch (ParseException ex) {
                //java.util.logging.Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            endDate = null;
        }

        List<Course> coursesResults = courseService.findByMultiCcondition(parteOfTitle, startDate, endDate, locationId);
        request.setAttribute("coursesList", coursesResults);
        List<Location> locations = locationService.findAll();
        locations.add(0, new Location(null, null));
        request.setAttribute("lists", locations);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coursesession");
        modelAndView.addObject("lists", locations);
        modelAndView.addObject("coursesList", coursesResults);
        return modelAndView;
    }

    
    public String delSpace(String str) throws Exception {  
        if (str == null) {  
            return null;  
        }  
        String regStartSpace = "^[　 ]*";  
        String regEndSpace = "[　 ]*$";  
        // 连续两个 replaceAll   
        // 第一个是去掉前端的空格， 第二个是去掉后端的空格   
        String strDelSpace = str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");  
        return strDelSpace;  
    }
    
    @RequestMapping(value = "inscrireCourse")
    public ModelAndView inscrireCourse(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String courseId = this.delSpace(request.getParameter("courseSessionId"));
        int sessionId = Integer.parseInt(courseId);
        //user inscrire session 
        usersService.inscrirSession(email, sessionId);

        Users u = usersService.findByEmail(email);

        List<Course> coursesList = courseService.findAll();
        List<Location> locations = locationService.findAll();
        locations.add(0, new Location(null, null));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", u);
        modelAndView.addObject("lists", locations);
        modelAndView.addObject("coursesList", coursesList);
        modelAndView.setViewName("coursesession");
        return modelAndView;
    }
    

    @RequestMapping(value = "/registrersession")
    public String registrerSession(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("coursesessionid"));

//        courseService.fin
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
//        CourseSessionService courseSessionService=new CourseSessionService();
        CourseSession cs = new CourseSession();
        cs = courseSessionService.findById(id);
        ClientService clientservice = new ClientService();
        Client client = new Client();
        client.setPhone(phone);
        client.setAddress(address);
        client.setCourseSessionId(cs);
        client.setFirstname(firstname);
        client.setEmail(email);
        client.setLastname(lastname);
        clientservice.save(client);
        return "redirect:/";
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
            CourseSessionService courseSessionService = new CourseSessionService();

            Users u = new Users();
            u.setPhone(phone);
            u.setAddress(address);
            u.setFirstname(firstname);
            u.setEmail(email);
            u.setLastname(lastname);
            u.setPassword(pwd);
            usersService.save(u);
//        request.setAttribute("userEmail", email);
            request.getSession().setAttribute("user", u);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", u);
            List<Course> coursesList = courseService.findAll();

            List<Location> locations = locationService.findAll();
            modelAndView.addObject("lists", locations);
            modelAndView.addObject("coursesList", coursesList);
            modelAndView.setViewName("coursesession");

            return modelAndView;

        } else {
            //request.getSession().setAttribute("msg", "error");
            return new ModelAndView("inscrire", "msg", "email déjà existe");
        }

    }

    @RequestMapping(value = "/inscrire")
    public ModelAndView inscrire(HttpServletRequest request, HttpServletResponse response) {
//     
        return new ModelAndView("inscrire");
    }

    @RequestMapping(value = "login")
    public ModelAndView validerCompte(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("accountNo");
        String password = request.getParameter("pwd");

        boolean isValidUser = usersService.checkLogin(userName, password);
        if (!isValidUser) {
//            request.setAttribute("msg", "le mot de passe ou identifiant est incorrecte!");

            return new ModelAndView("home", "error", "le mot de passe ou identifiant est incorrecte");
        } else {

            Users u = usersService.findByEmail(userName);

            List<Course> coursesList = courseService.findAll();

            List<Location> locations = locationService.findAll();

            locations.add(0, new Location(null, null));
//            request.setAttribute("lists", locations);
//            request.setAttribute("coursesList", coursesList);

            request.getSession().setAttribute("user", u);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("lists", locations);
            modelAndView.addObject("coursesList", coursesList);
            modelAndView.setViewName("coursesession");
            return modelAndView;

        }
    }

}

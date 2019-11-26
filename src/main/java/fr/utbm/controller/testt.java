/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.controller;

import fr.utbm.entity.Course;
import fr.utbm.entity.Location;
import fr.utbm.service.CourseService;
import fr.utbm.service.LocationService;
import java.util.List;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author wuying
 */
@Controller
public class testt {
    
    private static Logger logger = Logger.getLogger(testt.class);
    
     @Autowired
    public CourseService courseService;

//     @RequestMapping(value="/fuck") //用于配制url路径
//     public String main(HttpServletRequest request,HttpServletResponse response){
//        		
//		 List<Course> cs = courseService.findByTitle("a");
//        request.setAttribute("cours", cs);
//
//        request.setAttribute("message", "Hello WU Ying! \n 测试service返回数据:");
//
//    return "location";}
     @RequestMapping(value="/")
	public String main(HttpServletRequest request,HttpServletResponse response){
        		
		List<Course> users =courseService.findByTitle("a");
		request.setAttribute("users", users);
		
		return "location";
	}
//    public String printHello(ModelMap model){
////使用Service返回数据成功
//        List<Course> cs = courseService.findByTitle("a");
//        model.addAttribute("cours", cs);
//
//        model.addAttribute("message", "Hello WU Ying! \n 测试service返回数据:");
//
//    return "location";  //hello 是页面模版的名字 hello.jsp
//        
//    }
    
    @RequestMapping(value="/searchName")
	public String searchName(HttpServletRequest request ,HttpServletResponse response){
				
		String userName = request.getParameter("title");						
		List<Course> users = courseService.findByTitle(userName);
		request.setAttribute("users", users);
		
		return "location";
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.controller;

import fr.utbm.entity.Course;
import fr.utbm.repository.CourseDaoImp;
import fr.utbm.service.CourseService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author c
 */
@Controller
public class CoursController {
     @RequestMapping(value="/courses",method=RequestMethod.GET) //用于配制url路径
    public String printHello(ModelMap model){
//使用Service返回数据成功
        CourseService courseService = new CourseService();
        List<Course> cs = courseService.findByTitle("base de donnees");
        model.addAttribute("courses",cs);  
    return "courses";  //hello 是页面模版的名字 hello.jsp
        
    }
}
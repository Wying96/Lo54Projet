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
 * @author wuying
 */
@Controller
public class HelloController {
    @RequestMapping(value="/wu",method=RequestMethod.GET) //用于配制url路径
    public String printHello(ModelMap model){
//        CourseService courseService = new CourseService();
//        String test = courseService.findByTitle("LO51").toString();
    CourseDaoImp coursDao = new CourseDaoImp();
    List<Course> cs = coursDao.findByTitle("test");
    model.addAttribute("message","Hello WU Ying! \n 测试service返回数据:");
    
    model.addAttribute("cours",cs);
    

    return "hello";  //hello 是页面模版的名字 hello.jsp
        
    }
}

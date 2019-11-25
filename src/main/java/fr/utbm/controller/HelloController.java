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
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author wuying
 */
@Controller
public class HelloController {
     @Autowired
    public CourseService courseService;
      
    @RequestMapping(value="/wu.do",method=RequestMethod.GET) //用于配制url路径
    public String printHello(ModelMap model){
// 使用courseDAO返回数据成功
//    CourseDaoImp coursDao = new CourseDaoImp();
//    List<Course> cs = coursDao.findByTitle("test");
//    model.addAttribute("cours",cs);

//使用Service返回数据成功
//        CourseService courseService = new CourseService();
        List<Course> cs = courseService.findByTitle("a");
        model.addAttribute("cours",cs);

    model.addAttribute("message","Hello WU Ying! \n 测试service返回数据:");
    
    

    return "hello";  //hello 是页面模版的名字 hello.jsp
        
    }
}

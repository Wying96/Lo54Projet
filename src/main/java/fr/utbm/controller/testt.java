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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author wuying
 */
@Controller
public class testt {
     @RequestMapping(value="/fuck.do",method=RequestMethod.GET) //用于配制url路径
    public String printHello(ModelMap model){
//使用Service返回数据成功
       LocationService l=new LocationService();
        List<Location> cs = l.findAll();
        model.addAttribute("courses",cs);  
    return "location";  //hello 是页面模版的名字 hello.jsp
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.controller;

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
        model.addAttribute("message","Hello WU Ying!");
        return "hello";  //hello 是页面模版的名字 hello.jsp
        
    }
}

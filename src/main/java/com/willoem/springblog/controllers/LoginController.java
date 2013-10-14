
package com.willoem.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(ModelMap map){
        return "login";
    }
    
    @RequestMapping(value="/loginfailed", method=RequestMethod.GET)
    public String loginError(ModelMap map){
        map.addAttribute("error", "true");
        return "login";
    }
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(ModelMap map){
        map.addAttribute("logout", "true");
        return "login";
    }
}

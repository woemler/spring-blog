
package me.woemler.springblog.controllers;

import me.woemler.springblog.repositories.BlogService;
import me.woemler.springblog.repositories.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminBaseController {

    @Autowired
    private BlogService blogService;
    
    @Autowired
    private TagService tagService;
    
    //Admin Home
    @RequestMapping(value="/admin")
    public String admin(ModelMap map){
        Long postCount = blogService.count();
        Long tagCount = tagService.count();
        map.addAttribute("postCount", postCount);
        map.addAttribute("tagCount", tagCount);
        return "admin/admin";
    }
    
}

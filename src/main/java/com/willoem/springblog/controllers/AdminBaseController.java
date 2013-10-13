
package com.willoem.springblog.controllers;

import com.willoem.springblog.services.BlogService;
import com.willoem.springblog.services.TagService;
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
        Integer postCount = blogService.getBlogPostCount();
        Integer tagCount = tagService.getTagCount();
        map.addAttribute("postCount", postCount);
        map.addAttribute("tagCount", tagCount);
        return "admin/admin";
    }
    
}

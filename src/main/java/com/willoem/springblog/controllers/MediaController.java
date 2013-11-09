
package com.willoem.springblog.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletContext;

@Controller
public class MediaController {
    
    @Autowired
    private ServletContext servletContext;
    
    @RequestMapping(value="/media")
    public String media(ModelMap map){
        
        //Get images for thumbnails
        String contextPath = servletContext.getContextPath();
        String staticDir = "/resources/img/public/";
        String staticPath = servletContext.getRealPath(staticDir);
        List<String> images = new ArrayList();

        for (String file: Arrays.asList(new File(staticPath).list())){
            if (file.matches("[a-zA-Z0-9._-]+\\.(jpg|png|gif|svg)$")){
                images.add(contextPath + staticDir + file);
            }
        }
        map.addAttribute("images", images);

        return "media";
    }
    
}


package me.woemler.springblog.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author woemler 
 * 
 * This controller class handles requests for special media pages, such as a gallery of public image
 *   thumbnails.
 */

@Controller
public class MediaController {

    private static Logger logger  = LoggerFactory.getLogger(MediaController.class);
    
    @Autowired
    private ServletContext servletContext;
    
    @RequestMapping(value="/media")
    public String media(ModelMap map){
        String contextPath = servletContext.getContextPath();
        String staticDir = "/static/img/public/";
        String staticPath = servletContext.getRealPath(staticDir);
        List<String> images = new ArrayList();
        File staticFileDir = new File(staticPath);
        if (staticFileDir.list() != null && staticFileDir.list().length > 0){
            for (String file: staticFileDir.list()){
                if (file.matches("[a-zA-Z0-9._-]+\\.(jpg|png|gif|svg)$")){
                    images.add(contextPath + staticDir + file);
                }
            }
        }
        map.addAttribute("images", images);
        return "media";
    }
    
}

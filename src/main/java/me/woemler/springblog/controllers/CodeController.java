
package me.woemler.springblog.controllers;

import me.woemler.springblog.services.GitHubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@Controller
public class CodeController {

    private static Logger logger  = LoggerFactory.getLogger(CodeController.class);
    
    @Autowired
    private GitHubService ghService;
    
    @Value("${github.username}")
    String githubUsername;
    
    @RequestMapping(value="/code", method=RequestMethod.GET)
    public String codePage(ModelMap map)
            throws MalformedURLException, Exception
    {
        List<Map<String,String>> repos = ghService.getReposByUser(githubUsername);
        map.addAttribute("repos", repos);
        return "code";
    }
    
}

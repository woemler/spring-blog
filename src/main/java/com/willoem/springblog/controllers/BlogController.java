
package com.willoem.springblog.controllers;

import com.willoem.springblog.models.BlogPost;
import com.willoem.springblog.models.Tag;
import com.willoem.springblog.services.BlogService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    
    //Home Page
    @Transactional
    @RequestMapping(value={"/", "", "/blog"}, method=RequestMethod.GET)
    public String home(ModelMap map, @RequestParam(value = "page", defaultValue = "1") Integer page){
        
        Map<String,Object> pageData = blogService.getBlogPostsByPage(page);
        List<BlogPost> blogPosts = (List<BlogPost>) pageData.get("blogPosts");
        Map<Integer, Set<Tag>> tagMap = new HashMap();
        for (BlogPost post: blogPosts){
            Hibernate.initialize(post.getTags());
            tagMap.put(post.getPostId(), post.getTags());
        }
        Integer postCount = (Integer) pageData.get("postCount");
        Integer numPages = (Integer) pageData.get("numPages");
        
        map.addAttribute("currentPage", page);
        map.addAttribute("numPages", numPages);
        map.addAttribute("postCount", postCount);
        map.addAttribute("blogPosts", blogPosts);
        map.addAttribute("tagMap", tagMap);
        
        return "home";
    }
    
    //Blog Post
    @Transactional
    @RequestMapping(value="/blog/{slug}")
    public String post(ModelMap map, @PathVariable("slug") String slug){
        Map<String,BlogPost> requestedPosts = blogService.getBlogPostBySlug(slug);
        Hibernate.initialize(requestedPosts.get("requestedPost").getTags());
        Set<Tag> tags = requestedPosts.get("requestedPost").getTags();
        
        map.addAttribute("requestedPost", requestedPosts.get("requestedPost"));
        map.addAttribute("nextPost", requestedPosts.get("nextPost"));
        map.addAttribute("previousPost", requestedPosts.get("previousPost"));
        map.addAttribute("postTags", tags);
        
        return "blogPost";
    }
}
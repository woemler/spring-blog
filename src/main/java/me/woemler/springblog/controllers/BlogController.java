
package me.woemler.springblog.controllers;

import me.woemler.springblog.models.BlogPost;
import me.woemler.springblog.models.Tag;
import me.woemler.springblog.repositories.BlogService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    
    //Home Page
    @Transactional
    @RequestMapping(value={"/", "", "/blog"}, method=RequestMethod.GET)
    public String home(
        @PageableDefault(size = 5, sort = {"postDate"}, direction = Sort.Direction.DESC) Pageable pageable,
        ModelMap map
    ) {
        Page<BlogPost> posts = blogService.findPaged(pageable);
        Map<Integer, Set<Tag>> tagMap = new HashMap<>();
        for (BlogPost post: posts){
            Hibernate.initialize(post.getTags());
            tagMap.put(post.getPostId(), post.getTags());
        }
        map.addAttribute("currentPage", pageable.getPageNumber() + 1);
        map.addAttribute("numPages", posts.getTotalPages());
        map.addAttribute("postCount", posts.getTotalElements());
        map.addAttribute("blogPosts", posts.getContent());
        map.addAttribute("tagMap", tagMap);
        return "home";
    }
    
    //Blog Post
    @Transactional
    @RequestMapping(value="/blog/{slug}")
    public String post(ModelMap map, @PathVariable("slug") String slug){
        BlogPost post = blogService.findBySlug(slug);
        Hibernate.initialize(post.getTags());
        Set<Tag> tags = post.getTags();
        map.addAttribute("requestedPost", post);
        map.addAttribute("nextPost", null);
        map.addAttribute("previousPost", null);
        map.addAttribute("postTags", tags);
        return "blogPost";
    }
    
    //About page
    @RequestMapping(value="/about")
    public String about(ModelMap map){
        return "about";
    }
}

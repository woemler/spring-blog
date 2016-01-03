
package me.woemler.springblog.controllers;

import me.woemler.springblog.models.BlogPost;
import me.woemler.springblog.models.Tag;
import me.woemler.springblog.repositories.TagService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping(value = "/tags")
public class TagController {
    
    @Autowired
    private TagService tagService;
    
    @Transactional
    @RequestMapping(value="", method=RequestMethod.GET)
    public String listTags(ModelMap map){
        List<Tag> tags = tagService.findAll();
        Map<Integer,Integer> tagCounts = new HashMap<>();
        for (Tag tag: tags){
            Hibernate.initialize(tag.getBlogPosts());
            tagCounts.put(tag.getTagId(), tag.getBlogPosts().size());
        }
        
        map.addAttribute("tags", tags);
        map.addAttribute("tagCounts", tagCounts);
        return "tagList";
    }
    
    @Transactional
    @RequestMapping(value="/{tag}", method=RequestMethod.GET)
    public String tagDetails(ModelMap map, @PathVariable("tag") String tag){
        Tag tagObj = tagService.findByTagName(tag);
        Hibernate.initialize(tagObj.getBlogPosts());
        List<BlogPost> blogPosts = new ArrayList<>(tagObj.getBlogPosts());
        Map<Integer, Set<Tag>> tagMap = new HashMap<>();
        for (BlogPost post: blogPosts){
            Hibernate.initialize(post.getTags());
            tagMap.put(post.getPostId(), post.getTags());
        }
        
        map.addAttribute("tag", tagObj);
        map.addAttribute("blogPosts", blogPosts);
        map.addAttribute("tagMap", tagMap);
        return "tagDetails";
    }
    
}

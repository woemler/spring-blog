
package com.willoem.springblog.controllers;

import com.willoem.springblog.models.BlogPost;
import com.willoem.springblog.models.Tag;
import com.willoem.springblog.services.TagService;
import java.util.ArrayList;
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

@Controller
public class TagController {
    
    @Autowired
    private TagService tagService;
    
    @Transactional
    @RequestMapping(value="/tags", method=RequestMethod.GET)
    public String listTags(ModelMap map){
        List<Tag> tags = tagService.getAllTags();
        Map<Integer,Integer> tagCounts = new HashMap();
        for (Tag tag: tags){
            Hibernate.initialize(tag.getBlogPosts());
            tagCounts.put(tag.getTagId(), tag.getBlogPosts().size());
        }
        
        map.addAttribute("tags", tags);
        map.addAttribute("tagCounts", tagCounts);
        return "tagList";
    }
    
    @Transactional
    @RequestMapping(value="/tags/{tag}", method=RequestMethod.GET)
    public String tagDetails(ModelMap map, @PathVariable("tag") String tag){
        Tag tagObj = tagService.getTagByName(tag);
        Hibernate.initialize(tagObj.getBlogPosts());
        List<BlogPost> blogPosts = new ArrayList(tagObj.getBlogPosts());
        Map<Integer, Set<Tag>> tagMap = new HashMap();
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

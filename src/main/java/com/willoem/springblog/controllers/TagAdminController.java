
package com.willoem.springblog.controllers;

import com.willoem.springblog.models.Tag;
import com.willoem.springblog.services.TagService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("tag")
public class TagAdminController {
    
    @Autowired
    private TagService tagService;
    
    @ModelAttribute("tag")
    public Tag getTag(){
        return new Tag();
    }
    
    @Transactional
    @RequestMapping(value="/admin/tags", method=RequestMethod.GET)
    public String adminTagList(ModelMap map, SessionStatus status){
        status.setComplete();
        List<Tag> tags = tagService.getAllTags();
        Map<Integer,Integer> tagCounts = new HashMap();
        for (Tag tag: tags){
            Hibernate.initialize(tag.getBlogPosts());
            tagCounts.put(tag.getTagId(), tag.getBlogPosts().size());
        }
        
        map.addAttribute("tags", tags);
        map.addAttribute("tagCounts", tagCounts);
        return "admin/tagList";
    }
    
    @RequestMapping(value="/admin/tags/new", method=RequestMethod.GET)
    public String newTag(ModelMap map){
        Tag tag = new Tag();
        map.addAttribute("tag", tag);
        return "admin/editTag";
    }
    
    @RequestMapping(value="/admin/tags/new", method=RequestMethod.POST)
    public String saveTag(@Valid @ModelAttribute Tag tag, 
            BindingResult result, 
            SessionStatus status)
    {
        if (result.hasErrors()){
            return "admin/editTag";
        }
        else {
            tagService.saveTag(tag);
            status.setComplete();
            return "redirect:/admin/tags";
            
        }
    }
    
    //Delete tag
    @RequestMapping(value="/admin/delete/tags/{id}", method=RequestMethod.POST)
    public @ResponseBody String deleteTag(@PathVariable("id") Integer id, SessionStatus status){
        tagService.deleteTag(id);
        status.setComplete();
        return "The item was deleted succesfully";
    }
    
    @RequestMapping(value="/admin/tags/cancel", method=RequestMethod.GET)
    public String cancelTagEdit(SessionStatus status){
        status.setComplete();
        return "redirect:/admin/tags";
    }
    
}


package me.woemler.springblog.controllers;

import me.woemler.springblog.models.Tag;
import me.woemler.springblog.repositories.TagService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/tags")
@SessionAttributes("tag")
public class TagAdminController {
    
    @Autowired
    private TagService tagService;
    
    @ModelAttribute("tag")
    public Tag getTag(){
        return new Tag();
    }
    
    @Transactional
    @RequestMapping(value="", method=RequestMethod.GET)
    public String adminTagList(ModelMap map, SessionStatus status){
        status.setComplete();
        List<Tag> tags = tagService.findAll();
        Map<Integer,Integer> tagCounts = new HashMap<>();
        for (Tag tag: tags){
            Hibernate.initialize(tag.getBlogPosts());
            tagCounts.put(tag.getTagId(), tag.getBlogPosts().size());
        }
        
        map.addAttribute("tags", tags);
        map.addAttribute("tagCounts", tagCounts);
        return "admin/tagList";
    }
    
    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newTag(ModelMap map){
        Tag tag = new Tag();
        map.addAttribute("tag", tag);
        return "admin/editTag";
    }
    
    @RequestMapping(value="/new", method=RequestMethod.POST)
    public String saveTag(@Valid @ModelAttribute Tag tag, 
            BindingResult result, 
            SessionStatus status)
    {
        if (result.hasErrors()){
            return "admin/editTag";
        }
        else {
            tagService.save(tag);
            status.setComplete();
            return "redirect:/admin/tags";
            
        }
    }
    
    //Delete tag
    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String deleteTag(@PathVariable("id") Integer id, SessionStatus status){
        tagService.delete(id);
        status.setComplete();
        return "The item was deleted succesfully";
    }
    
    @RequestMapping(value="/cancel", method=RequestMethod.GET)
    public String cancelTagEdit(SessionStatus status){
        status.setComplete();
        return "redirect:/admin/tags";
    }

    //AJAX event for autocomplete
    @RequestMapping(value="/autocomplete", method=RequestMethod.POST)
    public @ResponseBody List<String> tagsAutocomplete(@RequestParam("fragment") String fragment)
        throws UnsupportedEncodingException
    {
        if (fragment.isEmpty()){
            return null;
        }
        else {
            return tagService.findTagNamesByFragment(fragment);
        }
    }
    
}

package me.woemler.springblog.controllers;

import me.woemler.springblog.models.BlogPost;
import me.woemler.springblog.models.Tag;
import me.woemler.springblog.repositories.BlogService;
import me.woemler.springblog.repositories.TagService;
import org.hibernate.Hibernate;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin/blog")
@SessionAttributes("blogPost")
public class BlogAdminController {
    
    @Autowired
    private BlogService blogService;
    
    @Autowired 
    private TagService tagService;
    
    @ModelAttribute("blogPost")
    public BlogPost getBlogPost(){
        return new BlogPost();
    }
    
    //List Blog Posts
    @RequestMapping(value="", method=RequestMethod.GET)
    public String blogAdmin(ModelMap map, SessionStatus status){
        status.setComplete();
        List<BlogPost> postList = blogService.findAll();
        map.addAttribute("postList", postList);
        return "admin/blogPostList";
    }
    
    //Add new blog post
    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newPost(ModelMap map){
        BlogPost blogPost = new BlogPost();
        map.addAttribute("blogPost", blogPost);
        return "admin/editBlogPost";
    }
    
    //Save new post
    @RequestMapping(value="/new", method=RequestMethod.POST)
    public String addPost(@Valid @ModelAttribute BlogPost blogPost, 
            BindingResult result, 
            @RequestParam(value="tagString", defaultValue="") String tagString, 
            @RequestParam(value="enablePegdown", required=false) boolean enablePegdown,
            Model model, 
            SessionStatus status)
    {
        if (result.hasErrors()){
            return "admin/editBlogPost";
        }
        else {
            Set<Tag> tagSet = new HashSet();
            
            for (String tag: tagString.split(",")){
                tag = tag.replaceAll("\\s+", "");
                if (tag.equals("") || tag == null){
                    //pass
                }
                else {
                    //Check to see if the tag exists
                    Tag tagObj = tagService.findByTagName(tag);
                    //If not, add it
                    if (tagObj == null){
                        tagObj = new Tag();
                        tagObj.setTagName(tag);
                        tagService.save(tagObj);
                    }
                    tagSet.add(tagObj);
                }
            }
            
            //Pegdown
            if (enablePegdown){
                blogPost.setMarkup(new PegDownProcessor().markdownToHtml(blogPost.getMarkup()));
            }
            
            blogPost.setPostDate(new Date());
            blogPost.setTags(tagSet);
            blogService.save(blogPost);
            
            status.setComplete();
            
            return "redirect:/admin/blog";

        }
    }
    
    //Edit existing blog post
    @Transactional
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String editPost(ModelMap map, @PathVariable("id") Integer postId){
        BlogPost blogPost = blogService.findById(postId);
        map.addAttribute("blogPost", blogPost);
        Hibernate.initialize(blogPost.getTags());
        Set<Tag> tags = blogPost.getTags();
        String tagString = "";
        for (Tag tag: tags){
            tagString = tagString + " " + tag.getTagName();
        }
        tagString = tagString.trim();
        map.addAttribute("tagString", tagString);
        
        return "admin/editBlogPost";
    }
    
    //Update post
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public String savePostChanges(
            @Valid @ModelAttribute BlogPost blogPost, 
            BindingResult result, 
            @RequestParam(value="tagString", defaultValue="") String tagString, 
            @RequestParam("enablePegdown") boolean enablePegdown,
            Model model, SessionStatus status)
    {
        if (result.hasErrors()){
            return "admin/editBlogPost";
        }
        else {
            Set<Tag> tagSet = new HashSet();
            
            for (String tag: tagString.split(",")){
                tag = tag.replaceAll("\\s+", "");
                if (tag.equals("") || tag == null){
                    //pass
                }
                else {
                    //Check to see if the tag exists
                    Tag tagObj = tagService.findByTagName(tag);
                    //If not, add it
                    if (tagObj == null){
                        tagObj = new Tag();
                        tagObj.setTagName(tag);
                        tagService.save(tagObj);
                    }
                    tagSet.add(tagObj);
                }
            }
            
            //Pegdown
            if (enablePegdown){
                blogPost.setMarkup(new PegDownProcessor().markdownToHtml(blogPost.getMarkup()));
            }
            
            blogPost.setTags(tagSet);
            blogPost.setPostDate(new Date());
            blogService.save(blogPost);
            
            status.setComplete();
            
            return "redirect:/admin/blog";

        }
    }
    
    //Delete blog post
    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String deleteBlogPost(@PathVariable("id") Integer id, SessionStatus status){
        blogService.delete(id);
        status.setComplete();
        return "The item was deleted succesfully";
    }
    
    //Cancel post edit
    @RequestMapping(value="/cancel", method=RequestMethod.GET)
    public String cancelBlogEdit(SessionStatus status){
        status.setComplete();
        return "redirect:/admin/blog";
    }
    
    
}

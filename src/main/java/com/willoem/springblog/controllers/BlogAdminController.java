package com.willoem.springblog.controllers;

import com.willoem.springblog.models.BlogPost;
import com.willoem.springblog.models.Tag;
import com.willoem.springblog.services.BlogService;
import com.willoem.springblog.services.TagService;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.hibernate.Hibernate;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
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
    @RequestMapping(value="/admin/blog", method=RequestMethod.GET)
    public String blogAdmin(ModelMap map, SessionStatus status){
        status.setComplete();
        List<BlogPost> postList = blogService.getAllBlogPosts();
        map.addAttribute("postList", postList);
        return "admin/blogPostList";
    }
    
    //Add new blog post
    @RequestMapping(value="/admin/blog/new", method=RequestMethod.GET)
    public String newPost(ModelMap map){
        BlogPost blogPost = new BlogPost();
        map.addAttribute("blogPost", blogPost);
        return "admin/editBlogPost";
    }
    
    //Save new post
    @RequestMapping(value="/admin/blog/new", method=RequestMethod.POST)
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
                    Tag tagObj = tagService.getTagByName(tag);
                    //If not, add it
                    if (tagObj == null){
                        tagObj = new Tag();
                        tagObj.setTagName(tag);
                        tagService.saveTag(tagObj);
                    }
                    tagSet.add(tagObj);
                }
            }
            
            //Pegdown
            if (enablePegdown){
                blogPost.setMarkup(new PegDownProcessor().markdownToHtml(blogPost.getMarkup()));
            }
            
            blogPost.setPostDate(Calendar.getInstance());
            blogPost.setTags(tagSet);
            blogService.saveBlogPost(blogPost);
            
            status.setComplete();
            
            return "redirect:/admin/blog";

        }
    }
    
    //Edit existing blog post
    @Transactional
    @RequestMapping(value="/admin/blog/{id}", method=RequestMethod.GET)
    public String editPost(ModelMap map, @PathVariable("id") Integer postId){
        BlogPost blogPost = blogService.getBlogPostById(postId);
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
    @RequestMapping(value="/admin/blog/{id}", method=RequestMethod.POST)
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
                    Tag tagObj = tagService.getTagByName(tag);
                    //If not, add it
                    if (tagObj == null){
                        tagObj = new Tag();
                        tagObj.setTagName(tag);
                        tagService.saveTag(tagObj);
                    }
                    tagSet.add(tagObj);
                }
            }
            
            //Pegdown
            if (enablePegdown){
                blogPost.setMarkup(new PegDownProcessor().markdownToHtml(blogPost.getMarkup()));
            }
            
            blogPost.setTags(tagSet);
            blogPost.setPostDate(Calendar.getInstance());
            blogService.updateBlogPost(blogPost);
            
            status.setComplete();
            
            return "redirect:/admin/blog";

        }
    }
    
    //Delete blog post
    @RequestMapping(value="/admin/delete/blog/{id}", method=RequestMethod.POST)
    public @ResponseBody String deleteBlogPost(@PathVariable("id") Integer id, SessionStatus status){
        blogService.deleteBlogPost(id);
        status.setComplete();
        return "The item was deleted succesfully";
    }
    
    //Cancel post edit
    @RequestMapping(value="/admin/blog/cancel", method=RequestMethod.GET)
    public String cancelBlogEdit(SessionStatus status){
        status.setComplete();
        return "redirect:/admin/blog";
    }
    
    //AJAX event for autocomplete
    @RequestMapping(value="/admin/tags/autocomplete", method=RequestMethod.POST)
    public @ResponseBody String tagsAutocomplete(@RequestParam("fragment") String fragment) 
            throws UnsupportedEncodingException
    {
        String json = "";
        if (fragment.isEmpty()){
                //pass
        }
        else {
           json = blogService.getTagAutocompleteJson(fragment);
        }

        return json;
    }
}

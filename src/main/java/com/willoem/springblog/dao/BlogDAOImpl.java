
package com.willoem.springblog.dao;

import com.google.gson.Gson;
import com.willoem.springblog.models.BlogPost;
import com.willoem.springblog.models.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDAOImpl implements BlogDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    
    //Get every blog post in order by date
    @Override
    public List<BlogPost> getAllBlogPosts(){
        return getCurrentSession()
                .createQuery("from BlogPost p order by p.postDate desc")
                .list();
    }
    
    //Get live blog posts by page, sorted by date
    @Override
    public Map<String,Object> getBlogPostsByPage(Integer page){
        
        Map<String,Object> pageData = new HashMap();
        Integer postsPerPage = 10; //Determines page size
        
        Integer postCount = ((Long) getCurrentSession()
                .createQuery("select count(post_id) from BlogPost where status = 'live'")
                .uniqueResult())
                .intValue();
        pageData.put("postCount", postCount);
        
        List<BlogPost> blogPosts = getCurrentSession()
                .createQuery("from BlogPost p where p.status='live' order by p.postDate desc")
                .setMaxResults(postsPerPage)
                .setFirstResult((postsPerPage * page) - postsPerPage)
                .list();
        
        pageData.put("blogPosts", blogPosts);
        
        //Integer numPages = (int) Math.ceil(postCount/postsPerPage);
        Integer numPages = (int) (postCount/postsPerPage)+1;
        pageData.put("numPages",numPages);
        
        return pageData;
    }
    
    //Get count of all blog posts
    @Override
    public Integer getBlogPostCount(){
        return ((Long) getCurrentSession()
                .createQuery("select count(*) from BlogPost")
                .uniqueResult())
                .intValue();
    }
    
    //Get blog post by slug
    @Override
    public Map<String,BlogPost> getBlogPostBySlug(String slug){
        Map<String, BlogPost> requestedPosts = new HashMap();
        List<BlogPost> posts = getCurrentSession()
                //.createQuery("from BlogPost p where p.status = 'live' order by p.postDate desc")
                .createQuery("from BlogPost p order by p.postDate desc")
                .list();
        
        for (int i=0; i<posts.size(); i=i+1){
            
            //Is this the requested post
            if (posts.get(i).getSlug().equals(slug)){
                requestedPosts.put("requestedPost", posts.get(i));
                
                //Check if next post exists
                if (i+1 < posts.size()){
                    requestedPosts.put("nextPost", posts.get(i+1));
                }
                else {
                    requestedPosts.put("nextPost", null);
                }
                
                //CHeck if previous post exists
                if (i-1 > -1){
                    requestedPosts.put("previousPost", posts.get(i-1));
                }
                else {
                    requestedPosts.put("previousPost", null);
                }
            }
        }
        
        return requestedPosts;
    }
    
    //Get blog post by ID
    @Override
    public BlogPost getBlogPostById(Integer postId){
        return (BlogPost) getCurrentSession()
                .createQuery("from BlogPost p where p.postId = :postId")
                .setParameter("postId", postId)
                .list()
                .get(0);
    }
    
    @Override
    public void saveBlogPost(BlogPost post){
        getCurrentSession().save(post);
    }
    
    @Override
    public void updateBlogPost(BlogPost post){
        getCurrentSession().update(post);
    }
    
    @Override
    public void deleteBlogPost(Integer postId){
        getCurrentSession().delete(this.getBlogPostById(postId));
    }
    
    //Get tags for autocomplete
    @Override
    public String getTagAutocompleteJson(String fragment){
        List<Tag> results = getCurrentSession()
                .createQuery("from Tag t where t.tagName like :fragment order by t.tagName")
                .setParameter("fragment", "%"+fragment+"%")
                .list();
        List<String> tags = new ArrayList();
       
        if (results.isEmpty()){
            //pass
        }
        else {
            for (Tag tag: results){
                tags.add(tag.getTagName());
            }
        }
        Gson gson = new Gson();
        return gson.toJson(tags);
    }
}

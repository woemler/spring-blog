
package com.willoem.springblog.services;

import com.willoem.springblog.dao.BlogDAO;
import com.willoem.springblog.models.BlogPost;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    
    @Autowired
    private BlogDAO blogDao;
    
    @Override
    public List<BlogPost> getAllBlogPosts(){
        return blogDao.getAllBlogPosts();
    }
    
    @Override
    public Map<String,Object> getBlogPostsByPage(Integer page){
        return blogDao.getBlogPostsByPage(page);
    }
    
    @Override
    public Integer getBlogPostCount(){
        return blogDao.getBlogPostCount();
    }
    
    @Override
    public Map<String,BlogPost> getBlogPostBySlug(String slug){
        return blogDao.getBlogPostBySlug(slug);
    }
    
    @Override
    public BlogPost getBlogPostById(Integer postId){
        return blogDao.getBlogPostById(postId);
    }
    
    @Override
    public void saveBlogPost(BlogPost post){
        blogDao.saveBlogPost(post);
    }
    
    @Override
    public void updateBlogPost(BlogPost post){
        blogDao.updateBlogPost(post);
    }
    
    @Override 
    public void deleteBlogPost(Integer postId){
        blogDao.deleteBlogPost(postId);
    }
    
}

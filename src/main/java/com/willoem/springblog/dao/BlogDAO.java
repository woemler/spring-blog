
package com.willoem.springblog.dao;

import com.willoem.springblog.models.BlogPost;
import java.util.List;
import java.util.Map;


public interface BlogDAO {

    List<BlogPost> getAllBlogPosts();
    Map<String,Object> getBlogPostsByPage(Integer page);
    Integer getBlogPostCount();
    Map<String,BlogPost> getBlogPostBySlug(String slug);
    BlogPost getBlogPostById(Integer postId);
    void saveBlogPost(BlogPost post);
    void updateBlogPost(BlogPost post);
    void deleteBlogPost(Integer postId);
    String getTagAutocompleteJson(String fragment);
    
}

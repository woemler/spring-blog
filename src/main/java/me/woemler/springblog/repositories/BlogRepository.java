
package me.woemler.springblog.repositories;

import me.woemler.springblog.models.BlogPost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author woemler 
 */

public interface BlogRepository extends MongoRepository<BlogPost, String>, BlogOperations {
    List<BlogPost> findByStatusOrderByPostDateAsc(String status);
    Page<BlogPost> findByStatusOrderByPostDateAsc(String status, Pageable pageable);
    BlogPost findBySlug(String slug);
	List<BlogPost> findByTags(String tag);
}

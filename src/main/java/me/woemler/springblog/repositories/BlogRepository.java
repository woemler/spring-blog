
package me.woemler.springblog.repositories;

import me.woemler.springblog.models.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author woemler 
 */

public interface BlogRepository extends MongoRepository<BlogPost, String>, CustomBlogRepository {
  BlogPost findBySlug(String slug);
	List<BlogPost> findByTags(String tag);
}
